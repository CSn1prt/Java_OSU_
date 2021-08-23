import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class CoinChange2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private CoinChange2() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        int coinArray[] = new int[6];
        String denomArray[] = new String[6];
        out.println("Enter amount here: ");
        double x = in.nextDouble();

        int coints = (int) (100 * x);
        int dollars = coints / 100;
        int halfDollars = (coints - 100 * dollars) / 50;
        int quarters = (coints - 100 * dollars - 50 * halfDollars) / 25;
        int dimes = (coints - 100 * dollars - 50 * halfDollars - 25 * quarters)
                / 10;
        int nickels = (coints - 100 * dollars - 50 * halfDollars - 25 * quarters
                - 10 * dimes) / 5;
        int pennies = coints - 100 * dollars - 50 * halfDollars - 25 * quarters
                - 10 * dimes - 5 * nickels;

        for (int i = 0; i < coinArray.length; i++) {
            out.println("");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
