import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Seunghyun Nam, (Group17)
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * test case (constructors).
     */
    @Test
    public void testConstructor() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five",
                "Andy", "two");
        Map<String, String> test2 = this.createFromArgsRef("Bob", "five",
                "Andy", "two");
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) .
     */
    @Test
    public void testConstructorWithBlank() {
        Map<String, String> test = this.constructorRef();
        Map<String, String> test2 = this.constructorTest();
        assertEquals(test, test2);
    }

    /**
     * test case (add).
     */
    @Test
    public void testWithaddingToblank() {
        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> test2 = this.createFromArgsRef("Bob", "five");
        test.add("Bob", "five");
        assertEquals(test, test2);
    }

    /**
     * test case (add).
     */
    @Test
    public void testWithadding() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five");
        Map<String, String> test2 = this.createFromArgsRef("Bob", "five",
                "Andy", "six");
        test.add("Andy", "six");
        assertEquals(test, test2);
    }

    /**
     * test case (remove).
     */
    @Test
    public void testWithremove() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five",
                "Andy", "six");
        Map<String, String> test2 = this.createFromArgsRef("Andy", "six");
        test.remove("Bob");

        assertEquals(test, test2);
    }

    /**
     * test case (remove).
     */
    @Test
    public void testWithremoveAfterBlank() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five");
        Map<String, String> test2 = this.createFromArgsRef();
        test.remove("Bob");

        assertEquals(test, test2);
    }

    /**
     * test case (removeany).
     */
    @Test
    public void testWithremoveany() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five",
                "Andy", "six");
        Map<String, String> test2 = this.createFromArgsRef("Bob", "five",
                "Andy", "six");
        Map.Pair<String, String> compare = test.removeAny();
        String check = compare.key();

        assertEquals(true, test2.hasKey(check));
        assertEquals(compare.value(), test2.value(compare.key()));
        test2.remove(check);
        assertEquals(test, test2);

    }

    /**
     * test case (removeany).
     */
    @Test
    public void testWithremoveanyWithone() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five");
        Map<String, String> test2 = this.createFromArgsRef();
        test.removeAny();
        assertEquals(test, test2);
    }

    /**
     * test case (value).
     */
    @Test
    public void testWithvlaue() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five");
        Map<String, String> test2 = this.createFromArgsTest("Bob", "five");
        String check = test.value("Bob");
        assertEquals("five", check);
        assertEquals(test, test2);
    }

    /**
     * test case (value).
     */
    @Test
    public void testWithhaskeytrue() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five");
        Map<String, String> test2 = this.createFromArgsTest("Bob", "five");
        boolean check = test.hasKey("Bob");
        assertEquals(true, check);
        assertEquals(test, test2);
    }

    /**
     * test case (value).
     */
    @Test
    public void testWithhaskeyfalse() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five");
        //Reference: check if the original Map has been modified or not.
        Map<String, String> testExpected = this.createFromArgsTest("Bob",
                "five");
        boolean check = test.hasKey("bob");
        assertEquals(false, check);
        assertEquals(test, testExpected);
    }

    /**
     * test case (size).
     */
    @Test
    public void testwithSize0() {
        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> testExpected = this.createFromArgsTest();
        int big = test.size();
        assertEquals(0, big);
        assertEquals(test, testExpected);
    }

    /**
     * test case (size).
     */
    @Test
    public void testwithSize() {
        Map<String, String> test = this.createFromArgsTest("Bob", "five",
                "Andy", "six", "Nick", "one");
        Map<String, String> testExpected = this.createFromArgsTest("Bob",
                "five", "Andy", "six", "Nick", "one");
        int big = test.size();
        assertEquals(3, big);
        assertEquals(test, testExpected);
    }

}
