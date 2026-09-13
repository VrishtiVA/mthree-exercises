package com.mthree.academy.c458.vrishti.jco.exercises.address_book.dao;


import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dto.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookDaoFileImplTest {

    //Constant(s)
    String TEST_FILE = "testAddressBook.txt";

    //Attribute(s)
    AddressBookDao testDao;

    private Address buildSampleAddress1() {
        Address testAddress = new Address(1);
        testAddress.setFirstName("John");
        testAddress.setLastName("Doe");
        testAddress.setStreet("18 Unknown Street");
        testAddress.setCity("San Francisco");
        testAddress.setState("California");
        testAddress.setCountry("USA");
        testAddress.setPostCode("94120");
        return testAddress;
    }

    private Address buildSampleAddress2() {
        Address testAddress = new Address(2);
        testAddress.setFirstName("Jane");
        testAddress.setLastName("Doe");
        testAddress.setStreet("18 Unknown Street");
        testAddress.setCity("San Francisco");
        testAddress.setState("California");
        testAddress.setCountry("USA");
        testAddress.setPostCode("94120");
        return testAddress;
    }

    private Address buildSampleAddress3() {
        Address testAddress = new Address(3);
        testAddress.setFirstName("Felonius");
        testAddress.setLastName("Gru");
        testAddress.setStreet("2230 N Broadway");
        testAddress.setCity("San Francisco");
        testAddress.setState("California");
        testAddress.setCountry("USA");
        testAddress.setPostCode("94121");
        return testAddress;
    }

    @BeforeEach
    void setUp() throws IOException {
        //Blank the file
        new FileWriter(TEST_FILE);
        //Ready the test dao
        this.testDao = new AddressBookDaoFileImpl(TEST_FILE);
    }

    @Test
    void testAddGetAddress() {

        //Arrange
        Address testAddress = buildSampleAddress1();

        //Act
        boolean success = testDao.addAddress(testAddress);
        Address retrievedAddress = testDao.getAddressById(1);

        //Assert
        assertTrue(success);
        assertEquals(testAddress, retrievedAddress);
    }

    @Test
    void testAddRemoveGetAddress() {

        //Arrange
        Address testAddress = buildSampleAddress1();
        testDao.addAddress(testAddress);

        //Act
        boolean success = testDao.removeAddress(testAddress.getId());
        Address retrievedAddress = testDao.getAddressById(testAddress.getId());

        //Assert
        assertTrue(success);
        assertNull(retrievedAddress);
    }

    @Test
    void testCountAddresses() {

        //1. Act
        int count = testDao.countAddresses();

        //Assert
        assertEquals(0, count, "Initially the address book would be empty.");

        //2. Arrange
        Address testAddress = buildSampleAddress1();
        testDao.addAddress(testAddress);

        //Act
        count = testDao.countAddresses();

        //Assert
        assertEquals(1, count);
    }

    @Test
    void testGetAllAddressesEmpty() {

        //Act
        List<Address> allAddresses = testDao.getAllAddresses();

        //Assert
        assertNotNull(allAddresses);
        assertEquals(0, allAddresses.size());
    }

    @Test
    void testGetAllAddresses() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        Address testAddress2 = buildSampleAddress2();
        Address testAddress3 = buildSampleAddress3();
        testDao.addAddress(testAddress1);
        testDao.addAddress(testAddress2);
        testDao.addAddress(testAddress3);

        //Act
        List<Address> allAddresses = testDao.getAllAddresses();

        //Assert
        assertNotNull(allAddresses);
        assertEquals(3, allAddresses.size());
        assertTrue(allAddresses.contains(testAddress1));
        assertTrue(allAddresses.contains(testAddress2));
        assertTrue(allAddresses.contains(testAddress3));
    }

    @Test
    void testGetAllAddressesByLastName() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        Address testAddress2 = buildSampleAddress2();
        Address testAddress3 = buildSampleAddress3();
        testDao.addAddress(testAddress1);
        testDao.addAddress(testAddress2);
        testDao.addAddress(testAddress3);

        //Act
        List<Address> allAddresses = testDao.getAllAddressesByLastName("Doe");

        //Assert
        assertNotNull(allAddresses);
        assertEquals(2, allAddresses.size());
        assertTrue(allAddresses.contains(testAddress1));
        assertTrue(allAddresses.contains(testAddress2));
        assertFalse(allAddresses.contains(testAddress3));
    }

    @Test
    void testGetAddressById() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        Address testAddress2 = buildSampleAddress2();
        testDao.addAddress(testAddress1);
        testDao.addAddress(testAddress2);

        //Act
        Address address = testDao.getAddressById(1);

        //Assert
        assertEquals(testAddress1, address);
        assertNotEquals(testAddress2, address);
    }

    @Test
    void testUpdateFirstName() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        testDao.addAddress(testAddress1);

        //Act
        boolean success = testDao.updateFirstName(testAddress1.getId(), "Samantha");
        Address retrievedAddress = testDao.getAddressById(testAddress1.getId());

        //Assert
        assertTrue(success);
        assertEquals("Samantha", retrievedAddress.getFirstName());
    }

    @Test
    void testUpdateLastName() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        testDao.addAddress(testAddress1);

        //Act
        boolean success = testDao.updateLastName(testAddress1.getId(), "Samantha");
        Address retrievedAddress = testDao.getAddressById(testAddress1.getId());

        //Assert
        assertTrue(success);
        assertEquals("Samantha", retrievedAddress.getLastName());
    }

    @Test
    void updateAddressStreet() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        testDao.addAddress(testAddress1);

        //Act
        boolean success = testDao.updateAddressStreet(testAddress1.getId(), "Samantha");
        Address retrievedAddress = testDao.getAddressById(testAddress1.getId());

        //Assert
        assertTrue(success);
        assertEquals("Samantha", retrievedAddress.getStreet());
    }

    @Test
    void updateAddressCity() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        testDao.addAddress(testAddress1);

        //Act
        boolean success = testDao.updateAddressCity(testAddress1.getId(), "Samantha");
        Address retrievedAddress = testDao.getAddressById(testAddress1.getId());

        //Assert
        assertTrue(success);
        assertEquals("Samantha", retrievedAddress.getCity());
    }

    @Test
    void updateAddressState() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        testDao.addAddress(testAddress1);

        //Act
        boolean success = testDao.updateAddressState(testAddress1.getId(), "Samantha");
        Address retrievedAddress = testDao.getAddressById(testAddress1.getId());

        //Assert
        assertTrue(success);
        assertEquals("Samantha", retrievedAddress.getState());
    }

    @Test
    void updateAddressCountry() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        testDao.addAddress(testAddress1);

        //Act
        boolean success = testDao.updateAddressCountry(testAddress1.getId(), "Samantha");
        Address retrievedAddress = testDao.getAddressById(testAddress1.getId());

        //Assert
        assertTrue(success);
        assertEquals("Samantha", retrievedAddress.getCountry());
    }

    @Test
    void updateAddressPostCode() {

        //Arrange
        Address testAddress1 = buildSampleAddress1();
        testDao.addAddress(testAddress1);

        //Act
        boolean success = testDao.updateAddressPostCode(testAddress1.getId(), "Samantha");
        Address retrievedAddress = testDao.getAddressById(testAddress1.getId());

        //Assert
        assertTrue(success);
        assertEquals("Samantha", retrievedAddress.getPostCode());
    }
}