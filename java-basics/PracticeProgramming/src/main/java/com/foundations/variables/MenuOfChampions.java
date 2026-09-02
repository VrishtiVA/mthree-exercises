package com.foundations.variables;

public class MenuOfChampions {
    public static void main(String[] args) {

        // Declare variables
        String item1, item2, item3;
        float price1, price2, price3;

        // Initialise variables
        item1 = "Chicken";
        price1 = 9.99f;
        item2 = "Lamb";
        price2 = 11.99f;
        item3 = "Feijao";
        price3 = 3.99f;

        // Beautiful ascii art menu header
        System.out.println(
           "                                                               .---.\n" +
           "                                                              /  .  \\\n" +
           "                                                             |\\_/|   |\n" +
           "                                                             |   |  /|\n" +
           "  .----------------------------------------------------------------' |\n" +
           " /  .-.                                                              |\n" +
           "|  /   \\                                                             |\n" +
           "| |\\_.  |             Welcome to Restaurant Night Vale               |\n" +
           "|\\|  | /|                    Today's Menu Is...                      |\n" +
           "| `---' |                                                            |\n" +
           "|       |                                                           /\n" +
           "|       |----------------------------------------------------------'\n" +
           "\\       |\n" +
           " \\     /\n" +
           "  `---'\n"
//           "Thank you for visiting https://asciiart.website/\n" +
//           "This ASCII pic can be found at\n" +
//           "https://asciiart.website/art/975"
        );

        //Display Menu
        System.out.printf("$%f.2\t %s\n", price1, item1);
        System.out.printf("$%f.2\t %s\n", price2, item2);
        System.out.printf("$%f.2\t %s\n", price3, item3);

    }
}
