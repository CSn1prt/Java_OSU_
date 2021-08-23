
/**
 * Put a short phrase describing the program here.
 *
 * @author Seunghyun Nam
 *
 */
public final class Template1 {

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        String str = "([)]";
        String[] arrOfStr = str.split("\\)");

        for (String a : arrOfStr) {
            System.out.println(a);
        }

    }

}
