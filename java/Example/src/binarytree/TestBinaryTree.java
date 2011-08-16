package binarytree;
public class TestBinaryTree {

	static public void main(String[] args) {
		BinaryTree<Integer> t1 = new BinaryTree<Integer>(1);
		BinaryTree<Integer> t3 = new BinaryTree<Integer>(3);
		BinaryTree<Integer> t5 = new BinaryTree<Integer>(5);
		BinaryTree<Integer> t7 = new BinaryTree<Integer>(7);
		BinaryTree<Integer> t2 = new BinaryTree<Integer>();
		BinaryTree<Integer> t4 = new BinaryTree<Integer>();
		BinaryTree<Integer> t6 = new BinaryTree<Integer>();

		t2.merge(2, t1, t3);
		t6.merge(6, t5, t7);
		t4.merge(4, t2, t6);
		System.out.println(BinaryTree.sizeofLeaves(t4));
		System.out.println(BinaryTree.sizeofhalfleaves(t4));
		System.out.print(BinaryTree.sizeofroot(t4));
	}
}
