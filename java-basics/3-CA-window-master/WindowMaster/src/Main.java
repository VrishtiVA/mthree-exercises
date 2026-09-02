import java.util.Scanner;

public class Main {
    /**
     * Aim: Calculate total cost for home replacement windows.
     * Pre-Refactor Plan: Window Master Plan/Thoughts:
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
     * @param args
     */
    public static void main(String[] args) {

        //Desired inputs
        int quantity;
        float height;
        float width;
        float glassCost;
        float trimCost;

        //Desired outputs
        float area;
        float perimeter;
        float cost;

        //Declare and initialize useful scanner object
        Scanner scanner = new Scanner(System.in);

        //Demand and parse user inputs.
        quantity = validInt(scanner, "How many windows do you need?: ");
        height = validFloat(scanner, "What is the height of your window? (in feet): ");
        width = validFloat(scanner, "What is the width of your window? (in feet):");
        glassCost = validFloat(scanner, "What is the glass cost per square foot?: ");
        trimCost = validFloat(scanner, "What is the trim cost per linear foot?: ");

        //Calculate area and perimeter
        area = height * width;
        perimeter = 2 * (height + width); //(2h+2w) = 2(h+w)

        //Calculate total cost of all windows, and round to 2dp.
        cost = ((area * glassCost) + (perimeter * trimCost)) * quantity;
        cost = Math.round(cost * 100)/100f;

        //Output nice summary
        System.out.println(
            "\n---------- Results ----------" +
            "\nQuantity: " + quantity +
            "\nArea per Window: " + area +
            "\nPerimeter per Window: " + perimeter +
            "\nTotal Cost: $" + cost +
            "\n-----------------------------"
        );

        //Close scanner to tidy up.
        scanner.close();

    }

    /**
     * Method to ask question to obtain a valid float.
     * Using exception handling.
     * @param question The relevant question to ask for obtaining the float.
     * @return A valid float.
     */
    private static float validFloat(Scanner scanner, String question) {

        boolean isValid = false;
        float result = 0f;

        //Ask question at least once until valid response.
        do {
            //Ask relevant question
            System.out.println(question);

            try {
                //Try take input and parse
                result = Float.parseFloat(scanner.nextLine());

                //Ensure number is positive (example restriction)
                if (result < 0) {
                    throw new NumberFormatException();
                }

                //If reached here, must be valid
                isValid = true;

            } catch (NumberFormatException ex) {
                //If failed somewhere along the way, it is invalid so retry.
                System.out.println("Invalid input, please try again.");
            }

        } while (!isValid);

        return result;
    }

    /**
     * Method to ask question to obtain a valid int.
     * Using exception handling.
     * @param question The relevant question to ask for obtaining the int.
     * @return A valid int.
     */
    private static int validInt(Scanner scanner, String question) {

        boolean isValid = false;
        int result = 0;

        //Ask question at least once until valid response.
        do {
            //Ask relevant question
            System.out.println(question);

            try {
                //Try take input and parse
                result = Integer.parseInt(scanner.nextLine());

                //Ensure number is positive (example restriction)
                if (result < 0) {
                    throw new NumberFormatException();
                }

                //If reached here, must be valid
                isValid = true;

            } catch (NumberFormatException ex) {
                //If failed somewhere along the way, it is invalid so retry.
                System.out.println("Invalid input, please try again.");
            }

        } while (!isValid);

        return result;
    }
}
