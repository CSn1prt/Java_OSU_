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
public final class ProgramWithIOAndStaticMethod {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    private static String reversedString(String s) {
        String rs = "";
        for (int i = 0; i < s.length(); i++) {
            rs = s.charAt(i) + rs;
        }
        return rs;
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
        out.println("Type in string to be reversed: ");
        String s = "";
        s = in.nextLine();
        out.println("Reversed string: " + reversedString(s));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
