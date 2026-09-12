package com.tsg.unittesting.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExerciseATest {

    /* ========== Test Plan ==========

    Test Cases:
     - Acquaintance: friendlyGreeting( "Goofus" , false ) ->   "hi"
     - Friend: friendlyGreeting( "Gallant" , true ) ->   "Hello, Gallant!"
     - Stranger: friendlyGreeting( null , false ) ->   "..."
     - Nameless: friendlyGreeting( null , true ) ->   "..."

     */

    @Test
    void testFriendlyGreetingAcquaintance() {

        //Act
        String result = LogicExerciseA.friendlyGreeting("Goofus" , false);
        //Assert
        assertEquals("hi", result, "A named non-friend should only receive a shy greeting.");
    }

    @Test
    void testFriendlyGreetingFriend() {

        //Act
        String result = LogicExerciseA.friendlyGreeting("Gallant" , true);
        //Assert
        assertEquals("Hello, Gallant!", result, "A named friend should receive a hearty hello.");
    }

    @Test
    void testFriendlyGreetingStranger() {

        //Act
        String result = LogicExerciseA.friendlyGreeting(null , false);
        //Assert
        assertEquals("...", result, "Don't greet strangers.");
    }

    @Test
    void testFriendlyGreetingNameless() {

        //Act
        String result = LogicExerciseA.friendlyGreeting(null , true);
        //Assert
        assertEquals("...", result, "You don't know how to greet the nameless.");
    }
}