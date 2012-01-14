import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;


public class TestGraph {
	private static Graph g = new Graph("distance.txt");
	private static PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(g.vertexMap.size(), new java.util.Comparator<Vertex>() {
		@Override
		public int compare(Vertex o1, Vertex o2) {
			return (int)(o1.distance - o2.distance);
		}
	});
	private static Set<Vertex> closeSet = new HashSet<Vertex>();

	public static void findPath(Vertex source, Vertex destination) {
		Vertex current;
		Vertex neighbor;
		source.distance = 0;
		for(Edge e : source.adj) {
			e.dest.distance = e.cost;
			e.dest.pre = source;
			queue.add(e.dest);
		}
		while (!queue.isEmpty()) {
			current = queue.poll();
			if(current.name == destination.name) {
				break;
			}
			for (int i = 0; i < current.adj.size(); i++) {
				// each path is in bidirection, so it is possible that the
				// neighbor we want to analyze has been removed from the queue
				neighbor = current.adj.get(i).dest;
				if (!closeSet.contains(neighbor)) {
					if (queue.contains(neighbor)) {
						if (neighbor.distance > current.distance + current.adj.get(i).cost) {
							// we need to update the value of distance, first remove this item, then add it afterwards
							queue.remove(neighbor);
							neighbor.distance = current.distance + current.adj.get(i).cost;
							queue.add(neighbor);

							// it is easier to trace back the path
							neighbor.pre = current;
						}
					} else {
						neighbor.distance = current.distance + current.adj.get(i).cost;
						queue.add(neighbor);
						// it is easier to trace back the path
						neighbor.pre = current;
					}
				}
				/*
				if (!closeSet.contains(neighbor)) {
					// This version works as well, for I have set the default
					// value of distance to be very big. Therefore, the vertex
					// are added to the queue anyway.
					if (neighbor.distance > current.distance + current.adj.get(i).cost) {
						// we need to update the value of distance, first remove this item, then add it afterwards
						queue.remove(neighbor);
						neighbor.distance = current.distance + current.adj.get(i).cost;
						queue.add(neighbor);

						// it is easier to trace back the path
						neighbor.pre = current;
					}
				}
				*/
			}
			closeSet.add(current);
		}
	}

	public static class Test {
		public static void main(String[] args) {
			Vertex sou, des;
			if (args.length < 3) {
				sou = g.vertexMap.get("Kalbo");
				des = g.vertexMap.get("Långvik");
				System.err.println("The number of argument should be three.");
				System.out.println("I will use " + sou.name + " -> " + des.name + " instead.");
			} else {
				sou = g.vertexMap.get(args[0]);
				des = g.vertexMap.get(args[1]);
			}

			TestGraph.findPath(sou, des);
			ArrayList<String> list = new ArrayList<String>();
			Vertex tmp = des;
			while (!tmp.pre.name.equals(sou.name)) {
				list.add(tmp.pre.name);
				tmp = tmp.pre;
			}
			System.out.print(sou.name + "->");
			for (int i = list.size() - 1; i >= 0; i--) {
				System.out.print(list.get(i) + "->");
			}
			System.out.println(des.name);
			System.out.println(des.distance);
		}
	}
}
