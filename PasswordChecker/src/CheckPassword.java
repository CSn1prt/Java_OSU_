import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
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
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        boolean b1 = false, b2 = false, b3 = false;
        int ascii;

        for (int i = 0; i < s.length(); i++) {

            ascii = s.charAt(i);
            if (ascii > 32 && ascii < 65) {
                b1 = true;
                break;
            } else {
                b1 = false;
            }

        }

        for (int j = 0; j < s.length(); j++) {
            ascii = s.charAt(j);
            if (ascii > 47 && ascii < 58) {
                b2 = true;
                break;
            } else {
                b2 = false;
            }
        }
        /*
         * for (int k = 0; k < s.length(); k++) { ascii = s.charAt(k); if
         * (Character.isUpperCase(s.charAt(0))) { b3 = true; break; } else { b3
         * = false; } }
         */

        if (b1 && b2 && containsUpperCaseLetter(s)) {
            out.println("valid password");
        } else {
            out.println("invalid password");
        }

    }

    /**
     * Checks if the given String contains an upper case letter. ok
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {

        boolean b = false, isCapital = Character.isUpperCase(s.charAt(0));

        for (int index = 1; index < s.length(); index++) {
            isCapital = Character.isUpperCase(s.charAt(index));
            if (isCapital) {
                b = true;
            } else {
                b = false;
            }
        }

        return b;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        myMethod();
        out.println(containsUpperCaseLetter("$hello5K"));
        checkPassword("$404NotFound", out);
        out.println(Character.isUpperCase('a'));

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
