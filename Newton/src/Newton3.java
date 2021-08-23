import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Seunghyun Nam
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number (and 0) to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double l) {

        //Initial guess.
        double temp = x;

        double root;

        if (x != 0) {
            while ((Math.abs(temp * temp - x)) / x > l) {
                temp = (temp + (x / temp)) / 2;
            }
        }

        return temp;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        String response = "";
        double x, error;

        out.print("Do you want to calculate a square root Y/N: ");
        response = in.nextLine();

        while (response.toLowerCase().equals("y")) {

            //Answers that are not in Y/N cannot be accepted.
            //If the response is "y", then the program should proceed;
            //if it is anything else, then the program should quit.
            out.print("Enter margin of error: ");
            error = in.nextDouble();
            out.print("Enter number here: ");
            x = in.nextDouble();

            out.println(sqrt(x, error));
            out.print("Do you want to calculate another square root Y/N: ");
            response = in.nextLine();

        }

        out.print("End of the sequence");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
