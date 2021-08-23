import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber total = new NaturalNumber2(0);
        XMLTree left = exp.child(0);
        XMLTree right = exp.child(1);

        NaturalNumber leftValue = new NaturalNumber2(0);
        NaturalNumber rightValue = new NaturalNumber2(0);

        if (left.hasAttribute("value")) {
            NaturalNumber copyLeft = new NaturalNumber2(
                    Integer.parseInt(left.attributeValue("value")));
            leftValue.transferFrom(copyLeft);
        }

        if (right.hasAttribute("value")) {
            NaturalNumber copyRight = new NaturalNumber2(
                    Integer.parseInt(right.attributeValue("value")));
            rightValue.transferFrom(copyRight);
        }

        NaturalNumber placeLeft = new NaturalNumber2(leftValue);
        NaturalNumber placeRight = new NaturalNumber2(rightValue);

        if (exp.isTag()) {
            if (exp.label().equals("plus")) {
                if (!left.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(left));
                    total.add(rightValue);
                }
                if (!right.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(right));
                    total.add(leftValue);
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    rightValue.add(leftValue);
                    total.add(rightValue);
                }
            }
            //Creating the result of addition(+) with checking the integer
            //values of left and right nodes in a subtree. (recursion)

            if (exp.label().contentEquals("minus")) {
                if (!left.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(left));
                    if (total.compareTo(placeRight) < 0) {
                        components.utilities.Reporter.fatalErrorToConsole(
                                "Error in subtraction, negative occured.");
                    } else {
                        total.subtract(rightValue);
                    }
                }
                if (!right.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(right));
                    if (total.compareTo(placeLeft) < 0) {
                        components.utilities.Reporter.fatalErrorToConsole(
                                "Error in subtraction, negative occured.");
                    } else {
                        total.subtract(leftValue);
                    }
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    if (placeLeft.compareTo(placeRight) < 0) {
                        components.utilities.Reporter.fatalErrorToConsole(
                                "Error in subtraction, negative occured.");
                    } else {
                        leftValue.subtract(rightValue);
                        total.add(leftValue);
                    }
                }
            }
            //Creating the result of subtraction(-) with checking the integer
            //values of left and right nodes in a subtree. (recursion)
            //(addition and subtraction have similar structure from each other)

            if (exp.label().equals("times")) {
                if (!left.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(left));
                    total.multiply(rightValue);
                }
                if (!right.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(right));
                    total.multiply(leftValue);
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    rightValue.multiply(leftValue);
                    total.multiply(rightValue);
                }
            }

            //Creating the result of multplication(*) with checking the integer
            //values of left and right nodes in a subtree. (recursion)

            if (exp.label().equals("divide")) {
                if (!left.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(left));
                    if (rightValue.isZero()) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, "
                                        + "impossible to divide by 0");
                    } else {
                        total.divide(rightValue);
                    }
                    //Divison by 0 is impossible,

                }

                if (!right.hasAttribute("value")) {
                    total = new NaturalNumber2(evaluate(right));
                    if (leftValue.isZero()) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, "
                                        + "impossible to divide by 0");
                    } else {
                        leftValue.divide(total);
                        total.transferFrom(leftValue);
                    }
                    //Divison by 0 is impossible,
                }

                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    if (rightValue.isZero()) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, "
                                        + "impossible to divide by 0");
                    } else {
                        leftValue.divide(rightValue);
                        total.add(leftValue);
                    }
                }
            }
            //Creating the result of division(/) with checking the integer

        }

        /*
         * return statement.
         */
        return total;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}