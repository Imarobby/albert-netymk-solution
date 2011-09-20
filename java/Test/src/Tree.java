import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Tree {
	private static boolean isMax = false;
	private static boolean leftFirst;
	private static int tmpUtility;

	public static int search(Node n, boolean leftFirst) {
		Tree.leftFirst = leftFirst;
		return search(n);
	}

	public static int search(Node n) {
		// Every call stack will invert the value of isMax.
		isMax = !isMax;
		// System.out.println("Beginning: This node is " + n + ", isMax is " + isMax);
		if(n.hasChildren == false) {
			isMax = !isMax;
			return n.getUtility();
		}

		if(leftFirst) {
			if(n.hasParent() == false || n.getParentUtility() == null) {
				// We are in the highest level or the parent utility has been decided,
				// so just iterate all the children to find the max/min
				// Pre-select the first one
				n.setUtility(search(n.getChild(0)));
				// Iterating the rest.
				for(int i=1; i<n.getChildrenSize(); ++i) {
					tmpUtility = search(n.getChild(i));
					if(isMax) {
						if(tmpUtility > n.getUtility()) {
							n.setUtility(tmpUtility);
						}
					} else {
						if(tmpUtility < n.getUtility()) {
							n.setUtility(tmpUtility);
						}
					}
				}
			} else {
				// In addition to the iteration, we need to check if it's necessary to continue the iteration after each node.
				for(int i=0; i<n.getChildrenSize(); ++i) {
					tmpUtility = search(n.getChild(i));
					if(isMax) {
						if( n.getUtility() == null || tmpUtility > n.getUtility() ) {
							n.setUtility(tmpUtility);
						}
						if(tmpUtility > n.getParentUtility()) {
							System.out.println("current node is: " + n + " " + n.getUtility()
									+ " Parent is: " + n.getParentUtility());
							for(int j=i+1; j<n.getChildrenSize(); ++j) {
								System.out.println("cutoff: Node " + n.getChild(j));
							}
							break;
						}
					} else {
						if( n.getUtility() == null || tmpUtility < n.getUtility() ) {
							n.setUtility(tmpUtility);
						}
						if(tmpUtility < n.getParentUtility()) {
							System.out.println("current node is: " + n + " " + n.getUtility()
									+ " Parent is: " + n.getParentUtility());
							for(int j=i+1; j<n.getChildrenSize(); ++j) {
								System.out.println("cutoff: Node " + n.getChild(j));
							}
							break;
						}
					}
				}
			}
			isMax = !isMax;
			return n.getUtility();
		} else {
			// right first branch
			if(n.hasParent() == false || n.getParentUtility() == null) {
				n.setUtility(search(n.getChild(n.getChildrenSize()-1)));
				for(int i=n.getChildrenSize()-2; i>=0; --i) {
					tmpUtility = search(n.getChild(i));
					if(isMax) {
						if(tmpUtility > n.getUtility()) {
							n.setUtility(tmpUtility);
						}
					} else {
						if(tmpUtility < n.getUtility()) {
							n.setUtility(tmpUtility);
						}
					}
				}
			} else {
				// System.out.println("This node is " + n + ", isMax is " + isMax);
				for(int i=n.getChildrenSize()-1; i>=0; --i) {
					tmpUtility = search(n.getChild(i));
					if(isMax) {
						if( n.getUtility() == null || tmpUtility > n.getUtility() ) {
							n.setUtility(tmpUtility);
						}
						if(tmpUtility > n.getParentUtility()) {
							System.out.println("current node is: " + n + " " + n.getUtility()
									+ " Parent is: " + n.getParentUtility());
							for(int j=i-1; j>=0; --j) {
								System.out.println("cutoff: Node " + n.getChild(j));
							}
							break;
						}
					} else {
						if( n.getUtility() == null || tmpUtility < n.getUtility() ) {
							n.setUtility(tmpUtility);
						}
						if(tmpUtility < n.getParentUtility()) {
							System.out.println("current node is: " + n + " " + n.getUtility()
									+ " Parent is: " + n.getParentUtility());
							for(int j=i-1; j>=0; --j) {
								System.out.println("cutoff: Node " + n.getChild(j));
							}
							break;
						}
					}
				}
			}
			isMax = !isMax;
			return n.getUtility();
		}
	}
	public static class Test {
		public static void main(String[] args) {
			List<Node> childrenList;
			Node w = new Node("W", 10, null);
			Node x = new Node("X", 2, null);
			Node y = new Node("Y", 9, null);
			childrenList = new LinkedList<Node>();
			childrenList.add(w);
			childrenList.add(x);
			childrenList.add(y);
			Node n = new Node("N",childrenList, null);
			w.setParent(n);
			x.setParent(n);
			y.setParent(n);
			Node o = new Node("O", 4, null);
			childrenList  = new LinkedList<Node>();
			childrenList.add(n);
			childrenList.add(o);
			Node f = new Node("F", childrenList, null);
			o.setParent(f);
			n.setParent(f);
			Node e = new Node("E", 8, null);
			Node g = new Node("G", 7, null);
			Node z = new Node("Z", 2, null);
			Node aa = new Node("AA", 4, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(z);
			childrenList.add(aa);
			Node q = new Node("Q", childrenList, null);
			z.setParent(q);
			aa.setParent(q);
			Node p = new Node("P", 4, null);
			Node r = new Node("R", 8, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(p);
			childrenList.add(q);
			childrenList.add(r);
			Node h = new Node("H", childrenList, null);
			p.setParent(h);
			q.setParent(h);
			r.setParent(h);
			childrenList = new ArrayList<Node>();
			childrenList.add(e);
			childrenList.add(f);
			childrenList.add(g);
			childrenList.add(h);
			Node b = new Node("B", childrenList, null);
			e.setParent(b);
			f.setParent(b);
			g.setParent(b);
			h.setParent(b);
			Node i = new Node("I", 3, null);
			Node j = new Node("J", 8, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(i);
			childrenList.add(j);
			Node c = new Node("C", childrenList, null);
			i.setParent(c);
			j.setParent(c);
			Node bb = new Node("BB", 3, null);
			Node cc = new Node("CC", 9, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(bb);
			childrenList.add(cc);
			Node s = new Node("S", childrenList, null);
			bb.setParent(s);
			cc.setParent(s);
			Node dd = new Node("DD", 2, null);
			Node ee = new Node("EE", 8, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(dd);
			childrenList.add(ee);
			Node t = new Node("T", childrenList, null);
			dd.setParent(t);
			ee.setParent(t);
			childrenList = new ArrayList<Node>();
			childrenList.add(s);
			childrenList.add(t);
			Node k = new Node("K", childrenList, null);
			s.setParent(k);
			t.setParent(k);
			Node l = new Node("L", 4, null);
			Node u = new Node("U", 6, null);
			Node v = new Node("V", 9, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(u);
			childrenList.add(v);
			Node m = new Node("M", childrenList, null);
			u.setParent(m);
			v.setParent(m);
			childrenList = new ArrayList<Node>();
			childrenList.add(k);
			childrenList.add(l);
			childrenList.add(m);
			Node d = new Node("D", childrenList, null);
			k.setParent(d);
			l.setParent(d);
			m.setParent(d);
			childrenList = new ArrayList<Node>();
			childrenList.add(b);
			childrenList.add(c);
			childrenList.add(d);
			Node a = new Node("A", childrenList, null);
			b.setParent(a);
			c.setParent(a);
			d.setParent(a);
			System.out.println(Tree.search(a, false));
			/*
			Node w = new Node("W", -3, null);
			Node x = new Node("X", -5, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(w);
			childrenList.add(x);
			Node o = new Node("O", childrenList, null);
			w.setParent(o);
			x.setParent(o);
			Node n = new Node("N", 4, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(n);
			childrenList.add(o);
			Node f = new Node("F", childrenList, null);
			n.setParent(f);
			o.setParent(f);
			Node g = new Node("G", -5, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(f);
			childrenList.add(g);
			Node b = new Node("B", childrenList, null);
			f.setParent(b);
			g.setParent(b);

			Node p = new Node("P", 9, null);
			Node q = new Node("Q", -6, null);
			Node r = new Node("R", 0, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(p);
			childrenList.add(q);
			childrenList.add(r);
			Node j = new Node("J", childrenList, null);
			p.setParent(j);
			q.setParent(j);
			r.setParent(j);
			Node h = new Node("H", -10, null);
			Node i = new Node("I", 8, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(h);
			childrenList.add(i);
			childrenList.add(j);
			Node c = new Node("C", childrenList, null);
			h.setParent(c);
			i.setParent(c);
			j.setParent(c);

			Node d = new Node("D", 0, null);

			Node s = new Node("S", 3, null);
			Node t = new Node("T", 5, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(s);
			childrenList.add(t);
			Node k = new Node("K", childrenList, null);
			s.setParent(k);
			t.setParent(k);
			Node l = new Node("L", 2, null);
			Node u = new Node("U", -7, null);
			Node v = new Node("V", -9, null);
			childrenList = new ArrayList<Node>();
			childrenList.add(u);
			childrenList.add(v);
			Node m = new Node("M", childrenList, null);
			u.setParent(m);
			v.setParent(m);
			childrenList = new ArrayList<Node>();
			childrenList.add(k);
			childrenList.add(l);
			childrenList.add(m);
			Node e = new Node("E", childrenList, null);
			k.setParent(e);
			l.setParent(e);
			m.setParent(e);
			childrenList = new ArrayList<Node>();
			childrenList.add(b);
			childrenList.add(c);
			childrenList.add(d);
			childrenList.add(e);
			Node a = new Node("A", childrenList, null);
			b.setParent(a);
			c.setParent(a);
			d.setParent(a);
			e.setParent(a);
			System.out.println(Tree.search(a, true));
			*/
		}
	}
}
