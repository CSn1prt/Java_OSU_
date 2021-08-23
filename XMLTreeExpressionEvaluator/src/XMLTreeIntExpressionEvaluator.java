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
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int total = 0;
        XMLTree left = exp.child(0);
        XMLTree right = exp.child(1);

        if (exp.isTag()) {
            if (exp.label().equals("plus")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    total += Integer.parseInt(right.attributeValue("value"));
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);
                    total = Integer.parseInt(left.attributeValue("value"))
                            + total;
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    total += Integer.parseInt(left.attributeValue("value"))
                            + Integer.parseInt(right.attributeValue("value"));
                }
            }
            //Creating the result of addition(+) with checking the integer
            //values of left and right nodes in a subtree. (recursion)

            if (exp.label().contentEquals("minus")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    total -= Integer.parseInt(right.attributeValue("value"));
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);
                    total = Integer.parseInt(left.attributeValue("value"))
                            - total;
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    total += Integer.parseInt(left.attributeValue("value"))
                            - Integer.parseInt(right.attributeValue("value"));
                }
            }
            //Creating the result of subtraction(-) with checking the integer
            //values of left and right nodes in a subtree. (recursion)
            //(addition and subtraction have similar structure from each other)

            if (exp.label().equals("times")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    total = total
                            * Integer.parseInt(right.attributeValue("value"));
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);
                    total = Integer.parseInt(left.attributeValue("value"))
                            * total;
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    total += Integer.parseInt(left.attributeValue("value"))
                            * Integer.parseInt(right.attributeValue("value"));
                }
            }

            //Creating the result of multplication(*) with checking the integer
            //values of left and right nodes in a subtree. (recursion)

            if (exp.label().equals("divide")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    if (Integer.parseInt(right.attributeValue("value")) == 0) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, "
                                        + "impossible to divide by 0");
                    } else {
                        total = total / Integer
                                .parseInt(right.attributeValue("value"));
                    }
                    ////Divison by 0 is impossible,
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);
                    if (total == 0) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, "
                                        + "impossible to divide by 0");
                    } else {
                        total = Integer.parseInt(left.attributeValue("value"))
                                / total;
                    }
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    if (Integer.parseInt(right.attributeValue("value")) == 0) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, "
                                        + "impossible to divide by 0");
                    } else {
                        total += Integer.parseInt(left.attributeValue("value"))
                                / Integer.parseInt(
                                        right.attributeValue("value"));
                    }
                    //Divison by 0 is impossible, again
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