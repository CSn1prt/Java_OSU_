import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Counts how many times each word appeared in the text file.
 *
 * @author Seunghyun Roy Nam
 *
 */
public final class WordCount {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCount() {
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
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
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
        final String separatorStr = " .\t, -";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        while (!in.atEOS()) {
            String tempStr = in.nextLine();

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
                    } else {
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
     * @param content
     *            Map that stores word and their meaning from input text file.
     * @param alphabetical
     *            Queue that stores words into non-decreasing lexicographic
     *            order
     * @param outfile
     *            the output stream
     * @param fileName
     *            the name of the html files being created,
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputWordHtml(SimpleWriter outfile,
            Map<String, Integer> content, String fileName,
            Queue<String> alphabetical) {
        outfile.println("<html>");
        outfile.println("<head>");
        outfile.println(
                "<title>Words Counted in " + fileName + ".txt" + "</title>");
        outfile.println("</head>");
        outfile.println("<body>");
        outfile.println("<h2>Words Counted in " + fileName + "</h2>");
        outfile.println("<hr>");
        outfile.println("<table border=\"1\">");
        outfile.println("<tbody>");
        outfile.println("<tr>");
        outfile.println("<th>" + "Words" + "</th>");
        outfile.println("<th>" + "Counts" + "</th>");
        outfile.println("</tr>");
        final int wordNumSize = content.size();
        for (int j = 0; j < wordNumSize; j++) {
            String temp1 = alphabetical.dequeue();
            Map.Pair<String, Integer> temp2 = content.remove(temp1);
            outfile.println("<tr>");
            outfile.println("<td>" + temp2.key() + "</td>");
            outfile.println("<td>" + temp2.value().toString() + "</td>");
            outfile.println("</tr>");
        }
        outfile.println("</tr>");
        outfile.println("</table>");
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
        Map<String, Integer> wordCopy = wordNum.newInstance();
        wordCopy.transferFrom(wordNum);
        final int wordNumLen = wordCopy.size();
        for (int i = 0; i < wordNumLen; i++) {
            Map.Pair<String, Integer> tempA = wordCopy.removeAny();
            alphabet.enqueue(tempA.key());
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

        String fileName = "";
        String directoryName = "";
        String indexName = "index";

        out.println("Type in the name of text file"
                + "(ex. terms.txt, data/terms2.txt): ");
        fileName = in.nextLine();
        out.println("Type in the directory to save the file."
                + "(should be the name of pre-existing folder ex. temp): ");
        directoryName = in.nextLine();
        out.println("Type in the name of html file (ex. index):");
        indexName = in.nextLine();

        SimpleWriter outfile = new SimpleWriter1L(
                directoryName + "/" + indexName + ".html");
        SimpleReader infile = new SimpleReader1L(fileName);
        //The part that asks user to type in

        Map<String, Integer> wordNum = new Map1L<>();
        countAndCreateMap(infile, wordNum);

        //Sort lines into non-decreasing lexicographic order
        //Creating Queue
        Queue<String> alphabetical = new Queue2<>();
        keywordToQueue(wordNum, alphabetical);

        Comparator<String> cs = new StringLT();
        alphabetical.sort(cs);

        //creates html file.
        outputWordHtml(outfile, wordNum, fileName, alphabetical);

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
