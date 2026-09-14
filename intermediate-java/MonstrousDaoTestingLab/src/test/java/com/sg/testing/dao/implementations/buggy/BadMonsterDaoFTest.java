package com.sg.testing.dao.implementations.buggy;

import com.sg.testing.dao.MonsterDao;
import com.sg.testing.dao.implementations.AGoodMonsterDao;
import com.sg.testing.model.Monster;
import com.sg.testing.model.MonsterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BadMonsterDaoFTest {

    MonsterDao testDao;

    @BeforeEach
    void setUp() {
        //Doing stateful tests, so we'll reset to good known states.

        this.testDao = new BadMonsterDaoF();
    }

    private Monster getSampleMonster1() {
        Monster monster = new Monster(
                "Godzilla",
                MonsterType.LIZARDMAN,
                1000000,
                "Todos"
        );
        return monster;
    }

    private Monster getSampleMonster2() {
        Monster monster = new Monster(
                "Dracula",
                MonsterType.VAMPIRE,
                0,
                "Blood"
        );
        return monster;
    }

    @Test
    void testAddGetMonster() {

        //Arrange
        Monster sampleMonster = getSampleMonster1();

        //Act
        Monster previousMonster = testDao.addMonster(1, sampleMonster);
        Monster retrievedMonster = testDao.getMonster(1);

        //Assert
        assertNull(previousMonster);
        assertEquals(sampleMonster, retrievedMonster);
    }

    @Test
    void testAddOverrideGetMonster() {

        //Arrange
        Monster sampleMonster = getSampleMonster1();
        Monster sampleMonster2 = getSampleMonster2();
        testDao.addMonster(1, sampleMonster);

        //Act
        Monster previousMonster = testDao.addMonster(1, sampleMonster2);
        Monster retrievedMonster = testDao.getMonster(1);

        //Assert
        assertEquals(sampleMonster, previousMonster);
        assertEquals(sampleMonster2, retrievedMonster);
    }

    @Test
    void testGetMonster() {

        //Arrange
        Monster sampleMonster = getSampleMonster1();
        testDao.addMonster(1, sampleMonster);

        //Act
        Monster retrievedMonster = testDao.getMonster(1);

        //Assert
        assertEquals(sampleMonster, retrievedMonster);
    }

    @Test
    void testGetMonsterNull() {

        //Act
        Monster retrievedMonster = testDao.getMonster(1);

        //Assert
        assertNull(retrievedMonster);
    }

    @Test
    void testGetAllMonsters() {

        //Arrange
        Monster sampleMonster1 = getSampleMonster1();
        Monster sampleMonster2 = getSampleMonster2();
        testDao.addMonster(1, sampleMonster1);
        testDao.addMonster(2, sampleMonster2);

        //Act
        List<Monster> monsterList = testDao.getAllMonsters();

        //Assert
        assertNotNull(monsterList);
        assertEquals(2, monsterList.size());
        assertTrue(monsterList.contains(sampleMonster1));
        assertTrue(monsterList.contains(sampleMonster2));
    }

    @Test
    void testGetAllMonstersEmpty() {

        //Act
        List<Monster> monsterList = testDao.getAllMonsters();

        //Assert
        assertNotNull(monsterList);
        assertEquals(0, monsterList.size());
    }

    @Test
    void testUpdateMonster() {

        //Arrange
        Monster sampleMonster1 = getSampleMonster1();
        Monster sampleMonster2 = getSampleMonster2();
        testDao.addMonster(1, sampleMonster1);

        //Act
        testDao.updateMonster(1, sampleMonster2);
        Monster retrievedMonster = testDao.getMonster(1);

        //Assert
        assertEquals(sampleMonster2, retrievedMonster);
    }

    /**
     *  Replacing non-existent shouldn't throw an error, but nothing would happen, so can't get it still.
     */
    @Test
    void testUpdateNonExistentMonster() {

        //Arrange
        Monster sampleMonster1 = getSampleMonster1();

        //Act
        testDao.updateMonster(1, sampleMonster1);
        Monster retrievedMonster = testDao.getMonster(1);

        //Assert
        assertNull(retrievedMonster);
    }

    @Test
    void testRemoveMonster() {

        //Arrange
        Monster sampleMonster1 = getSampleMonster1();
        Monster sampleMonster2 = getSampleMonster2();
        testDao.addMonster(1, sampleMonster1);
        testDao.addMonster(2, sampleMonster2);

        //Act
        Monster removedMonster = testDao.removeMonster(1);
        List<Monster> monsterList = testDao.getAllMonsters();

        //Assert
        assertEquals(removedMonster, sampleMonster1);
        assertEquals(1, monsterList.size());
        assertTrue(monsterList.contains(sampleMonster2));
        assertFalse(monsterList.contains(sampleMonster1));
    }

    @Test
    void testRemoveNonExistentMonster() {

        //Arrange
        Monster sampleMonster1 = getSampleMonster1();
        Monster sampleMonster2 = getSampleMonster2();
        testDao.addMonster(1, sampleMonster1);
        testDao.addMonster(2, sampleMonster2);

        //Act
        Monster removedMonster = testDao.removeMonster(3);
        List<Monster> monsterList = testDao.getAllMonsters();

        //Assert
        assertNull(removedMonster);
        assertEquals(2, monsterList.size());
        assertTrue(monsterList.contains(sampleMonster2));
        assertTrue(monsterList.contains(sampleMonster1));
    }

}