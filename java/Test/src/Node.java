import java.util.List;

public class Node {
	private String name;
	private Integer utility;
	final private List<Node> children;
	public Node parent;
	final public boolean hasChildren;

	public Node(String n, List<Node> c, Node p) {
		name = n;
		children = c;
		hasChildren = true;
		parent = p;
	}
	public Node(String n, int u, Node p) {
		name = n;
		utility = u;
		parent = p;
		children = null;
		hasChildren = false;
	}

	public void setParent(Node p) {
		parent = p;
	}

	public Integer getParentUtility() {
		return parent.utility;
	}

	public int getChildrenSize() {
		return children.size();
	}

	public Node getChild(int index) {
		return children.get(index);
	}

	public boolean hasParent() {
		return parent != null;
	}

	public Integer getUtility() {
		return utility;
	}
	public void setUtility(int u) {
		System.out.println("Set node " + name + " " + u);
		utility = u;
	}

	public String toString() {
		return name;
	}
}
