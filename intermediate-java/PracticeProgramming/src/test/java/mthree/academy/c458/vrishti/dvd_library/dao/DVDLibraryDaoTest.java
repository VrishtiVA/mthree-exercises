package mthree.academy.c458.vrishti.dvd_library.dao;


import mthree.academy.c458.vrishti.dvd_library.dto.DVD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DVDLibraryDaoTest {

    private final String TEST_DVD_LIBRARY_FILE = "test-dvd-library.txt";
    private DVDLibraryDao testDao;

    @BeforeEach
    void setUp() throws IOException {
        //Blank the file first - important to start with empty file before passing to testDao.
        new FileWriter(TEST_DVD_LIBRARY_FILE);
        //Now use this file
        testDao = new DVDLibraryDaoFileImpl(TEST_DVD_LIBRARY_FILE);
    }

    @Test
    void testAddGetDVD() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();

        //Act
        DVD previousDVD = testDao.addDVD(testDVD);
        DVD retrievedDVD = testDao.getDVD(testDVD.getId());

        //Assert
        assertNull(previousDVD);
        assertEquals(testDVD, retrievedDVD);
    }

    @Test
    void testRemoveDVD() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();
        DVD testDVD2 = DVDSamples.getDVDSample2();
        testDao.addDVD(testDVD);
        testDao.addDVD(testDVD2);

        //Act
        DVD removedDVD = testDao.removeDVD(testDVD.getId());
        DVD retrievedDVD = testDao.getDVD(testDVD.getId());
        DVD otherDVD = testDao.getDVD(testDVD2.getId());

        //Assert
        assertEquals(testDVD, removedDVD);
        assertNull(retrievedDVD);
        assertEquals(testDVD2, otherDVD);
    }


    @Test
    void testRemoveNonExistentDVD() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();
        testDao.addDVD(testDVD);

        //Act
        DVD removedDVD = testDao.removeDVD(2);
        DVD retrievedDVD = testDao.getDVD(testDVD.getId());

        //Assert
        assertEquals(retrievedDVD, testDVD);
        assertNull(removedDVD);
    }

    @Test
    void testEditDVD() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();
        DVD testDVD2 = DVDSamples.getDVDSample2();
        testDao.addDVD(testDVD);
        testDao.addDVD(testDVD2);
        DVD edittedDVD = DVDSamples.getDVDSample1();
        edittedDVD.setTitle("Harry Potter 1");

        //Act
        DVD originalDVD = testDao.editDVD(testDVD.getId(), edittedDVD);
        DVD retrievedDVD = testDao.getDVD(testDVD.getId());
        DVD otherDVD = testDao.getDVD(testDVD2.getId());

        //Assert
        assertEquals(testDVD, originalDVD);
        assertEquals(edittedDVD, retrievedDVD);
        assertEquals(testDVD2, otherDVD);
    }

    @Test
    void testGetDVDs() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();
        DVD testDVD2 = DVDSamples.getDVDSample2();
        testDao.addDVD(testDVD);
        testDao.addDVD(testDVD2);

        //Act
        List<DVD> dvds = testDao.getDVDs();

        //Assert
        assertNotNull(dvds);
        assertEquals(2, dvds.size());
        assertTrue(dvds.contains(testDVD));
        assertTrue(dvds.contains(testDVD2));
    }


    @Test
    void testGetDVDsEmpty() {

        //Act
        List<DVD> dvds = testDao.getDVDs();

        //Assert
        assertNotNull(dvds);
        assertEquals(0, dvds.size());
    }

    @Test
    void testGetDVD() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();
        testDao.addDVD(testDVD);

        //Act
        DVD retrievedDVD = testDao.getDVD(testDVD.getId());

        //Assert
        assertNotNull(retrievedDVD);
        assertEquals(testDVD, retrievedDVD);
    }

    @Test
    void testGetNonExistentDVD() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();

        //Act
        DVD retrievedDVD = testDao.getDVD(testDVD.getId());

        //Assert
        assertNull(retrievedDVD);
    }

    @Test
    void testFindDVDsByTitle() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();
        DVD testDVD2 = DVDSamples.getDVDSample2();
        DVD testDVD3 = DVDSamples.getDVDSample3();
        testDao.addDVD(testDVD);
        testDao.addDVD(testDVD2);
        testDao.addDVD(testDVD3);

        //Act
        List<DVD> dvds = testDao.findDVDsByTitle("harry");

        //Assert
        assertNotNull(dvds);
        assertEquals(2, dvds.size());
        assertTrue(dvds.contains(testDVD));
        assertTrue(dvds.contains(testDVD2));
        assertFalse(dvds.contains(testDVD3));
    }

    @Test
    void testFindDVDsByTitleEmpty() {

        //Arrange
        DVD testDVD = DVDSamples.getDVDSample1();
        DVD testDVD2 = DVDSamples.getDVDSample2();
        DVD testDVD3 = DVDSamples.getDVDSample3();
        testDao.addDVD(testDVD);
        testDao.addDVD(testDVD2);
        testDao.addDVD(testDVD3);

        //Act
        List<DVD> dvds = testDao.findDVDsByTitle("unknown");

        //Assert
        assertNotNull(dvds);
        assertEquals(0, dvds.size());
    }
}