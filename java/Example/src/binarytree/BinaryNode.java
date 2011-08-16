package binarytree;

import java.util.ArrayDeque;

// BinaryNode class; stores a node in a tree.
//
// CONSTRUCTION: with (a) no parameters, or (b) an Object,
//     or (c) an Object, left child, and right child.
//
// *******************PUBLIC OPERATIONS**********************
// int size( )            --> Return size of subtree at node
// int height( )          --> Return height of subtree at node
// void printPostOrder( ) --> Print a postorder tree traversal
// void printInOrder( )   --> Print an inorder tree traversal
// void printPreOrder( )  --> Print a preorder tree traversal
// BinaryNode duplicate( )--> Return a duplicate tree

/**
 * Binary node class with recursive routines to compute size and height.
 */
final class BinaryNode<AnyType> {
	private AnyType element;
	private BinaryNode<AnyType> left;
	private BinaryNode<AnyType> right;

	public BinaryNode() {
		this(null, null, null);
	}

	public BinaryNode(AnyType t) {
		this(t, null, null);
	}

	public BinaryNode(AnyType theElement, BinaryNode<AnyType> lt,
			BinaryNode<AnyType> rt) {
		element = theElement;
		left = lt;
		right = rt;
	}

	/**
	 * Return the size of the binary tree rooted at t.
	 */
	public static <AnyType> int size(BinaryNode<AnyType> t) {
		if (t == null)
			return 0;
		else
			return 1 + size(t.left) + size(t.right);
	}

	public static <AnyType> int sizeofleaves(BinaryNode<AnyType> t) {
		if (t == null)
			return 0;
		else {
			if (t.left == null && t.right == null)
				return 1;
			else
				return sizeofleaves(t.left) + sizeofleaves(t.right);
		}

	}

	public static <AnyType> int sizeofhalfleaves(BinaryNode<AnyType> t) {
		if (t == null)
			return 0;
		else {
			if ((t.left == null && t.right != null)
					|| (t.left != null && t.right == null))
				return 1;
			else
				return sizeofhalfleaves(t.left) + sizeofhalfleaves(t.right);
		}
	}

	public static <AnyType> int sizeofroot(BinaryNode<AnyType> t) {
		if (t == null)
			return 0;
		else {
			if (t.left != null && t.right != null)
				return 1 + sizeofroot(t.left) + sizeofroot(t.right);
			else
				return sizeofroot(t.left) + sizeofroot(t.right);
		}

	}

	/**
	 * Return the height of the binary tree rooted at t.
	 */
	public static <AnyType> int height(BinaryNode<AnyType> t) {
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	// Print tree rooted at current node using preorder traversal.
	public static <AnyType> String printPreOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			StringBuilder str = new StringBuilder();
			str.append(t.getElement()).append("\n"); // Node
			if (t.getLeft() != null)
				str.append(printPreOrder(t.getLeft())); // Left
			if (t.getRight() != null)
				str.append(printPreOrder(t.getRight())); // Right
			return str.toString();
		} else
			return null;
	}

	// Print tree rooted at current node using postorder traversal.
	public static <AnyType> String printPostOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			StringBuilder str = new StringBuilder();
			if (t.getLeft() != null)
				str.append(printPreOrder(t.getLeft())); // Left
			if (t.getRight() != null)
				str.append(printPreOrder(t.getRight())); // Right
			str.append(t.getElement()).append("\n"); // Node
			return str.toString();
		} else
			return null;
	}

	// Print tree rooted at current node using inorder traversal.
	public static <AnyType> String printInOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			StringBuilder str = new StringBuilder();
			if (t.getLeft() != null)
				str.append(printPreOrder(t.getLeft())); // Left
			str.append(t.getElement()).append("\n"); // Node
			if (t.getRight() != null)
				str.append(printPreOrder(t.getRight())); // Right
			return str.toString();
		} else
			return null;
	}

	public static <AnyType> String printLevelOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			ArrayDeque<BinaryNode<AnyType>> queue = new ArrayDeque<BinaryNode<AnyType>>();
			StringBuilder str = new StringBuilder();
			queue.add(t);
			while (!queue.isEmpty()) {
				t = queue.poll();
				str.append(t.getElement()).append("\n");
				if (t.getLeft() != null)
					queue.add(t.getLeft());
				if (t.getRight() != null)
					queue.add(t.getRight());
			}
			return str.toString();
		} else
			return null;
	}

	/**
	 * Return a reference to a node that is the root of a duplicate of the
	 * binary tree rooted at the current node.
	 */
	public BinaryNode<AnyType> duplicate() {
		BinaryNode<AnyType> root = new BinaryNode<AnyType>(element, null, null);

		if (left != null) // If there's a left subtree
			root.left = left.duplicate(); // Duplicate; attach
		if (right != null) // If there's a right subtree
			root.right = right.duplicate(); // Duplicate; attach
		return root; // Return resulting tree
	}

	public AnyType getElement() {
		return element;
	}

	public BinaryNode<AnyType> getLeft() {
		return left;
	}

	public BinaryNode<AnyType> getRight() {
		return right;
	}

	public void setElement(AnyType x) {
		element = x;
	}

	public void setLeft(BinaryNode<AnyType> t) {
		left = t;
	}

	public void setRight(BinaryNode<AnyType> t) {
		right = t;
	}

}