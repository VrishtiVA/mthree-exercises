package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository using annotation based DI
 */
@Repository
public class RoomDaoDBImpl implements RoomDao {

    /**
     * Autowire in JDBC template
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Room mapper to allow turning database data into a Room object.
     * Public so that we can access this mapper
     * e.g. to add a Room object to the meeting objects in Meeting DAO.
     */
    public static final class RoomMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {

            Room rm = new Room();
            rm.setId(rs.getInt("id"));
            rm.setName(rs.getString("name"));
            rm.setDescription(rs.getString("description"));

            return rm;
        }
    }

    @Override
    public List<Room> getAllRooms() {

        //Declaring query as a string
        final String SELECT_ALL_ROOMS = "SELECT * FROM room";
        //Query for results with query, to obtain a list of rooms.
        return jdbcTemplate.query(SELECT_ALL_ROOMS, new RoomMapper());
    }

    @Override
    public Room getRoomById(int id) {
        try {
            //Declaring query as a string
            final String SELECT_ROOM_BY_ID = "SELECT * FROM room WHERE id = ?";
            //Query for a single result object
            return jdbcTemplate.queryForObject(SELECT_ROOM_BY_ID, new RoomMapper(), id);
        } catch (DataAccessException e) {
            //Catch exception e.g. if no object returned or cannot data access.
            //Null to indicate we could not retrieve the object.
            return null;
        }
    }

    /**
     * Add a room object to the database.
     * The database assigns the room an ID.
     * @param room Room object without an ID.
     * @return Room object with an ID, that was added to the database.
     */
    @Override
    @Transactional //Want it to get the last ID, no other query to run during this transaction.
    public Room addRoom(Room room) {

        //Add room, using update method since db changes.
        final String INSERT_ROOM = "INSERT INTO room (name, description) VALUES (?, ?)";
        jdbcTemplate.update(INSERT_ROOM,
            room.getName(),
            room.getDescription()
        );

        //Special query to get the ID from the database.
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        //Return updated room object
        room.setId(newId);
        return room;
    }

    @Override
    public void updateRoom(Room room) {

        //Define update query
        final String UPDATE_ROOM = "UPDATE room SET name = ?, description = ? WHERE id = ?";

        //Use update method to send update to database with necessary data.
        jdbcTemplate.update(UPDATE_ROOM,
            room.getName(),
            room.getDescription(),
            room.getId()
        );
    }

    /**
     * Transactional: Multiple modifications should success/fail together.
     * Multi-tier delete: Due to FK relationships. out to in.
     *                    Order is very important for deletes.
     * @param id
     */
    @Override
    @Transactional
    public void deleteRoomById(int id) {

        //Delete FK relationships with meetings
        final String DELETE_MEETING_EMPLOYEE_BY_ROOM =
                "DELETE me.* FROM meeting_employee me " +
                "JOIN meeting m ON me.meetingId = m.id WHERE m.roomId = ?";
        jdbcTemplate.update(DELETE_MEETING_EMPLOYEE_BY_ROOM, id);

        //Delete related meetings
        final String DELETE_MEETING_BY_ROOM =
                "DELETE FROM meeting WHERE roomId = ?";
        jdbcTemplate.update(DELETE_MEETING_BY_ROOM, id);

        //Delete the room
        final String DELETE_ROOM = "DELETE FROM room WHERE id = ?";
        jdbcTemplate.update(DELETE_ROOM, id);
    }
}
