package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Most complicated of the 3 DAOs
 * Since it handles both the relationship with Room, and with Employee.
 */
@Repository
public class MeetingDaoDBImpl implements MeetingDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * This one could be private, since no other class would use.
     * But making public for consistency.
     */
    public static final class MeetingMapper implements RowMapper<Meeting> {

        @Override
        public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {

            Meeting meet = new Meeting();
            meet.setId(rs.getInt("id"));
            meet.setName(rs.getString("name"));
            //To bring date time, we'll bring it in as a Timestamp, and convert it to LocalDate.
            meet.setTime(rs.getTimestamp("time").toLocalDateTime());
            //Not taking in the foreign keys here.

            return meet;
        }
    }

    @Override
    public List<Meeting> getAllMeetings() {

        final String SELECT_ALL_MEETINGS = "SELECT * FROM meeting";
        //Query for all meetings
        List<Meeting> meetings = jdbcTemplate.query(SELECT_ALL_MEETINGS, new MeetingMapper());

        //Helper method: pass in list of all meetings, and have it add in room and employees for each.
        addRoomAndEmployeesToMeetings(meetings);

        //Return all meetings.
        return meetings;
    }

    /**
     * Private helper that other classes don't use.
     * @param meetings List of meetings we want to work with.
     */
    private void addRoomAndEmployeesToMeetings(List<Meeting> meetings) {

        //Enhanced loop to loop over each meeting
        //Using other helper methods for that.
        for (Meeting meeting : meetings) {
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));
        }
    }

    @Override
    public Meeting getMeetingByid(int id) {
        try {
            //Get non-fks object, query for single object.
            final String SELECT_MEETING_BY_ID = "SELECT * FROM meeting WHERE id = ?";
            Meeting meeting = jdbcTemplate.queryForObject(SELECT_MEETING_BY_ID, new MeetingMapper(), id);

            //Set FK relationshiped objects, using helper methods to get them
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));

            //Return meeting
            return meeting;
        } catch (DataAccessException e) {
            //In case no meeting found, indicate with null.
            return null;
        }
    }

    /**
     * Private helper method, since no other class will ever be using this method.
     * @implNote No try-catch here, since db definition should guarantee our meeting has a valid room in it.
     *           So we should never see an exception thrown here.
     * @param meeting Meeting object we want the room for, so we can access its id.
     * @return room found.
     */
    private Room getRoomForMeeting(Meeting meeting) {

        //Join room to meeting, so we can get the room object by meeting id.
        final String SELECT_ROOM_FOR_MEETING = "SELECT r.* FROM room r JOIN meeting m ON r.id = m.roomId WHERE m.id = ?";
        //Query for single room object.
        return jdbcTemplate.queryForObject(SELECT_ROOM_FOR_MEETING, new RoomDaoDBImpl.RoomMapper(), meeting.getId());
    }

    /**
     * Private since helper method that other classes should not be able to see.
     * @param meeting Meeting object so we can gets its id.
     * @return
     */
    private List<Employee> getEmployeesForMeeting(Meeting meeting) {

        //Joining meeting_employee to employee, so we can get all employee objects by meetign id.
        final String SELECT_EMPLOYEES_FOR_MEETING = "SELECT e.* FROM employee e JOIN meeting_employee me ON e.id = me.employeeId WHERE me.meetingId = ?";
        //Query for multiple employees.
        return jdbcTemplate.query(SELECT_EMPLOYEES_FOR_MEETING, new EmployeeDaoDBImpl.EmployeeMapper(), meeting.getId());
    }

    @Override
    @Transactional //Guarantee correct ID out of database.
    public Meeting addMeeting(Meeting meeting) {

        //Add meeting with update method
        final String INSERT_MEETING = "INSERT INTO meeting (name, time, roomId) VALUES (?, ?, ?)";
        jdbcTemplate.update(INSERT_MEETING,
            meeting.getName(),
            //Passing LocalDateTime field into database, by wrapping with Timestamp.valueOf so that it looks correct when we write to database.
            Timestamp.valueOf(meeting.getTime()),
            meeting.getRoom().getId() //Room ID handled here.
        );

        //Find and set id of last insert (id for new meeting)
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        meeting.setId(newId);

        //Handle creating bridge table entries for meeting/employee relationship.
        //To preserve the meeting with all its attendees.
        insertMeetingEmployee(meeting);

        //Return meeting object
        return meeting;
    }

    /**
     * To insert new entries into the meeting_employee bridge table to save the relationship
     * between meeting and any employee attending the meeting.
     * @param meeting The meeting to insert for.
     */
    private void insertMeetingEmployee(Meeting meeting) {

        //Create query string before the loop since never actually change, only data we put into it does.
        final String INSERT_MEETING_EMPLOYEE = "INSERT INTO meeting_employee (meetingId, employeeId) VALUES (?, ?)";
        //Loop over list of employees, and use update method.
        for (Employee employee : meeting.getAttendees()) {
            jdbcTemplate.update(INSERT_MEETING_EMPLOYEE, meeting.getId(), employee.getId());
        }
    }

    /**
     * @implNote Deleting relationship and recreating in an update method,
     *           can be an easy way to handle relationship information.
     *           Could also analyze and make specific updates, but can be extra work that
     *           doesn't increase efficiency in this case.
     * @param meeting
     */
    @Override
    @Transactional //Multiple modifying calls to db, should succeed/fail as a unit
    public void updateMeeting(Meeting meeting) {

        //Update meeting using update method
        final String UPDATE_MEETING = "UPDATE meeting SET name = ?, time = ?, roomId = ? WHERE id = ?";
        jdbcTemplate.update(UPDATE_MEETING,
            meeting.getName(),
            Timestamp.valueOf(meeting.getTime()), //Make sure localdatetime looks right for database
            meeting.getRoom().getId(),
            meeting.getId()
        );

        //Handle relationship with employee by refreshing bridge table entries for it.
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee WHERE meetingId = ?";
        jdbcTemplate.update(DELETE_MEETING_EMPLOYEE, meeting.getId());

        //Re-insert the employees that are now associated with this meeting.
        insertMeetingEmployee(meeting);
    }

    @Override
    public void deleteMeetingById(int id) {

        //Delete meeting employees first - handling FK relationships first
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee WHERE meetingId = ?";
        jdbcTemplate.update(DELETE_MEETING_EMPLOYEE, id);

        //Then delete the meeting itself
        final String DELETE_MEETING = "DELETE FROM meeting WHERE id = ?";
        jdbcTemplate.update(DELETE_MEETING, id);
    }

    @Override
    public List<Meeting> getMeetingsForRoom(Room room) {

        //Get all meetings by room id
        final String SELECT_MEETINGS_FOR_ROOM = "SELECT * FROM meeting WHERE roomId = ?";
        List<Meeting> meetings = jdbcTemplate.query(SELECT_MEETINGS_FOR_ROOM, new MeetingMapper(), room.getId());

        //Use helper to populate rooms and employees for each meeting
        addRoomAndEmployeesToMeetings(meetings);

        //Return list of meetings for room.
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsForEmployee(Employee employee) {

        //Get all meetings by employee id
        final String SELECT_MEETINGS_FOR_EMPLOYEE = "SELECT * FROM meeting m JOIN meeting_employee me ON m.id = me.meetingId WHERE me.employee = ?";
        List<Meeting> meetings = jdbcTemplate.query(SELECT_MEETINGS_FOR_EMPLOYEE, new MeetingMapper(), employee.getId());

        //Use helper to populate rooms and employees for each meeting
        addRoomAndEmployeesToMeetings(meetings);

        //Return list of meetings for employee
        return meetings;
    }
}
