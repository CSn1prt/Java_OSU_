import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author 5Yuki (Seunghyun Nam)
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {

    }

    /**
     * Generates the pairs of terms and definitions (Strings) in the given
     * {@code file} into the given {@code Map}. Also generates a Queue of all
     * the terms in no particular order into the given {@code Queue}.
     *
     * @param file
     *            the given {@code file}
     * @param strMap
     *            the {@code Map} to be replaced
     * @param q
     *            the {@code Queue} to be replaced
     * @replaces {@code strSet}
     * @replaces {@code q}
     * @ensures <pre>
     * {@code strMap = elements(file)}
     * </pre>
     */
    private static void setText(SimpleReader file, Map<String, String> strMap,
            Sequence<String> q) {

        int count = 0;
        String term = "";
        String defHolder = "";
        String finalDef = "";

        while (!file.atEOS()) {
            term = file.nextLine();
            q.add(count, term);
            count++;
            defHolder = "Hello";
            finalDef = "";
            while (!defHolder.equals("")) {
                defHolder = file.nextLine();
                finalDef = finalDef.concat(defHolder);

            }
            strMap.add(term, finalDef);
        }
    }

    /**
     * Alphabetizes the given {@code Sequence}.
     *
     * @param q
     *            the given {@code Sequence}
     * @replaces {@code q}
     */
    private static void organize(Sequence<String> q) {
        Sequence<String> q2 = new Sequence1L<String>();
        q2.transferFrom(q);
        String next = "";
        int countPlus = 0;
        while (q2.length() != 0) {
            int count = 0;
            int nextCount = 0;
            while (count < q2.length() - 1) {
                next = q2.entry(nextCount);
                if (next.compareTo(q2.entry(count + 1)) > 0) {
                    nextCount = count + 1;
                }
                count++;
            }
            q.add(countPlus, q2.entry(nextCount));
            q2.remove(nextCount);
            countPlus++;
        }
    }

    /**
     * Updates given {@code Map} with the utilization of the given word
     * {@code Sequence}.
     *
     * @param m
     *            the given {@code Map}
     * @param s
     *            the {@code Sequence} to be used to update m
     * @replaces {@code m}
     */
    private static void createMap(Map<String, String> m, Sequence<String> s) {
        Map<String, String> copy = new Map1L<String, String>();
        int count = 0;
        while (count < s.length()) {
            copy.add(s.entry(count), m.value(s.entry(count)));
            count++;
        }
        m.transferFrom(copy);
    }

    /**
     * Outputs the opening tags in the generated HTML file.
     *
     * @param terms
     *            the Sequence of terms
     * @param out
     *            the output stream
     * @updates {@code out.content}
     * @ensures <pre>
     * {@code out.content = #out.content * [the HTML opening tags]}
     * </pre>
     */
    private static void outputIndex(Queue<String> terms, String folderpath) {

        assert terms != null : "Violation of: terms is not null";

        SimpleWriter file = new SimpleWriter1L(folderpath + "/index.html");
        // Output

        file.println("<?xml version=\"1.0\" encoding=\"EUC-KR\" ?>");
        file.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0"
                + " Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        file.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        file.println("<head>");
        file.println("<meta http-equiv=\"Content-Type\" content=\"text/html;"
                + " charset=EUC-KR\" />");
        file.println("<title>Glossary</title>");
        file.println("</head>");
        file.println("<body>");
        file.println("<h2>Glossary</h2>");
        file.println("<hr>");
        file.println("<h3>Index</h3>");
        file.println("<ul>");
        // Printing HTML code

        for (int j = 0; j < terms.length(); j++) {
            file.println("<li>");

            String name = terms.front();

            SimpleWriter fileOut = new SimpleWriter1L(
                    folderpath + "/" + name + ".html");
            // Creating HTML pages for each word

            file.println("<a href=\"" + name + ".html\">" + name + "</a>");

            file.println("</li>");

            terms.rotate(1);

            fileOut.close();
        }

        file.println("</ul>");
        file.println("</body>");
        file.println("</html>");
        // Closing the HTML index code.

        file.close();

    }

    /**
     * Outputs the opening tags in the generated HTML file.
     *
     * @param m
     *            the Map of terms and definitions
     * @param terms
     *            the Sequence of terms
     * @param foldername
     *            the name of the folder the html files are output to
     * @updates {@code out.content}
     * @ensures <pre>
     * {@code out.content = #out.content * [the HTML tags]}
     * </pre>
     */
    private static void outputDefinitions(Map<String, String> m,
            Sequence<String> terms, String foldername) {

        assert terms != null : "Violation of: terms is not null";

        int count = 0;
        String filename = "";
        while (count < terms.length()) {
            filename = terms.entry(count);
            SimpleWriter file = new SimpleWriter1L(
                    foldername + "/" + filename + ".html");
            file.println("<?xml version='1.0' encoding\"EUC-KR\" ?>");
            file.println(
                    "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'"
                            + " 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
            file.println("<head>");
            file.println("<meta http-equiv='Content-Type'"
                    + " content='text/html; charset=ISO-8859-1' />");
            file.println("<body>");
            file.println("<h2><b><i><font color=\"red\">" + terms.entry(count)
                    + "</font></i></b></h2>");
            file.println("<blockquote>" + m.value(terms.entry(count))
                    + "</blockquote>");
            file.println("<p>Return to <a href=\"index.html\">index</a></p>");
            file.println("</body>");
            file.println("</html>");
            file.close();
            count++;

        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires <pre>
     * {@code 0 <= position < |text|}
     * </pre>
     * @ensures <pre>
     * {@code nextWordOrSeparator =
     *   text[ position .. position + |nextWordOrSeparator| )  and
     * if elements(text[ position .. position + 1 )) intersection separators = {}
     * then
     *   elements(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    elements(text[ position .. position + |nextWordOrSeparator| + 1 ))
     *      intersection separators /= {})
     * else
     *   elements(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    elements(text[ position .. position + |nextWordOrSeparator| + 1 ))
     *      is not subset of separators)}
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int count = 0;
        char pieceReturned = 'x';
        String stringReturned = "";
        if (separators.contains(text.charAt(position))) {
            while (count < text.substring(position, text.length()).length()) {
                pieceReturned = text.charAt(position + count);
                if (separators.contains(text.charAt(position + count))) {
                    stringReturned += pieceReturned;
                    count++;
                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
            count = 0;
        } else {
            while (count < text.substring(position, text.length()).length()) {
                pieceReturned = text.charAt(position + count);
                if (!separators.contains(text.charAt(position + count))) {
                    stringReturned += pieceReturned;
                    count++;
                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
            count = 0;
        }
        return stringReturned;

    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        char setPiece = ' ';
        charSet.clear();
        for (int i = 0; i < str.length(); i++) {
            if (!charSet.contains(str.charAt(i))) {
                setPiece = str.charAt(i);
                charSet.add(setPiece);
            }
        }

    }

    /**
     * Updates given {@code Map} values with html hyperlink tags included next
     * to terms appearing in definitions.
     *
     * @param m
     *            the given {@code Map}
     * @param s
     *            the {@code Sequence} to be used to update m
     * @param separatorSet
     *            the {@code Set} to be used to separate words from each other
     * @restores {@code s}
     * @replaces {@code m}
     */
    private static void fullLine(Map<String, String> m, Sequence<String> s,
            Set<Character> separatorSet) {
        Map<String, String> copy = new Map1L<String, String>();
        Set<String> terms = new Set1L<String>();
        String testCopy = "";
        int position = 0;
        int count = 0;
        int size = s.length();
        while (terms.size() < size) {
            testCopy = s.remove(count);
            terms.add(testCopy);
            s.add(count, testCopy);
            count++;
        }
        count = 0;
        testCopy = "";
        while (count < terms.size()) {
            position = 0;
            testCopy = "";
            String testStr = m.value(s.entry(count));
            while (position < testStr.length()) {
                String link = nextWordOrSeparator(testStr, position,
                        separatorSet);
                if (separatorSet.contains(link.charAt(0))) {
                    testCopy += link;

                } else if (terms.contains(link)) {
                    testCopy = testCopy + ("<a href=\"" + link + ".html\">"
                            + link + "</a>");

                } else {
                    testCopy = testCopy.concat(link);
                }
                position += link.length();
            }
            copy.add(s.entry(count), testCopy);
            count++;
        }
        m.transferFrom(copy);

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
        //Asking for the input file information and output file information
        out.print("Type in the name of input file here: ");
        SimpleReader file = new SimpleReader1L(in.nextLine());
        out.println();
        // For example, data\terms.txt
        out.print("Type in the name of output file here"
                + " (ex. data, lib, doc, etc): ");
        String folder = in.nextLine();

        Map<String, String> wordBox = new Map1L<String, String>();
        Sequence<String> terms = new Sequence1L<String>();
        Queue<String> words = new Queue1L<>();

        setText(file, wordBox, terms);

        //organizing the terms in alphabetical order
        organize(terms);

        createMap(wordBox, terms);

        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<Character>();
        generateElements(separatorStr, separatorSet);
        fullLine(wordBox, terms, separatorSet);

        //creating index page
        outputIndex(words, folder);

        //creating definition pages
        outputDefinitions(wordBox, terms, folder);

        out.println("Check the file you created.");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
