import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class CoinChange1 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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

        out.println("" + dollars + " dollar " + halfDollars + " half dollar "
                + quarters + " quarter " + dimes + " dimes " + nickels
                + " nickels" + pennies + " cents ");

        in.close();
        out.close();
    }

}
