import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Seunghyun NAM
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        // TODO - fill in body
        NaturalNumber about = new NaturalNumber2(2);
        NaturalNumber lowGuess = new NaturalNumber2(0);
        NaturalNumber highGuess = new NaturalNumber2(n);
        NaturalNumber middle = new NaturalNumber2(0);
        highGuess.increment();

        middle.copyFrom(highGuess);
        middle.subtract(lowGuess);
        middle.divide(about);

        //lowGuess = 0, highGuess = n + 1, middle = (highGuess - lowGuess) / 2

        NaturalNumber lowGuessCopy = new NaturalNumber2(lowGuess);
        lowGuess.increment();
        //For variables inside while() bracket
        //inside the bracket: highGuess > lowGuess + 1
        while (highGuess.compareTo(lowGuessCopy) > 0) {
            //Checks if highGuess > lowGuess + 1 or not
            NaturalNumber middleCopy = new NaturalNumber2(middle);
            middleCopy.power(r);
            // middleCopy = middle ^ r (middle powered by r)
            if (n.compareTo(middleCopy) >= 0) {
                lowGuess.copyFrom(middle);
            } else {
                highGuess.copyFrom(middle);
            }
            //Checks if n >= middle ^ r (middle powered by r)
            middle.copyFrom(highGuess);
            middle.add(lowGuess);
            middle.divide(about);
            lowGuessCopy.copyFrom(lowGuess);
            lowGuessCopy.increment();
            //Sets value of middle = (highGuess + lowGuess) / 2
            //& Sets new value for lowGuessCopy, which is lowGuess + 1
        }
        n.transferFrom(lowGuess);
        //Sets the final result

    }

    private static String mystery1(String s1) {
        s1 = s1.substring(0, 2);
        return s1;
    }

    private static void mystery2(NaturalNumber n1) {
        n1.decrement();
        n1 = new NaturalNumber1L(5);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        NaturalNumber n1 = new NaturalNumber2(7);
        NaturalNumber n2 = new NaturalNumber2(2);
        NaturalNumber n3 = new NaturalNumber2(3);
        NaturalNumber r = n1.divide(n2);
        if (!r.isZero()) {
            n1.multiply(n2);
            n1.add(r);
            n1.multiply(n3);
            n1.increment();

        }
        out.println(n1);
        NaturalNumber n4 = new NaturalNumber2(25);
        NaturalNumber hund = new NaturalNumber2(100);
        NaturalNumber num = n4;
        while (n4.compareTo(hund) < 0) {
            n4.multiply(new NaturalNumber2(2));
            if (n4.compareTo(num) < 0) {
                num = n4;
            }
        }
        out.println(num);

        NaturalNumber nn1 = new NaturalNumber2(2);
        NaturalNumber nn2 = new NaturalNumber2(6);
        NaturalNumber nn3 = new NaturalNumber2(4);

        String s = "GoBUCKS";
        s = mystery1(s) + s;
        mystery2(nn1);
        nn2.add(nn2.divide(nn3));

        out.println();
        out.println("s: " + s);
        out.println("nn1 : " + nn1);
        out.println("nn2: " + nn2);

        out.close();
    }

}