import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GlossaryTest {

    /**
     * Test fullLine()
     */
    @Test
    public void fullLineTest1() {
        /*
         * Set up variables and call method under test
         */
        String term = "";
        String definition = "";

        /*
         * Assert that values of variables match expectations
         */

        assertEquals(fullLine(term, definition), " <a href=\".html\"></a>");
    }

    /**
     * Test fullLine()
     */
    @Test
    public void fullLineTest2() {
        /*
         * Set up variables and call method under test
         */
        String term = "hello";
        String definition = "";

        /*
         * Assert that values of variables match expectations
         */

        assertEquals(Glossary.fullLine(term, definition),
                " <a href=\"" + "hello" + ".html\">" + "hello" + "</a>");
    }

    /**
     * Test NextWordOrSeparator()
     */
    @Test
    public void nextWordOrSeparatorTest2() {
        /*
         * Set up variables and call method under test
         */
        String term = "hello world";

        /*
         * Assert that values of variables match expectations
         */

        assertEquals(nextWordOrSeparator(term, definition), " ");
    }

}
