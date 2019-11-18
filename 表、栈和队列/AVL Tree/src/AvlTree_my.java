// AvlTree_my class
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.nio.BufferUnderflowException;

public class AvlTree_my <AnyType extends Comparable<? super AnyType>> {

    private static class AvlNode<AnyType> {

        AnyType element; // The data in the node
        AvlNode<AnyType> left; // Left child
        AvlNode<AnyType> right; // Right child
        AvlNode<AnyType> parent;
        int height; //Height

        // Constructors
        AvlNode(AnyType theElement) {this(theElement, null, null, null);}
        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt, AvlNode<AnyType> pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
            height = 0;
        }
    }

    /** The tree root*/
    private AvlNode<AnyType> root;
    private static final int ALLOW_IMBALANCE = 1;

    /**
     * Construct the tree
     */
    private AvlTree_my() {
        root = null;
    }

    /**
     * Insert into the tree
     * @param x the item to insert
     */
    private AvlNode<AnyType> insertPoint;
    private void insert(AnyType x) {root = insert(x, root, null);}
    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t, AvlNode<AnyType> k) {
        if (t == null){
            insertPoint = k;
            AvlNode<AnyType> p = new AvlNode<AnyType>(x, null, null, k);
            return p;
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
            if (t.left.height != 0 ) {
                t.height += 1;
            }
        }
        else if (compareResult > 0) {
            t.right = insert(x, t.right, t);
            if (t.right.height != 0 ) {
                t.height -= 1;
            }
        }
        else {
            //Duplicate; do nothing
            ;
        }
        return balance(t);
    }

    /**
     * Remove from the tree
     * @param x the item to remove
     */
    private void remove(AnyType x) {root = remove(x, root);}
    private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t) {
        if (t == null) {

            return t; //Item not found; do nothing
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        }
        else if (compareResult > 0) {
            t.right = remove(x, t.right);
        }
        else if (t.left != null && t.right != null) { //two children
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    /**
     * Find an item in the tree
     * @param x the item to find
     * @return true if x is found
     */
    private boolean contains(AnyType x) {
        return contains(x, root);
    }
    private boolean contains(AnyType x, AvlNode<AnyType> t) {
        while (t != null) {
            int compareResult = x.compareTo(t.element);
            if (compareResult > 0) {
               t = t.left;
            }
            else if(compareResult < 0) {
                t = t.right;
            }
            else {
                return true;
            }
        }
        return false;
    }

    /**
     * find min item in the tree
     */
    private AnyType finMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }
    /**
     * find min item in the tree
     * @param t tree node
     * @return the data of min node
     */
    private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
        if (t == null){
            return t;
        }

        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    /**
     * find the max item in the tree
     */
    private AnyType finMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }
    /**
     * find max item in the tree
     * @param t tree node
     * @return the data of max node
     */
    private AvlNode<AnyType> findMax(AvlNode<AnyType> t) {
        if (t == null){
            return t;
        }

        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    /**
     * Test the tree is logically empty
     * @return true if empty, false otherwise
     */
    private boolean isEmpty() {
        return root == null;
    }

    /**
     * keep balance of the tree
     * @param t the check node
     * @return balanced tree node
     */
    private AvlNode<AnyType> balance(AvlNode<AnyType> t) {
        AvlNode<AnyType> unbalancedPoint = insertPoint.parent;
        AvlNode<AnyType> unbalancedPointParent = insertPoint.parent.parent;
        if (t == null) {
            return t;
        }
        if (height(unbalancedPoint) == 2) {
            if (height(unbalancedPoint.left.left) != 0 ) {
                unbalancedPoint = rotateWithLeftChild(unbalancedPoint);

            } else {
                unbalancedPoint = doubleWithLeftChild(unbalancedPoint);
            }
        }
        else {
            if (height(unbalancedPoint.right.right) != 0) {
                unbalancedPoint = doubleWithRightChild(unbalancedPoint);
            } else {
                unbalancedPoint = rotateWithRightChild(unbalancedPoint);
                }
        }
        unbalancedPoint.parent = unbalancedPointParent;
        return t;
    }

    private int height(AvlNode<AnyType> t) {
        return (t == null) ? -1 : t.height;
    }

    /**
     * Rotate tree node with left child
     * @param t the unbalanced tree node
     * @return balanced tree node
     */
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> t) {
        AvlNode<AnyType> k2 = t;
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k1.height = 0;
        k2.height = 0;
        renewHeight(k1);
        return k1;
    }

    /**
     * Rotate tree node with right child
     * @param t the unbalanced tree node
     * @return balanced tree node
     */
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> t) {
        AvlNode<AnyType> k2 = t;
        AvlNode<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k1.height = 0;
        k2.height = 0;
        renewHeight(k1);
        return k1;
    }

    /**
     * renew the height of parent
     * @param k the unbalanced point
     * @return balanced point
     */
    private AvlNode<AnyType> renewHeight (AvlNode<AnyType> k) {
        if (k.parent == null) {
            return k;
        }
        k.parent = renewHeight(k.parent);
        k.parent.height = 0;
        return k;
    }

    /**
     * Firstly, rotate tree node in left; Secondly, rotate right
     * @param t the unbalanced tree node
     * @return balanced tree node
     */
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> t) {
        AvlNode<AnyType> root_0 = t.parent;
        AvlNode<AnyType> k3 = t;
        AvlNode<AnyType> k2 = k3.left;
        AvlNode<AnyType> k1 = k3.right;
        if(k1.left != null) {
            //left rotate
            k2.right = k1.left;k2.parent = k1;
            k1.left = k2;k1.parent = k3;
            k3.left = k1;
            //right rotate
            k3.parent = k1;
            k1.right = k3;k1.parent = root_0;
            //renew height
            k1.height = 0;
            k2.height = 0;
            k3.height = -1;
        }
        else {
            //left rotate
            k2.right = null;k2.parent = k1;
            k1.left = k2;k1.parent = k3;
            k3.left = k1;
            //right rotate
            k3.parent = k1;k3.left = k1.right;
            k1.right = k3;k1.parent = root_0;
            //renew height
            k1.height = 0;
            k2.height = 1;
            k3.height = 0;
        }
        renewHeight(k1);
        return k1;
    }

    /**
     * Firstly, rotate tree node in right; Secondly, rotate left
     * @param t the unbalanced tree node
     * @return balanced tree node
     */
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> t) {
        AvlNode<AnyType> root_0 = t.parent;
        AvlNode<AnyType> k3 = t;
        AvlNode<AnyType> k2 = k3.right;
        AvlNode<AnyType> k1 = k3.left;
        if(k1.right != null) {
            //right rotate
            k2.left = k1.right;k2.parent = k1;
            k1.right = k2;k1.parent = k3;
            k3.right = k1;
            //left rotate
            k3.parent = k1;
            k1.left = k3;k1.parent = root_0;
            //renew height
            k1.height = 0;
            k2.height = 0;
            k3.height = 1;
        }
        else {
            //right rotate
            k2.left = null;k2.parent = k1;
            k1.right = k2;k1.parent = k3;
            k3.left = k1;
            //left rotate
            k3.parent = k1;k3.right = k1.left;
            k1.left = k3;k1.parent = root_0;
            //renew height
            k1.height = 0;
            k2.height = -1;
            k3.height = 0;

        }
        renewHeight(k1);
        return k1;
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }



    public static void main(String[] args) {
        AvlTree_my<Integer> t = new AvlTree_my<>();

        final int NUMS = 400;  // must be even
        final int GAP = 37;

        System.out.println("Checking... (no more output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
            t.insert(i);
        }
        t.printTree(t.root);
    }
}
