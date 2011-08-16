package binarytree;

// BinaryTree class; stores a binary tree.
//
// CONSTRUCTION: with (a) no parameters or (b) an object to
//    be placed in the root of a one-element tree.
//
// *******************PUBLIC OPERATIONS**********************
// Various tree traversals, size, height, isEmpty, makeEmpty.
// Also, the following tricky method:
// void merge( Object root, BinaryTree t1, BinaryTree t2 )
//                        --> Construct a new tree
// *******************ERRORS*********************************
// Error message printed for illegal merges.

/**
 * BinaryTree class that illustrates the calling of BinaryNode recursive
 * routines and merge.
 */
public class BinaryTree<AnyType extends Comparable<? super AnyType>> {
	protected BinaryNode<AnyType> root;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(AnyType rootItem) {
		root = new BinaryNode<AnyType>(rootItem, null, null);
	}

	public static <AnyType extends Comparable<? super AnyType>> String printPreOrder(
			BinaryTree<AnyType> t) {
		if (t != null)
			return BinaryNode.printPreOrder(t.root);
		else
			return null;
	}

	public static <AnyType extends Comparable<? super AnyType>> String printInOrder(
			BinaryTree<AnyType> t) {
		if (t != null)
			return BinaryNode.printInOrder(t.root);
		else
			return null;
	}

	public static <AnyType extends Comparable<? super AnyType>> String printPostOrder(
			BinaryTree<AnyType> t) {
		if (t != null)
			return BinaryNode.printPostOrder(t.root);
		else
			return null;
	}

	public static <AnyType extends Comparable<? super AnyType>> String printLevelOrder(
			BinaryTree<AnyType> t) {
		if (t != null)
			return BinaryNode.printLevelOrder(t.root);
		else
			return null;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Merge routine for BinaryTree class. Forms a new tree from rootItem, t1
	 * and t2. Does not allow t1 and t2 to be the same. Correctly handles other
	 * aliasing conditions.
	 */
	public void merge(AnyType rootItem, BinaryTree<AnyType> t1,
			BinaryTree<AnyType> t2) {
		if (t1.root == t2.root && t1.root != null) {
			System.err.println("leftTree==rightTree; merge aborted");
			return;
		}

		// Allocate new node
		root = new BinaryNode<AnyType>(rootItem, t1.root, t2.root);

		// Ensure that every node is in one tree
		if (this != t1)
			t1.root = null;
		if (this != t2)
			t2.root = null;
	}

	public int size() {
		return BinaryNode.size(root);
	}

	public static <AnyType extends Comparable<? super AnyType>> int sizeofLeaves(
			BinaryTree<AnyType> t) {

		return BinaryNode.sizeofleaves(t.root);
	}

	public static <AnyType extends Comparable<? super AnyType>> int sizeofhalfleaves(
			BinaryTree<AnyType> t) {
		return BinaryNode.sizeofhalfleaves(t.root);
	}

	public static <AnyType extends Comparable<? super AnyType>> int sizeofroot(
			BinaryTree<AnyType> t) {
		return BinaryNode.sizeofroot(t.root);
	}

	public int height() {
		return BinaryNode.height(root);
	}

	public BinaryNode<AnyType> getRoot() {
		return root;
	}

}