package com.tsg.unittesting.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExerciseCTest {

    /* Conditions:
     * You only go walking if it's light outside, or if you have a flashlight.
     * Also, only if it's not raining, or if you have an umbrella.
     * And if it's not too hot (more than 95 degrees) and not too cold (less than 50 degrees).
     *
     * Parameters:
     * @param isDark - 3 Cases (Light, Dark, Dark w/Flashlight)
     *  @param haveFlashlight
     * @param isRaining - 3 Cases (Dry, Raining, Raining w/Umbrella)
     *  @param haveUmbrella
     * @param degreesFarenheit - 3 + 2 Boundary Cases (Ideal Temp, LB Temp, UB Temp, Too Hot, Too Cold)
     *
     * ========== Test Cases ==========
     *
     * //Ideal case (covers normal for all too)
     * light, rain, dry, idea temp: goWalky( false, false, false, false, 75  ) -> true
     *
     * //Dark cases
     * dark, no flash, rain, umbrella, ideal temp: goWalky( true, false, true, true, 75  ) ->  false
     * dark, flash, rain, umbrella, ideal temp: goWalky( true, true, true, true, 75  ) -> true
     *
     * //Rain cases
     * light, rain, no umbrella, ideal temp: goWalky( false, false, true, false, 75  ) -> false
     * light, rain, umbrella, ideal temp: goWalky( false, false, true, true, 75  ) -> true
     *
     * //Temperature cases
     * light, dry, too hot: goWalky( false, true, false, false, 96  ) -> false
     * light, dry, ideal up temp: goWalky( false, true, false, false, 95  ) -> true
     * light, dry, ideal lb temp: goWalky( false, true, false, false, 50  ) ->  true
     * light, dry, cold: goWalky( false, false, false, false, 49  ) ->  false
     */

    @Test
    void testGoWalkyMostIdeal() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky( false, false, false, false, 75);
        //Assert
        assertTrue(result);
    }

    @Test
    void testGoWalkyDark() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(true, false, true, true, 75);
        //Assert
        assertFalse(result);
    }

    @Test
    void testGoWalkyDarkFlashlight() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(true, true, true, true, 75);
        //Assert
        assertTrue(result);
    }

    @Test
    void testGoWalkyRain() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(false, false, true, false, 75);
        //Assert
        assertFalse(result);
    }

    @Test
    void testGoWalkyRainUmbrella() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(false, false, true, true, 75);
        //Assert
        assertTrue(result);
    }

    @Test
    void testGoWalkyTooHot() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(false, true, false, false, 96);
        //Assert
        assertFalse(result);
    }

    @Test
    void testGoWalkyIdealTemperatureUpperBound() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(false, true, false, false, 95);
        //Assert
        assertTrue(result);
    }

    @Test
    void testGoWalkyIdealTemperatureLowerBound() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(false, true, false, false, 50);
        //Assert
        assertTrue(result);
    }

    @Test
    void testGoWalkyTooCold() {

        //Arrange & Act
        boolean result = LogicExerciseC.goWalky(false, false, false, false, 49);
        //Assert
        assertFalse(result);
    }
}