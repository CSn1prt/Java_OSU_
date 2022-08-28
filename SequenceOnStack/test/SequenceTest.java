import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    /**
     * Testing for add().
     */
    @Test
    public void testadd() {
        Sequence<String> s = this.createFromArgsTest(" a ", " b ");
        Sequence<String> sExpected = this.createFromArgsRef("c", " a ", " b ");

        s.add(0, " c ");

        assertEquals(sExpected, s);
    }

    /**
     * Testing for length of 0.
     */
    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, i);
    }

    /**
     * Testing for length that's not empty.
     */
    @Test
    public final void testLengthNonEmptyOne() {
        /*
         * Set up variables.
         */
        Sequence<String> q = this.createFromArgsTest("red");
        Sequence<String> qExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, i);
    }

    /**
     * testing when the length of the item is bigger than 1.
     */
    @Test
    public final void testLengthNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red", "white", "black");
        Sequence<String> qExpected = this.createFromArgsRef("red", "white",
                "blue");
        /*
         * Call method under test
         */
        int len = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(3, len);
    }

    /**
     * remove() testing.
     *
     */
    @Test
    public final void testRemove() {
        Sequence<String> q = this.createFromArgsTest("red", "white", "black");
        Sequence<String> qExpected = this.createFromArgsRef("white", "black");
        String digit = q.remove(0);
        assertEquals(qExpected, q);
        assertEquals(digit, "red");
    }
}
