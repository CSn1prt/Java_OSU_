import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class StringReassemblyTest {

    /**
     * Test of overlap().
     */
    @Test
    public void overlap1() {
        String str1 = "";
        String str2 = "";
        int result = StringReassembly.overlap(str1, str2);
        assertEquals(result, 0);
    } //boundary case, built to fail.

    @Test
    public void overlap2() {
        String str1 = "foo";
        String str2 = "var";
        int result = StringReassembly.overlap(str1, str2);
        assertEquals(result, 0);
    } //boundary case

    @Test
    public void overlap3() {
        String str1 = "helloworld";
        String str2 = "worldthere";
        int result = StringReassembly.overlap(str1, str2);
        assertEquals(result, 5);
    } //routine case, (challenging)

    @Test
    public void overlap4() {
        String str1 = "santa";
        String str2 = "anita";
        int result = StringReassembly.overlap(str1, str2);
        assertEquals(result, 1);
    } //routine case

    /**
     * Test of combination().
     */
    @Test
    public void Combination1() {
        String str1 = "howtodothis";
        String str2 = "water";
        int overlap = 0;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("iamnottheresult", result);
    }//error test (built to fail)

    @Test
    public void Combination2() {
        String str1 = "helloworld";
        String str2 = "worldofprogramming";
        int overlap = 5;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("helloworldofprogramming", result);
    }//rountine case

    @Test
    public void Combination3() {
        String str1 = "wearethesamesentence";
        String str2 = "wearethesamesentence";
        int overlap = 20;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("wearethesamesentence", result);
    }//routine case

    @Test
    public void Combination4() {
        String str1 = "iamthestringfromtheboxofdisk";
        String str2 = "kandthiswasokay";
        int overlap = 1;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("iamthestringfromtheboxofdiskandthiswasokay", result);
    }//routine case

    /**
     * Test of addToSetAvoidingSubstrings.
     */
    @Test
    public void addToSetAvoidingSubstrings1() {
        Set<String> set = new Set1L<>();
        String check = "";
        //setting up variables
        String str = "";
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        check = set.removeAny();

        assertEquals(str, check);
        //asserting values and check if it gives expected value
    }// boundary case

    @Test

    public void addToSetAvoidingSubstrings2() {

        Set<String> set = new Set1L<>();
        String check = "";

        String str = "hello world";
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        check = set.removeAny();

        assertEquals(str, check);
    }
    // routine case, should add to the set

    @Test
    public void addToSetAvoidingSubstrings3() {

        Set<String> set = new Set1L<>();
        String check1 = "";
        String check2 = "";

        String str = "Santa Anita";

        set.add(str);

        StringReassembly.addToSetAvoidingSubstrings(set, str);
        check1 = set.removeAny();
        check2 = set.removeAny();

        assertEquals(str, check1);
        assertEquals(str, check2);
    }
    // error test (built to fail, already in)

}