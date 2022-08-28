import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;

/**
 * Put a short phrase describing the program here.
 *
 * @author Seunghyun Roy Nam - nam.197
 *
 */
public final class SequenceStack {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceStack() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires
     *
     *           <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     *           </pre>
     *
     * @ensures
     *
     *          <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     *          </pre>
     */
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLen) {
        assert leftStack != null : "Violation of: rightStack is not null";
        assert leftStack != null : "Violation of: rightStack is not null";
        assert 0 <= newLeftLen : "Violation of: 0 <= newLeftLength";
        assert newLeftLen <= leftStack.length() + rightStack.length() : ""
                + "Violation of: newLeftLength <= |leftStack| + |rightStack|";
        int leftLen = leftStack.length();
//        if (leftLen > newLeftLen) {
//            leftStack.flip();
//            for (int i = 0; i < (leftLen - newLeftLen); i++) {
//                T digit = leftStack.pop();
//                rightStack.push(digit);
//            }
//            leftStack.flip();
//        }
//        if (leftLen < newLeftLen) {
//            leftStack.flip();
//            for (int i = 0; i < newLeftLen - leftLen; i++) {
//                T digit = rightStack.pop();
//                leftStack.push(digit);
//            }
//            leftStack.flip();
//        }

        if (leftLen < newLeftLen) {
            leftStack.push(rightStack.pop());
            setLengthOfLeftStack(leftStack, rightStack, newLeftLen);
        }

        if (leftLen > newLeftLen) {
            rightStack.push(leftStack.pop());
            setLengthOfLeftStack(leftStack, rightStack, newLeftLen);
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        myMethod();
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
