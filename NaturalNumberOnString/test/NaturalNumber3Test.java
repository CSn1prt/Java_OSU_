import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {

        // This line is for the return value from constructor.
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {

        // This line is for the return value from constructor.
        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {

        // This line is for the return value from constructor.
        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {

        // This line is for the return value from constructor.
        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {

        // This line is for the return value from constructor.
        return new NaturalNumber1L();

    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        // This line is for the return value from constructor.
        return new NaturalNumber1L(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        // This line is for the return value from constructor.
        return new NaturalNumber1L(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        // This line is for the return value from constructor.
        return new NaturalNumber1L(n);
    }

}
