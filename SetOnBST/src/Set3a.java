import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Group 17, Seunghyun Nam
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

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
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        boolean response = false;

        if (t.height() != 0) {
            T root = t.disassemble(left, right);
            int check = x.compareTo(root);

            if (check > 0) {
                response = isInTree(right, x);
                //when x is smaller than root, check left tree for x
            }

            if (check < 0) {
                response = isInTree(left, x);
                //when x is bigger than root, check right tree for x
            }

            if (check == 0) {
                response = true;
                //when root is the x
            }

            t.assemble(root, left, right);
        }

        return response;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {

        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        if (t.size() == 0) {
            t.assemble(x, left, right);
        } else if (x.compareTo(t.root()) < 0) {
            T root = t.disassemble(left, right);
            insertInTree(left, x);
            t.assemble(root, left, right);
        } else if (x.compareTo(t.root()) > 0) {
            T root = t.disassemble(left, right);
            insertInTree(right, x);
            t.assemble(root, left, right);
        }

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
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";

        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        //the smallest element is at the far left leave of the tree.

        T response = t.disassemble(left, right);
        T smallest = response;
        //parameter that stores the smallest, far left value of the tree.
        //added to prevent errors
        if (left.height() != 0) {

            smallest = removeSmallest(left);
            t.assemble(response, left, right);
            //after the recursion, BinaryTree t should keep initial value.

        } else {
            t.transferFrom(right);
            //when there's nothing on the left tree of the root, root is the
            //smallest.

        }

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

        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        T response = t.disassemble(left, right);
        T root = response;
        //root and response keeps same value at this point, to initialize
        //response value.

        if (x.compareTo(root) < 0) {
            response = removeFromTree(left, x);
            t.assemble(root, left, right);
            // if x is smaller than root, check left tree recursively
        } else if (x.compareTo(root) > 0) {
            response = removeFromTree(right, x);
            t.assemble(root, left, right);
            // if x is bigger than root, check right tree recursively
        }
        if (x.equals(response)) {
            response = x;
            if (right.height() != 0) {
                t.assemble(removeSmallest(right), left, right);
            } else {
                t.transferFrom(left);
            }
            //if left tree is empty, right tree's smallest element goes to the
            //left tree's root
        }

        return response;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.tree = new BinaryTree1<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
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
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        insertInTree(this.tree, x);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        return removeFromTree(this.tree, x);

    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        return removeSmallest(this.tree);

    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {

        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
