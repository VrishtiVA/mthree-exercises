package com.mthree.academy.c458.vrishti.foundations.flowcontrol.fors;

public class ForByFor {
    public static void main(String[] args) {

        //Row
        for (int i = 0; i < 3; i++) {
            System.out.print("|");

            //Column
            for (int j = 0; j < 3; j++) {
                //Fill according to condition
                if (i == 1 && j == 1) {
                    System.out.print("###");
                } else if (i != 1 && j == 1) {
                    System.out.print("$$$");
                } else if (i == 1) { //Omit && j != 1 here since already covered.
                    System.out.print("@@@");
                } else {
                    System.out.print("***");
                }

                System.out.print("|");
            }

            //Start new row
            System.out.println();
        }

    }
}
