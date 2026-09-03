package com.mthree.academy.c458.vrishti.foundations.flowcontrol.arrays;

public class FruitSalad {
    public static void main(String[] args) {

        // Observed that all the fruit is different
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        String[] fruitSalad = new String[12];

        // Code Recipe for fruit salad should go here!

        int saladPointer = 0;
        int fruitLimit = 12;
        int appleLimit = 3;
        int orangeLimit = 2;

        for (String f : fruit) {

            //Is a berry then add.
            if (f.contains("berry") || f.contains("Berry")) {
                fruitSalad[saladPointer++] = f;
                fruitLimit--;
            } else if (appleLimit > 0 && (f.contains("apple") || f.contains("Apple"))) {
                //Otherwise check if apple
                fruitSalad[saladPointer++] = f;
                fruitLimit--;
                appleLimit--;
            } else if (orangeLimit > 0 && (f.contains("Orange"))) {
                //Otherwise check if orange
                fruitSalad[saladPointer++] = f;
                fruitLimit--;
                orangeLimit--;
            }

            //Check if done early
            if (saladPointer == 12) break;
        }

        //Output fruit salad
        System.out.println("Fruit Salad Content:");
        for (String f : fruitSalad) {

            //Break early if smaller salad.
            if (f == null) break;

            //Output fruit
            System.out.println("- " + f);
        }

    }
}
