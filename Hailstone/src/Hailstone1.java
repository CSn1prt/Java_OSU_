import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class Hailstone1 {

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int x = n;
        int count = 1;
        final int k = 3;
        out.println(x);
        while (x != 2) {
            if (x % 2 == 0) {

                x = x / 2;
                out.println(x);
            }
            if (x % 2 == 1) {

                x = k * x + 1;
                out.println(x);
            }
            count++;

        }
        out.println("1");
    }

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Hailstone1() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("Hello World!");
        generateSeries(84, out);
        out.close();
    }

}
