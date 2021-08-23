import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {

        boolean b;
        if ((xCoord - 1.0) * (xCoord - 1.0)
                + (yCoord - 1.0) * (yCoord - 1.0) > 1.0) {
            b = false;
        } else {
            b = true;
        }

        return b;

    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {

        int count = 0;
        double xPts, yPts;

        Random rnd = new Random1L();

        xPts = 2.0 * rnd.nextDouble();
        yPts = 2.0 * rnd.nextDouble();

        for (int i = 0; i < n; i++) {
            if (pointIsInCircle(xPts, yPts)) {
                count++;
            }
        }

        return count;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsTotal = 0, ptsInCircle = 0;
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random1L();
        /*
         * Generate points and count how many fall in [0.0,0.5) interval
         */

        while (ptsTotal < n) {
            /*
             * Generate pseudo-random number in [0.0,1.0) interval
             */
            double x = 2.0 * rnd.nextDouble();
            double y = 2.0 * rnd.nextDouble();
            /*
             * Increment total number of generated points
             */
            ptsTotal++;
            /*
             * Check if point is in a circle whose center is at (1.0, 1.0) with
             * radius of 1.0 and increment counter if it is
             */
            if (pointIsInCircle(x, y)) {
                ptsInCircle++;
            }
        }
        double pi = (4 * ptsInCircle / ptsTotal);

        output.println("The value of pi(\u03C0) is approximately : " + pi);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}