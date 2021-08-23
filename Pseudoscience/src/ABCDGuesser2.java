import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * project description: Asks the user what constant μ should be approximated,
 * and then asks in turn for each of the four personal numbers w, x, y, and z.
 * The program should then calculate and report the values of the exponents a,
 * b, c, and d that bring the de Jager formula as close as possible to μ, as
 * well as the value of the formula w^a*x^b*y^c*z^d and the relative error of
 * the approximation to the nearest hundredth of one percent
 *
 * @author Seunghyun Nam
 */
public final class ABCDGuesser2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String response = "";
        double foo = 0.0;
        out.print("Enter number: ");
        response = in.nextLine();

        do {
            while (!FormatChecker.canParseDouble(response)) {
                out.println("Invalid input. Please enter a number: ");
                response = in.nextLine();
            }
            while (Double.parseDouble(response) <= 0) {
                out.println(
                        "Should be a positive real number.\nEnter another: ");
                response = in.nextLine();
            }
            foo = Double.parseDouble(response);
            return foo;

        } while (FormatChecker.canParseDouble(response));
        /*
         * If the input's data type is not "double", it will ask for new input
         * until the input is in proper data type.
         */

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String response = "";
        double foo = 0.0;
        out.print("Enter number: ");
        response = in.nextLine();

        do {
            while (!FormatChecker.canParseDouble(response)) {
                out.println("Invalid input. Please enter a number: ");
                response = in.nextLine();
            }
            while (Double.parseDouble(response) <= 0) {
                out.println(
                        "Should be a positive real number. (Enter a new one): ");
                response = in.nextLine();
            }
            while (Double.parseDouble(response) == 1.0) {
                out.println("It should be a positive real number not equal"
                        + " to 1.0 (Enter a new one): ");
                response = in.nextLine();
            }
            foo = Double.parseDouble(response);
            return foo;

        } while (FormatChecker.canParseDouble(response));
        /*
         * If the input's data type is not "double" or if it's 1, it will ask
         * for new input until the input is in proper input.
         */

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
        double mu;
        double w, x, y, z;
        final double[] numTemplate = { -5.0, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
                -1.0 / 4, 0.0, 1.0 / 4, 1.0 / 3, 0.5, 1, 2, 3, 4, 5 };
        double estimateFinal = 0, estimateNow = 0;
        double a, b, c, d;
        String exp = "";

        out.println("Enter a number for Jagar Formula's Greek letter Mu: ");
        mu = getPositiveDouble(in, out);
        out.println("Your first personal number: ");
        w = getPositiveDoubleNotOne(in, out);
        out.println("Your second personal number: ");
        x = getPositiveDoubleNotOne(in, out);
        out.println("Your third personal number: ");
        y = getPositiveDoubleNotOne(in, out);
        out.println("Your fourth personal number: ");
        z = getPositiveDoubleNotOne(in, out);

        for (int i = 0; i < numTemplate.length; i++) {
            a = numTemplate[i];

            for (int j = 0; j < numTemplate.length; j++) {
                b = numTemplate[j];
                for (int k = 0; k < numTemplate.length; k++) {
                    c = numTemplate[k];
                    for (int l = 0; l < numTemplate.length; l++) {
                        d = numTemplate[l];
                        estimateNow = Math.pow(w, a) * Math.pow(x, b)
                                * Math.pow(y, c) * Math.pow(z, d);
                        if (Math.abs(mu - estimateNow) < Math
                                .abs(mu - estimateFinal)) {
                            estimateFinal = estimateNow;
                            exp = " " + a + " " + b + " " + c + " " + d;
                        }
                    }
                }

            }
        }
        /*
         * The loop finds appropriate value(s) for exponent a,b,c,d among
         * numbers -5.0, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3, -1.0 / 4, 0.0, 1.0
         * / 4, 1.0 / 3, 0.5, 1, 2, 3, 4, 5 (from numTemplate), by putting those
         * values one by one, until (mu - estimateNow) / mu < (mu -
         * estimateFinal)) / mu is true.
         */

        out.println(
                "The value calculated (within a fraction of 1% relative error.): "
                        + estimateFinal);
        out.println("The value of the exponents: " + exp);
        //print final results

        /*
         * Close input and output streams
         */
        in.close();
        out.close();

    }

}
