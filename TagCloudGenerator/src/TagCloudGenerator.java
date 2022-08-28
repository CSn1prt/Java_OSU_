import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine2;

/**
 * Counts how many times each word appeared in the text file.
 *
 * @author Seunghyun Nam, Group17
 *
 */
public final class TagCloudGenerator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
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
    public static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        Set<Character> temp = new Set1L<>();
        for (int i = 0; i < str.length(); i++) {
            if (!temp.contains(str.charAt(i))) {
                temp.add(str.charAt(i));
            }
        }
        charSet.transferFrom(temp);
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            return o1.key().toLowerCase().compareTo(o2.key().toLowerCase());
        }
    }

    /**
     * Compare {@code Map.Pair<String, Integer>}s in value's largest to smallest
     * order.
     */
    private static class IntegerLT
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> v1,
                Map.Pair<String, Integer> v2) {
            return v2.value().compareTo(v1.value());
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
    public static void countAndCreateMap(SimpleReader in,
            Map<String, Integer> words) {
        assert in != null : "Violation of: in is not null";
        assert words != null : "Violation of: lines is not null";
        assert in.isOpen() : "Violation of: in.is_open";

        /*
         * Define separator characters for test
         */
        final String separatorStr = " \t\n\r,-.!?[]';:/()";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        words.clear();
        while (!in.atEOS()) {
            String tempStr = in.nextLine().toLowerCase();
            //This part replaced to get words in lower case only.

            /*
             * Process sorting
             */
            int position = 0;
            int count = 0;
            while (position < tempStr.length()) {
                String token = nextWordOrSeparator(tempStr, position,
                        separatorSet);
                if (!separatorSet.contains(token.charAt(0))) {
                    if (words.hasKey(token)) {
                        Map.Pair<String, Integer> k = words.remove(token);
                        count = k.value();
                        count++;
                        words.add(token, count);

                    }
                    if (!words.hasKey(token)) {
                        count = 1;

                        words.add(token, count);
                    }

                }

                position += token.length();
            }

        }

    }

    //The part where tags for html file is created
    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
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
     * @param sm2
     *            Map that stores word and their meaning from input text file.
     * @param countMinAndMax
     *            Queue that contains the number of occurrences of each word in
     *            the file given by user.
     * @param sm
     *            Queue that stores words into non-decreasing lexicographic
     *            order
     * @param outfile
     *            the output stream
     * @param fileName
     *            the name of the html files being created,
     * @param cloudSize
     *            the integer(num) set by the user for number of words in tag
     *            cloud.
     * @param maxOccur
     * @param minOccur
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputWordCloud(SimpleWriter outfile,
            SortingMachine<Pair<String, Integer>> sm2, String fileName,
            SortingMachine<Pair<String, Integer>> sm, int cloudSize,
            int minOccur, int maxOccur) {

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
        sm.changeToExtractionMode();

        int currElementsNumOccurences = 0;

        final int maxOccurrances = maxOccur;
//

        final int minOccurrences = minOccur;
        sm2.changeToExtractionMode();

//
        for (int j = 0; j < cloudSize; j++) {

            //Sps. map's order from biggest to smallest number
            Map.Pair<String, Integer> temp2 = sm2.removeFirst();
            currElementsNumOccurences = temp2.value();
            fontSize = MAX_FONT_SIZE
                    * (currElementsNumOccurences - minOccurrences)
                    / (maxOccurrances - minOccurrences)
                    + MINIMUM_ALLOWED_FONT_SIZE;

            outfile.println("<span style=\"cursor:default\" class=\"f"
                    + fontSize + "\" title=\"count:" + temp2.value().toString()
                    + "\">" + temp2.key() + "</span>");

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
     * Method that creates a queue that stores words in input file in sorted
     * order.
     *
     * @param wordNum
     *            the command line arguments
     * @param alphabet
     *            Queue that stores words in sorted order.
     */
    private static void keywordToQueue(Map<String, Integer> wordNum,
            Queue<String> alphabet) {
        Map<String, Integer> wordCopy = new Map1L<>();
        wordCopy.transferFrom(wordNum);
        final int wordNumLen = wordCopy.size();
        for (int i = 0; i < wordNumLen; i++) {
            Map.Pair<String, Integer> tempA = wordCopy.removeAny();
            alphabet.enqueue(tempA.key());
//            countWordMinMaxOccur.enqueue(tempA.key());
            wordNum.add(tempA.key(), tempA.value());
        }
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

        //A is the input example given in the description webpage.
        String fileName = ""; //A
        String directoryName = ""; //data
        String htmlName = ""; //index
        int num = 0; //tag size

        out.println("Type in the name of text file"
                + "(ex. terms.txt, data/terms2.txt, A): ");
        fileName = in.nextLine();
        out.println("Type in the directory to save the file."
                + "(should be the name of pre-existing folder ex. temp): ");
        directoryName = in.nextLine();
        out.println("Type in the name of html file to save the tag cloud"
                + "(for example, to create <index.html>, type<index>): ");
        htmlName = in.nextLine();
        out.println("Number of words for tag cloud: ");
        num = in.nextInteger();

        SimpleWriter outfile = new SimpleWriter1L(
                directoryName + "/" + htmlName + ".html");
        SimpleReader infile = new SimpleReader1L(fileName);
        //The part that asks user to type in

        Map<String, Integer> wordNum = new Map1L<>();
        countAndCreateMap(infile, wordNum);

        //only leave "num" number of elements in the map,
        //from biggest to smallest integer value.

        Map<String, Integer> wordNum2 = new Map1L<>();
        Comparator<Map.Pair<String, Integer>> ci = new IntegerLT();
        Comparator<Map.Pair<String, Integer>> cs = new StringLT();
        //map is in sorting machine.
        SortingMachine<Map.Pair<String, Integer>> sm = new SortingMachine2<Map.Pair<String, Integer>>(
                ci);
        SortingMachine<Map.Pair<String, Integer>> sm2 = new SortingMachine2<Map.Pair<String, Integer>>(
                cs);
        SortingMachine<Map.Pair<String, Integer>> sm1 = sm.newInstance();
        int wordNumSize = wordNum.size();

        for (int x = 0; x < wordNumSize; x++) {
            Map.Pair<String, Integer> trx = wordNum.removeAny();
            sm.add(trx);

        }

        if (num > wordNumSize) {
            num = wordNumSize;
        }
        out.println("The number of words for tag cloud shouldn't"
                + " exceed the total number of words.");
        out.println("Total number of words in text file: " + wordNumSize);

        //1. Sort map from value() is biggest to smallest, and take out top 100
        //2. Add that to new Map
//        Queue<Integer> countMinAndMaxNew = new Queue2<>();
        //^ this is a queue for organizing words from A to Z.
        //Since Map's elements are not

        sm.changeToExtractionMode();
        int minOccur = 0;
        int maxOccur = 0;
        for (int p = 0; p < num; p++) {

            Map.Pair<String, Integer> tmp = sm.removeFirst();
//            out.println(tmp);
            sm2.add(tmp);
            if (p == 0) {
                maxOccur = tmp.value();
            }

            if (p == num - 1) {
                minOccur = tmp.value();
            }
            //This is a part that stores of the words to set the font size.
        }
        //In countMinAndMaxNew, max number would get in first, and taken out
        //first at outputWordCloud

//        keywordToQueue(wordNum2, null);
        //creates the tag cloud file.
        outputWordCloud(outfile, sm2, fileName, sm1, num, minOccur, maxOccur);

        out.println("File has been created.");

        /*
         * Close input and output streams
         */
        infile.close();
        outfile.close();
        in.close();
        out.close();
    }

}
