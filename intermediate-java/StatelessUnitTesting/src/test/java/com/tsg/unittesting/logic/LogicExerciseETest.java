package com.tsg.unittesting.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExerciseETest {

    /*
     * Conditions:
     * If it doesn't fall within correct bands,
     *      return "Unknown" instead.
     * If it falls exactly within a band transition,
     *      return a compound color, with the longer wavelength color first.
     *
     * 	Color	Wavelength	Frequency	Photon energy
     * 	Violet	380–450 nm	668–789 THz	2.75–3.26 eV
     * 	Blue	450–495 nm	606–668 THz	2.50–2.75 eV
     * 	Green	495–570 nm	526–606 THz	2.17–2.50 eV
     * 	Yellow	570–590 nm	508–526 THz	2.10–2.17 eV
     * 	Orange	590–620 nm	484–508 THz	2.00–2.10 eV
     * 	Red	    620–750 nm	400–484 THz	1.65–2.00 eV
     *
     * ========== Test Cases ==========
     * Colors:
     * - whatColor( 449, 670, 3.00 ) ->  "Violet"
     * - whatColor( 451, 607, 2.51 ) ->  "Blue"
     * - whatColor( 496, 530, 2.18 ) ->  "Green"
     * - whatColor( 575, 510, 2.15 ) ->  "Yellow"
     * - whatColor( 590, 508, 2.05 ) ->  "Orange"
     * - whatColor( 640, 450, 1.7 ) ->  "Red"
     *
     * Compound: (When exact transition, longer wavelength first)
     * - whatColor( 570, 526, 2.17 ) ->  "Yellow-Green"
     * - whatColor( 590, 508, 2.10 ) ->  "Orange-Yellow"
     *
     * Unknown:
     * - whatColor( 621, 475, 16.5 ) ->  "Unknown"
     */

    @Test
    void testWhatColorViolet() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(449, 670, 3.00);
        //Assert
        assertEquals("Violet", result);
    }

    @Test
    void testWhatColorBlue() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(451, 607, 2.51);
        //Assert
        assertEquals("Blue", result);
    }

    @Test
    void testWhatColorGreen() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(496, 530, 2.18);
        //Assert
        assertEquals("Green", result);
    }

    @Test
    void testWhatColorYellow() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(575, 510, 2.15);
        //Assert
        assertEquals("Yellow", result);
    }

    @Test
    void testWhatColorOrange() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(590, 508, 2.05);
        //Assert
        assertEquals("Orange", result);
    }

    @Test
    void testWhatColorRed() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(640, 450, 1.7);
        //Assert
        assertEquals("Red", result);
    }

    @Test
    void testWhatColorCompoundYellowGreen() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(570, 526, 2.17);
        //Assert
        assertEquals("Yellow-Green", result);
    }

    @Test
    void testWhatColorCompoundOrangeYellow() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(590, 508, 2.10);
        //Assert
        assertEquals("Orange-Yellow", result);
    }

    @Test
    void testWhatColorUnknown() {
        //Arrange & Act
        String result = LogicExerciseE.whatColor(621, 475, 16.5);
        //Assert
        assertEquals("Unknown", result);
    }

}