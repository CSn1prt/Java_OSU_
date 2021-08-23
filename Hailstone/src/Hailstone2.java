import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone2() {
    }

    private static void generateSeries(int n, SimpleWriter out) {
        int x = n;
        int count = 1;
        out.println(x);
        while (x != 2) {
            count++;
            if (x % 2 == 0) {

                x = x / 2;
                out.println(x);
            }
            if (x % 2 == 1) {

                x = 3 * x + 1;
                out.println(x);
            }
            out.println("No. : " + count);

        }
        out.println("1");
        out.println("The lenght of this series is: " + count);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        generateSeries(84, out);
        out.close();
    }

}
