import java.util.Scanner;

public class Main {
    /**
     * Aim: Calculate total cost for home replacement windows.
     * Window Master Plan/Thoughts:
     * 1. Ask for height and width
     *      Need Scanner for user input and parsing.
     *      String and float variables for each needed.
     *      Float since can be decimal.
     * 2. Calculate area and perimeter
     *      Only 1 window so function overboard, just do maths.
     *      Since also need to display, better to store each in variables too.
     * 3. Calculate total cost of window
     *      Glass + Trim
     *      - area * 3.5 = cost of glass
     *      - perimeter * 2.25 = cost of trim
     * 4. Display breakdown: area, perimeter, cost.
     *
     * Questions:
     * - Can you cut the glass or glass will be custom-made?
     *      Cut glass will look weird, think minecraft it needs to be custom.
     *      So lets price based on sand and just do maths.
     *
     * Note:
     * I'm lazy to exception handle at the moment but will do that later on when higher stake.
     *
     * @param args
     */
    public static void main(String[] args) {

        //Desired inputs
        String stringHeight;
        String stringWidth;

        //Desired outputs
        float area;
        float perimeter;
        float cost;

        //Declare and initialize useful scanner object
        Scanner scanner = new Scanner(System.in);

        //Demand height of window from user
        System.out.println("What is the height of your window? (in feet): ");
        stringHeight = scanner.nextLine();

        //Demand width of window from user
        System.out.println("What is the width of your window? (in feet): ");
        stringWidth = scanner.nextLine();

        //Parse height and width provided
        float height = Float.parseFloat(stringHeight);
        float width = Float.parseFloat(stringWidth);

        //Calculate area and perimeter
        area = height * width;
        perimeter = 2 * (height + width); //(2h+2w) = 2(h+w)

        //Calculate total cost of window, and round to 2dp.
        cost = (area * 3.5f) + (perimeter * 2.25f);
        cost = Math.round(cost * 100)/100f;

        //Output nice summary
        System.out.println(
            "\nArea: " + area +
            "\nPerimeter: " + perimeter +
            "\nTotal Cost: $" + cost
        );

    }
}
