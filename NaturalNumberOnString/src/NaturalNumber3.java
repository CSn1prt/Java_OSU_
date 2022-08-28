import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author nam.197 Seunghyun Nam
 *
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.rep = "";

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {

        this.createNewRep();

    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";

        String number = "" + i;
        // check if it is 0 (if it is, do nothing.)
        if (!number.equals("0") || !number.equals("")) {
            this.rep = number;
        }

    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";
        // check if it is 0 if it is do nothing

        if (!s.equals("0") || !s.equals("")) {
            this.rep = s;
        }

    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";

        String number = n.toString();
        // check if it is 0 if it is do nothing

        if (!number.equals("0") || !number.equals("")) {
            this.rep = number;
        }
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        //implement case of when 'this' is 0 k is not 0 and equal "" than multiply

        // check if rep has nothing and k is 0
        if (this.rep.equals("") && k == 0) {
            this.rep = "";
        } else {
            this.rep = this.rep + String.valueOf(k);
        }

    }

    @Override
    public final int divideBy10() {

        int length = this.rep.length();
        int remain = 0;
        if (length >= 1) {
            // remain comes from last digit
            remain = Character.getNumericValue(this.rep.charAt(length - 1));
            if (length == 1) {
                this.rep = "";
                // if it is a single digit number
            } else {
                this.rep = this.rep.substring(0, length - 1);
                // if it is other than single digit number
            }
        } else {
            // if there is nothing nothing on string and remain as 0
            remain = 0;
            this.rep = "";
        }

        // This line added just to make the component compilable.
        return remain;

    }

    @Override
    public final boolean isZero() {

        // This line added just to make the component compilable.
        return (this.rep.equals("0") || this.rep.equals(""));
    }

}
