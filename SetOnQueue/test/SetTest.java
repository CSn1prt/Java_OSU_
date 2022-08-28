import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    //Test add
    @Test
    public final void testAddNonEmpty1() {
        //Set variable
        Set<String> s = this.createFromArgsTest("world");
        Set<String> sExpected = this.createFromArgsRef("Hello", "world");
        //Call method under test
        s.add("Hell0");
        //Check and compare
        assertEquals(s, sExpected);
    }

    //Test remove
    @Test
    public final void testRemoveNonEmpty() {
        //Set variables
        Set<String> s = this.createFromArgsTest("dog", "cat", "whale");
        Set<String> sExpected = this.createFromArgsRef("cat", "whale");
        //Calling method under test
        s.remove("dog");
        //Check and compare value
        assertEquals(s, sExpected);
    }

    //Test remove
    @Test
    public final void testRemoveAny() {
        //Setting variables
        Set<String> s = this.createFromArgsTest("alpha", "bravo", "charlie");
        Set<String> sExpected = this.createFromArgsRef("alpha", "bravo",
                "charlie");
        //Calling method under test
        String digit = s.removeAny();
        assertTrue(
                sExpected.contains(digit) && s.size() == sExpected.size() - 1);

    }

    //Test Contains
    @Test
    public final void testContains() {
        //Setting variables
        Set<String> s = this.createFromArgsTest("white", "red", "black");
        Set<String> sExpected = this.createFromArgsRef("white", "red", "black");
        //Calling method under test
        String digit = "white";
        assertTrue(sExpected.contains(digit) && s.contains(digit));

    }

    //Test size
    @Test
    public final void testSize() {
        //Setting variables
        Set<String> s = this.createFromArgsTest("dog", "cat", "whale");
        Set<String> sExpected = this.createFromArgsRef("red", "white", "black");
        //Calling method under test

        int sizeA = s.size();
        int sizeB = sExpected.size();
        assertTrue(sizeA == sizeB);
    }

}
