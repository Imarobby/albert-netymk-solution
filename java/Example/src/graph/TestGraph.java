package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class TestGraph {
	private static Graph g = new Graph("distance1.txt");
	private static PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(g.vertexMap.size(), new Compare());

	/*
	 * Put every vertex in the queue
	 */
	private static void fillQueue() {
		Iterator<String> iterator = g.vertexMap.keySet().iterator();
		while (iterator.hasNext()) {
			queue.add(g.vertexMap.get(iterator.next()));
		}
	}

	public static void findPath(Vertex source, Vertex destination) {
		Vertex current;
		Vertex neighbor;
		source.distance = 0;
		TestGraph.fillQueue();
		// while (!queue.isEmpty()) { It is not necessary to finish searching.
		while (queue.contains(destination)) {
			current = queue.poll();
			for (int i = 0; i < current.adj.size(); i++) {
				// each path is in bidirection, so it is possible that the neighbor we want to analyze has been removed from the queue
				neighbor = current.adj.get(i).dest;
				if (queue.contains(neighbor)) {
					if (neighbor.distance > current.distance + current.adj.get(i).cost) {
						// we need to update the value of distance, first remove this item, then add it afterwards
						queue.remove(neighbor);
						neighbor.distance = current.distance + current.adj.get(i).cost;
						queue.add(neighbor);

						// it is easier to trace back the path
						neighbor.pre = current;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("The number of argument should be three.");
			System.exit(8);
		}
		Vertex sou = g.vertexMap.get(args[0]);
		Vertex des = g.vertexMap.get(args[1]);
		TestGraph.findPath(sou, des);
		ArrayList<String> list = new ArrayList<String>();
		Vertex tmp = des;
		while (!tmp.pre.name.equals(sou.name)) {
			list.add(tmp.pre.name);
			tmp = tmp.pre;
		}
		System.out.print(args[0] + " ");
		for (int i = list.size() - 1; i >= 0; i--) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println(args[1]);
		System.out.println(des.distance);
	}
}

class Compare implements java.util.Comparator<Vertex> {

	@Override
	public int compare(Vertex o1, Vertex o2) {
		if (o1.distance < o2.distance)
			return -1;
		else if (o1.distance > o2.distance)
			return 1;
		else
			return 0;
	}
}
