import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * Counts how many times each word appeared in the text file.
 *
 * @author Seunghyun Nam, Group17
 *
 */
public final class TagCloud {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloud() {
    }

    /**
     * Maximum value of font size.
     */
    private static final int MAX_FONT_SIZE = 37;
    /**
     * Minimum value of font size.
     */
    private static final int MINIMUM_ALLOWED_FONT_SIZE = 11;

    /**
     *
     */

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
     * @requires 0 <= position < |text|
     * @ensures
     *
     *          <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     *          </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String response = "";

        int i = position;
        boolean check = separators.contains(text.charAt(i));
        while (i < text.length()
                && (separators.contains(text.charAt(i)) == check)) {

            if (i < text.length()) {
                response += text.charAt(i);

                i++;
            } else {
                i++;
            }
        }

        return response;
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
    public static void generateElements(String str, Set<Character> charSet)
            throws IOException {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";
        Set<Character> temp = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (!temp.contains(str.charAt(i))) {
                temp.add(str.charAt(i));
            }
        }
        charSet.addAll(temp);
        temp.clear();
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class IntegerLT
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    }

    /**
     * Compare {@code Map.Pair<String, Integer>}s in value's largest to smallest
     * order.
     */
    private static class StringLT
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> v1,
                Map.Entry<String, Integer> v2) {
            return v1.getKey().compareTo(v2.getKey());
        }
    }

    /**
     * Gets one line at a time from {@code in} until end of input, and puts them
     * into the queue {@code lines}.
     *
     * @param in
     *            the source of the lines to be input
     * @param words
     *            the map of lines that are read
     * @updates in
     * @replaces lines
     * @requires in.is_open
     * @ensures <pre>
     * in.is_open  and
     * in.ext_name = #in.ext_name  and
     * in.content = ""  and
     * lines = STRING_OF_LINES(#in.content)
     * </pre>
     */
    public static void countAndCreateMap(BufferedReader in,
            Map<String, Integer> words) {
        assert in != null : "Violation of: in is not null";
        assert words != null : "Violation of: lines is not null";

        /*
         * Define separator characters for test
         */
        final String separatorStr = " \t\n\r,-.!?[]';:/()";
        Set<Character> separatorSet = new HashSet<>();
        try {
            generateElements(separatorStr, separatorSet);
        } catch (IOException e) {

            System.err.println("Errors in separating lines to words.");
            return;
        }
        String tempStr = "";
        try {
            tempStr = in.readLine();
        } catch (IOException e1) {

            System.err.println("invalid line input.");
            return;
        }

        while (tempStr != null) {

            //This part replaced to get words in lower case only.
            tempStr = tempStr.toLowerCase();

            /*
             * Process sorting
             */
            int position = 0;
            int count = 1;
            while (position < tempStr.length()) {
                String token = nextWordOrSeparator(tempStr, position,
                        separatorSet);
                if (!separatorSet.contains(token.charAt(0))) {
                    if (words.containsKey(token)) {

                        count = words.get(token);
                        count++;
                        words.put(token, count);

                        //check if this is okay

                    }
                    if (!words.containsKey(token)) {
                        words.put(token, 1);
                    }

                }

                position += token.length();

            }
            try {
                tempStr = in.readLine();
            } catch (IOException e) {

                System.err.println("invalid line input.");
                return;
            }
        }

    }

    //The part where tags for html file is created
    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</head>
     * <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param content
     *            Map that stores word and their meaning from input text file.
     *
     * @param outfile
     *            the output stream
     * @param fileName
     *            the name of the html files being created,
     * @param cloudSize
     *            the integer(num) set by the user for number of words in tag
     *            cloud.
     * @param maxOccur
     *            There is a word that has appeared the most in the text, and I
     *            record how many times that word has appeared in the text.
     *            (maximum)
     * @param minOccur
     *            There is a word that has appeared the least in the text, and I
     *            record how many times that word has appeared in the text.
     *            text. (minimum)
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     *
     */
    private static void outputWordCloud(PrintWriter outfile,
            ArrayList<Map.Entry<String, Integer>> content, String fileName,

            int cloudSize, int minOccur, int maxOccur) throws IOException {

        int fontSize = MINIMUM_ALLOWED_FONT_SIZE;

        outfile.println("<html>");
        outfile.println("<head>");
        outfile.println("<title>Top" + cloudSize + " words in " + fileName
                + "</title>");
        outfile.println("<link href=\"http://web.cse.ohio-state.edu/software/22"
                + "31/web-sw2/assignments/projects/tag-cloud-generato"
                + "r/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        outfile.println("</head>");
        outfile.println("<body>");
        outfile.println("<h2>Top" + cloudSize + " words in " + fileName + ".txt"
                + "</h2>");
        outfile.println("<hr>");
        outfile.println("<div class=\"cdiv\">");
        outfile.println("<p class=\"cbox\">");

        int currElementsNumOccurences = 0;

        final int maxOccurrances = maxOccur;
        //415

        final int minOccurrences = minOccur;
        //140

        for (int j = 0; j < cloudSize; j++) {

            currElementsNumOccurences = content.get(j).getValue();

            if (maxOccur != minOccur) {
                fontSize = MAX_FONT_SIZE
                        * (currElementsNumOccurences - minOccurrences)
                        / (maxOccurrances - minOccurrences)
                        + MINIMUM_ALLOWED_FONT_SIZE;
            }

            outfile.println("<span style=\"cursor:default\" class=\"f"
                    + fontSize + "\" title=\"count:" + currElementsNumOccurences
                    + "\">" + content.get(j).getKey() + "</span>");

        }
        outfile.println("</p>");
        outfile.println("</div>");
        outfile.println("</body>");
        outfile.println(
                "<div id=\"__genieContainer\" style=\"all: initial;\">");
        outfile.println("</div>");
        outfile.println("</html>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        PrintWriter outfile = null;

        String fileName = "";
        String directoryName = "";
        String resultName = "";
        int num = 0;

        System.out.println("Type in the name of text file"
                + "(ex. terms.txt, data/terms2.txt, A): ");
        fileName = input.nextLine();
        System.out.println("fileName: " + fileName);
        System.out.println("Type in the directory to save the file."
                + "(should be the name of pre-existing folder ex. temp): ");
        directoryName = input.nextLine();
        System.out.println("dirName:" + directoryName);

        System.out.println("Name of the file that stores the result: ");
        resultName = input.nextLine();
        System.out.println("resultName: " + resultName);

        System.out.println("Number of words for tag cloud: ");
        num = input.nextInt();
        System.out.println("n: " + num);

        BufferedReader infile = null;
        try {
            outfile = new PrintWriter(new BufferedWriter(new FileWriter(
                    directoryName + "/" + resultName + ".html")));
        } catch (IOException e) {

            System.err.println("Error in creating file.");
            return;

        }

        try {
            infile = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.err.println("Error opening file");
            return;
        }
        //The part that asks user to type in

        Map<String, Integer> wordNum = new HashMap<String, Integer>();
//        countAndCreateMap(new SimpleReader1L(fileName), wordNum);
        countAndCreateMap(infile, wordNum);

//        System.out.println("HashMap: " + wordNum);
        //only leave 100 elements in the map,
        //from biggest to smallest integer value

        //1. sort only n-words from largest to smallest occurance
        //2. sort in alphabetical order.

        //1. Sort map from value() is biggest to smallest, and take out top 100
        //2. Add that to new Map
        int contentSize = wordNum.size();

        if (num > contentSize) {
            num = contentSize;
            System.out.println("The size of the cloud should not exceed"
                    + "the size of the list of the words in the text file.");
            System.out.println(
                    "the size of the list of the words in the text file: "
                            + contentSize);

        }

        //In case when the given input (integer) for the cloud size is bigger
        //than the total number of distinctive words in the file.

        ArrayList<Map.Entry<String, Integer>> x = new ArrayList<Entry<String, Integer>>(
                contentSize);
        for (Map.Entry<String, Integer> temp : wordNum.entrySet()) {

            x.add(temp);

        }

        //convert it to hashset
        //
        Comparator<Map.Entry<String, Integer>> ci = new IntegerLT();
        x.sort(ci);

        ArrayList<Map.Entry<String, Integer>> x2 = new ArrayList<Entry<String, Integer>>(
                num);

        int maxOccur = 0;
        int minOccur = 0;

        for (int i3 = 0; i3 < num; i3++) {
            Map.Entry<String, Integer> temp2 = x.get(i3);
            x2.add(temp2);

            if (i3 == 0) {
                maxOccur = temp2.getValue();
            }

            if (i3 == num - 1) {
                minOccur = temp2.getValue();
            }

        }
//        System.out.println(x2);
        //now x2 is sorted from maxcount to mincount.
        Comparator<Entry<String, Integer>> cs = new StringLT();
        x2.sort(cs);
//        System.out.println(x2);

        //creates the tag cloud file.
        try {
            outputWordCloud(outfile, x2, fileName, num, minOccur, maxOccur);

        } catch (IOException e) {
            System.err.print("file is empty");
            return;
        }

        System.out.println("File has been created.");

        /*
         * Close input and output streams
         */
        try {
            infile.close();
        } catch (IOException e) {

            System.err.println("Error closing the file (inflie)");
            return;

        }

        input.close();

        outfile.close();

    }

}
