package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// Represents an edge in the graph.
class Edge {
	public Vertex dest; // Second vertex in Edge
	public double cost; // Edge cost

	public Edge(Vertex d, double c) {
		dest = d;
		cost = c;
	}
}

// Represents a vertex in the graph.
class Vertex {
	public String name;			// Vertex name
	public List<Edge> adj;		// Adjacent vertices
	public double distance;		// the distance between the unkonw source to me
	public Vertex pre;			// to remember the shortest path

	public Vertex(String nm) {
		name = nm;
		adj = new LinkedList<Edge>();
		pre = this;
		distance = 1000000;
	}

}

// Graph class: evaluate shortest paths.
public class Graph {
	public Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

	/*
	 * Add a new edge to the graph.
	 */
	public void addEdge(String sourceName, String destName, double cost) {
		Vertex v = getVertex(sourceName);
		Vertex w = getVertex(destName);
		v.adj.add(new Edge(w, cost));
	}

	/*
	 * If vertexName is not present, add it to vertexMap. In either case, return
	 * the Vertex.
	 */
	private Vertex getVertex(String vertexName) {
		Vertex v = vertexMap.get(vertexName);
		if (v == null) {
			v = new Vertex(vertexName);
			vertexMap.put(vertexName, v);
		}
		return v;
	}

	public Graph(String map) {
		try {
			FileReader fin = new FileReader(map);
			BufferedReader graphFile = new BufferedReader(fin);

			// Read the edges and insert
			String line;
			while ((line = graphFile.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line);
				try {
					if (st.countTokens() != 3) {
						System.err.println("Skipping ill-formatted line " + line);
						continue;
					}
					String source = st.nextToken();
					String dest = st.nextToken();
					int cost = Integer.parseInt(st.nextToken());
					this.addEdge(source, dest, cost);
				} catch (NumberFormatException e) {
					System.err.println("Skipping ill-formatted line " + line);
				}
				// clean up
				fin.close();
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		System.out.println("File read...");
		System.out.println(this.vertexMap.size() + " vertices");
	}
}
