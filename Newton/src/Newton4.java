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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
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

        out.print("Enter margin of error: ");
        error = in.nextDouble();
        out.print("Type in a number you want to calculate a square root: ");
        x = in.nextDouble();

        while (x >= 0) {

            out.println(sqrt(x, error));
            out.print("Type in a number you want to calculate a square root: ");
            x = in.nextDouble();

        }

        while (x < 0) {
            out.println(
                    "Invalid input. Negative number cannot be a vaild response.");
        }

        out.print("End of the sequence");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
