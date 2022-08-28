import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of binary search tree static, generic
 * methods isInTree (and removeSmallest).
 *
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 *
 * @author Put your name here
 *
 */
public final class BinarySearchTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinarySearchTreeMethods() {
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        // TODO - fill in body
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        boolean response = false;

        if (t.height() != 0) {
            T root = t.disassemble(left, right);
            int check = x.compareTo(root);

            if (check > 0) {
                response = isInTree(right, x);
            }

            if (check < 0) {
                response = isInTree(left, x);
            }

            if (check == 0) {
                response = true;
            }

            t.assemble(root, left, right);
        }

        // This line added just to make the component compilable.
        return response;
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    public static <T> T removeSmallest(BinaryTree<T> t) {

        // TODO - fill in body

        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        T root = t.disassemble(left, right);

        T smallest = t.root();
        if (left.height() != 0) {

            smallest = removeSmallest(left);
            t.assemble(root, left, right);

        } else {

            smallest = t.root();
            t.transferFrom(right);

        }

        // This line added just to make the component compilable.

        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";

        // TODO - fill in body
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        T response = t.disassemble(left, right);

        if (x.compareTo(response) < 0) {
            response = removeFromTree(left, x);
            t.assemble(response, left, right);
        }

        if (x.compareTo(response) > 0) {
            response = removeFromTree(right, x);
            t.assemble(response, left, right);
        }

        if (x.equals(response)) {
            response = t.root();
            if (right.height() != 0) {
                t.assemble(removeSmallest(right), left, right);
            } else {
                t.transferFrom(left);
            }
            //if left tree is empty, right tree's smallest element goes to the
            //left tree's root?
        }

        return response;
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
         * Input tree labels and construct BST.
         */
        out.println("Input the distinct labels for a binary search tree "
                + "in the order in which you want them inserted.");
        out.println("Press Enter on an empty line to terminate your input.");
        out.println();
        out.print("Next label: ");
        String str = in.nextLine();
        BinaryTree<String> t = new BinaryTree1<String>();
        while (str.length() > 0) {
            BinaryTreeUtility.insertInTree(t, str);
            out.println();
            out.println("t = " + BinaryTreeUtility.treeToString(t));
            out.println();
            out.print("Next label: ");
            str = in.nextLine();
        }
        /*
         * Input strings and check whether each is in the BST or not.
         */
        out.println();
        out.print("  Input a label to search "
                + "(or just press Enter to input a new tree): ");
        String label = in.nextLine();
        while (label.length() > 0) {
            if (isInTree(t, label)) {
                out.println("    \"" + label + "\" is in the tree");
            } else {
                out.println("    \"" + label + "\" is not in the tree");
            }
            out.print("  Input a label to search "
                    + "(or just press Enter to terminate the program): ");
            label = in.nextLine();
        }
        /*
         * Output BST labels in order.
         */
//        out.println();
//        out.println("Labels in BST in order:");
//        while (t.size() > 0) {
//
//            label = removeSmallest(t);
//            out.println("  " + label);
//        }

        out.println("element to remove: ");
        String label2 = in.nextLine();
        out.println(removeFromTree(t, label2));

        in.close();
        out.close();
    }
}
