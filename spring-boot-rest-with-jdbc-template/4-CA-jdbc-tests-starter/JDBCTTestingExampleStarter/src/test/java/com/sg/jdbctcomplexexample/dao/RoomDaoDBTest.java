package com.sg.jdbctcomplexexample.dao;


import com.sg.jdbctcomplexexample.TestApplicationConfiguration;
import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Annotations to allow tests to access all our DAOs and
// ensure that Spring Boot will understand that these are tests it can run.
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoomDaoDBTest {

    //Autowire in DAOs.
    //Althrough only testing RoomDAO, we want access to the other 2 DAOs
    //So that can create complex objects for appropriate tests.
    @Autowired
    RoomDao roomDao;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    MeetingDao meetingDao;

    /**
     * Empty the test database before every single test
     * Returning it to an expected state
     * So that only what we add is considered for each test.
     * Done by getting all of each object, then deleting them.
     * Errors for all tests would suggest something wrong with get all or delete method
     */
    @BeforeEach
    public void setUp() {

        List<Room> rooms = roomDao.getAllRooms();
        for (Room room : rooms) {
            roomDao.deleteRoomById(room.getId());
        }

        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            employeeDao.deleteEmployeeById(employee.getId());
        }

        List<Meeting> meetings = meetingDao.getAllMeetings();
        for (Meeting meeting : meetings) {
            meetingDao.deleteMeetingById(meeting.getId());
        }
    }

    /**
     * Combined add and get by id.
     * If separate, tests almost always exact same
     * since need to add to get, and need to get to test add.
     */
    @Test
    void testAddGetRoom() {

        //Arrange - room object to add
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");

        //Act - add and get
        room = roomDao.addRoom(room);
        Room fromDao = roomDao.getRoomById(room.getId());

        //Assert - that created room is same as fetched room
        assertEquals(room, fromDao);
    }

    @Test
    void testGetAllRooms() {

        //Arrange rooms
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        roomDao.addRoom(room);

        Room room2 = new Room();
        room2.setName("Test Room 2");
        room2.setDescription("Test Room 2");
        roomDao.addRoom(room2);

        //Act
        List<Room> rooms = roomDao.getAllRooms();

        //Assert
        assertEquals(2, rooms.size());
        assertTrue(rooms.contains(room));
        assertTrue(rooms.contains(room2));
    }

    @Test
    void testUpdateRoom() {

        //Arrange - room in database
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        Room fromDao = roomDao.getRoomById(room.getId());
        //Assert - set-up correctly
        assertEquals(room, fromDao, "Didn't add to update properly");

        //Arrange
        room.setName("Another Test Room");
        //Act
        roomDao.updateRoom(room);
        //Assert - not same as before
        assertNotEquals(room, fromDao);

        //Act
        fromDao = roomDao.getRoomById(room.getId());
        //Assert - was actually updated
        assertEquals(room, fromDao);
    }

    /**
     * Since room delete has 3 parts, need to make sure those relationships
     * exist in database when we try test to delete.
     * Otherwise, wouldn't be testing that we're handling the relationships
     * room has with other objects.
     */
    @Test
    void testDeleteRoom() {

        //Arrange - Add room
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);

        //Arrange - Add employee
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);

        //Arrange - Add meeting
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now());
        meeting.setRoom(room);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);

        //Act - Delete room and try get
        roomDao.deleteRoomById(room.getId());
        Room fromDao = roomDao.getRoomById(room.getId());

        //Assert - that cannot get it back.
        assertNull(fromDao);
    }
}