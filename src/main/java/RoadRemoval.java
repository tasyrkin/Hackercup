import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class RoadRemoval {

	public static class Bag<Item> implements Iterable<Item> {
		private int N; // number of elements in bag
		private Node first; // beginning of bag

		// helper linked list class
		private class Node {
			private Item item;
			private Node next;
		}

		/**
		 * Create an empty stack.
		 */
		public Bag() {
			first = null;
			N = 0;
			assert check();
		}

		/**
		 * Is the BAG empty?
		 */
		public boolean isEmpty() {
			return first == null;
		}

		/**
		 * Return the number of items in the bag.
		 */
		public int size() {
			return N;
		}

		/**
		 * Add the item to the bag.
		 */
		public void add(Item item) {
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.next = oldfirst;
			N++;
			assert check();
		}

		// check internal invariants
		private boolean check() {
			if (N == 0) {
				if (first != null)
					return false;
			} else if (N == 1) {
				if (first == null)
					return false;
				if (first.next != null)
					return false;
			} else {
				if (first.next == null)
					return false;
			}

			// check internal consistency of instance variable N
			int numberOfNodes = 0;
			for (Node x = first; x != null; x = x.next) {
				numberOfNodes++;
			}
			if (numberOfNodes != N)
				return false;

			return true;
		}

		/**
		 * Return an iterator that iterates over the items in the bag.
		 */
		public Iterator<Item> iterator() {
			return new ListIterator();
		}

		// an iterator, doesn't implement remove() since it's optional
		private class ListIterator implements Iterator<Item> {
			private Node current = first;

			public boolean hasNext() {
				return current != null;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			public Item next() {
				if (!hasNext())
					throw new NoSuchElementException();
				Item item = current.item;
				current = current.next;
				return item;
			}
		}

	}

	public static class Digraph {
		private final int V;
		private int E;
		private Bag<Integer>[] adj;

		/**
		 * Create an empty digraph with V vertices.
		 */
		public Digraph(int V) {
			if (V < 0)
				throw new RuntimeException(
						"Number of vertices must be nonnegative");
			this.V = V;
			this.E = 0;
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
		}

		/**
		 * Copy constructor.
		 */
		public Digraph(Digraph G) {
			this(G.V());
			this.E = G.E();
			for (int v = 0; v < G.V(); v++) {
				// reverse so that adjacency list is in same order as original
				Stack<Integer> reverse = new Stack<Integer>();
				for (int w : G.adj[v]) {
					reverse.push(w);
				}
				for (int w : reverse) {
					adj[v].add(w);
				}
			}
		}

		/**
		 * Return the number of vertices in the digraph.
		 */
		public int V() {
			return V;
		}

		/**
		 * Return the number of edges in the digraph.
		 */
		public int E() {
			return E;
		}

		/**
		 * Add the directed edge v-w to the digraph.
		 */
		public void addEdge(int v, int w) {
			adj[v].add(w);
			E++;
		}

		/**
		 * Return the list of neighbors of vertex v as in Iterable.
		 */
		public Iterable<Integer> adj(int v) {
			return adj[v];
		}

		/**
		 * Return the reverse of the digraph.
		 */
		public Digraph reverse() {
			Digraph R = new Digraph(V);
			for (int v = 0; v < V; v++) {
				for (int w : adj(v)) {
					R.addEdge(w, v);
				}
			}
			return R;
		}

		/**
		 * Return a string representation of the digraph.
		 */
		public String toString() {
			StringBuilder s = new StringBuilder();
			String NEWLINE = System.getProperty("line.separator");
			s.append(V + " " + E + NEWLINE);
			for (int v = 0; v < V; v++) {
				s.append(v + ": ");
				for (int w : adj[v]) {
					s.append(w + " ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
		}

	}

	public static class TarjanSCC {
		private boolean[] marked; // marked[v] = has v been visited?
		private int[] id; // id[v] = id of strong component containing v
		private int[] low; // low[v] = low number of v
		private int pre; // preorder number counter
		private int count; // number of strongly-connected components
		private Stack<Integer> stack;

		public TarjanSCC(Digraph G) {
			marked = new boolean[G.V()];
			stack = new Stack<Integer>();
			id = new int[G.V()];
			low = new int[G.V()];
			for (int v = 0; v < G.V(); v++) {
				if (!marked[v])
					dfs(G, v);
			}

			// check that id[] gives strong components
			// assert check(G);
		}

		private void dfs(Digraph G, int v) {
			marked[v] = true;
			low[v] = pre++;
			int min = low[v];
			stack.push(v);
			for (int w : G.adj(v)) {
				if (!marked[w])
					dfs(G, w);
				if (low[w] < min)
					min = low[w];
			}
			if (min < low[v]) {
				low[v] = min;
				return;
			}
			int w;
			do {
				w = stack.pop();
				id[w] = count;
				low[w] = G.V();
			} while (w != v);
			count++;
		}

		// return the number of strongly connected components
		public int count() {
			return count;
		}

		// are v and w strongly connected?
		public boolean stronglyConnected(int v, int w) {
			return id[v] == id[w];
		}

		// in which strongly connected component is vertex v?
		public int id(int v) {
			return id[v];
		}
	}

	public static void main(String[]args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++){
			String[]parts = br.readLine().split("\\s+");
			int n = Integer.parseInt(parts[0]);
			int m = Integer.parseInt(parts[1]);
			int k = Integer.parseInt(parts[2]);
			Digraph graph = new Digraph(n);
			for(int j = 0; j < m; j++){
				parts = br.readLine().split("\\s+");
				int v = Integer.parseInt(parts[0]);
				int w = Integer.parseInt(parts[1]);
				graph.addEdge(v, w);
				graph.addEdge(w, v);
			}
			TarjanSCC tscc = new TarjanSCC(graph);
			System.out.println(tscc.count);
//			System.out.println("Case #" + (i+1) + ": " );
		}	
	}
}