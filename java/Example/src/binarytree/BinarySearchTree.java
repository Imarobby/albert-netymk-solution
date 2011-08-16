package binarytree;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// void removeMin( )      --> Remove minimum item
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Exceptions are thrown by insert, remove, and removeMin if warranted

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Mark Allen Weiss
 */
class ItemNotFoundException extends Exception {
};

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
		extends BinaryTree<AnyType> {
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Insert into the tree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @throws DuplicateItemException
	 *             if x is already present.
	 */
	public void insert(AnyType x) {
		root = insert(x, root);
	}

	/**
	 * Remove from the tree..
	 * 
	 * @param x
	 *            the item to remove.
	 * @throws ItemNotFoundException
	 *             if x is not found.
	 */
	public void remove(AnyType x) throws ItemNotFoundException {
		try {
			root = remove(x, root);
		} catch (ItemNotFoundException e) {
			throw e;
		}
	}

	/**
	 * Remove minimum item from the tree.
	 * 
	 * @throws ItemNotFoundException
	 *             if tree is empty.
	 */
	public void removeMin() throws ItemNotFoundException {
		try {
			root = removeMin(root);
		} catch (ItemNotFoundException e) {
			throw e;
		}
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin() {
		return elementAt(findMin(root));
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item or null if empty.
	 */
	public AnyType findMax() {
		return elementAt(findMax(root));
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the matching item or null if not found.
	 */
	public AnyType find(AnyType x) {
		return elementAt(find(x, root));
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the tree.
	 * @return the new root.
	 * @throws DuplicateItemException
	 *             if x is already present.
	 */
	private AnyType elementAt(BinaryNode<AnyType> t) {
		return t == null ? null : t.getElement();
	}

	protected BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			t = new BinaryNode<AnyType>(x);
		else if (x.compareTo(t.getElement()) < 0)
			t.setLeft(insert(x, t.getLeft()));
		else if (x.compareTo(t.getElement()) > 0)
			t.setRight(insert(x, t.getRight()));
		// else
		// throw new DuplicateItemException(x.toString()); // Duplicate
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the tree.
	 * @return the new root.
	 * @throws ItemNotFoundException
	 *             if x is not found.
	 */
	protected BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
			throws ItemNotFoundException {
		if (t == null)
			throw new ItemNotFoundException();
		if (x.compareTo(t.getElement()) < 0)
			t.setLeft(remove(x, t.getLeft()));
		else if (x.compareTo(t.getElement()) > 0)
			t.setRight(remove(x, t.getRight()));
		else if (t.getLeft() != null && t.getRight() != null) // Two children
		{
			t.setElement(findMin(t.getRight()).getElement());
			t.setRight(removeMin(t.getRight()));
		} else
			t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
		return t;
	}

	/**
	 * Internal method to remove minimum item from a subtree.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 * @return the new root.
	 * @throws ItemNotFoundException
	 *             if t is empty.
	 */
	protected BinaryNode<AnyType> removeMin(BinaryNode<AnyType> t)
			throws ItemNotFoundException {
		if (t == null)
			throw new ItemNotFoundException();
		if (t.getLeft() != null) {
			t.setLeft(removeMin(t.getLeft()));
			return t;
		} else
			return t.getRight();
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	protected BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t != null)
			while (t.getLeft() != null)
				t = t.getLeft();

		return t;
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t != null)
			while (t.getRight() != null)
				t = t.getRight();

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the matched item.
	 */
	private BinaryNode<AnyType> find(AnyType x, BinaryNode<AnyType> t) {
		while (t != null) {
			if (x.compareTo(t.getElement()) < 0)
				t = t.getLeft();
			else if (x.compareTo(t.getElement()) > 0)
				t = t.getRight();
			else
				return t; // Match
		}

		return null; // Not found
	}

	// Test program
	public static void main(String[] args) {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(System.in));
		// String line;
		// try {
		// while ((line = in.readLine()) != null) {
		// StringTokenizer st = new StringTokenizer(line);
		// while (st.hasMoreTokens())
		// t.insert(st.nextToken());
		// }
		// } catch (IOException e) {
		// System.err.println(e);
		// }
		t.insert(10);
		t.insert(3);
		t.insert(1);
		t.insert(4);
		System.out.println(BinaryTree.printLevelOrder(t));

	}
}