import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Monopoly {

	public static class Tree {

		public static int maxNumChildren;
		
		private static class Node {
			Integer elem;
			Node parent;
			int maxNumChildren = 0;
			int numChildren = 0;
			Node[] children;

			Node(Integer elem, Node parent, int maxNumChildren) {
				this.elem = elem;
				this.parent = parent;
				this.maxNumChildren = maxNumChildren;
				this.children = new Node[this.maxNumChildren];
			}
			
			public Node getChild(int i) {
				return children[i];
			}

			public void addChild(int child) {
				Node childNode = new Node(child, this, Tree.maxNumChildren);
				children[numChildren++] = childNode;
			}
		}

		Node root = null;

		private Node createRootNode(Integer elem) {
			return new Node(elem, null, Tree.maxNumChildren);
		}

		public Tree(int elem) {
			root = createRootNode(elem);
		}

		public Node root() {
			return root;
		}

		public Node parent(Node v) {
			return v.parent;
		}

		public Node get(Integer element) {
			if(root == null){
				return null;
			}
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root);
			while(!queue.isEmpty()){
				Node p = queue.poll();
				if(p.elem == element){
					return p;
				}
				for(int i = 0; i < p.numChildren; i++){
					queue.add(p.children[i]);
				}
			}
			return null;
		}
			
		public boolean add(Set<Integer> toWhich, Integer element) {
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root);
			while(!queue.isEmpty()){
				Node p = queue.poll();
				if(toWhich.contains(p.elem) && p.numChildren < p.maxNumChildren){
					Node newChild = new Node(element, p, p.maxNumChildren);
//					p.addChild(newChild);
					return true;
				}
				for(int i = 0; i < p.numChildren; i++){
					queue.add(p.children[i]);
				}
			}
			return false;
		}
		
		
		public int height(){
			if(root == null)return 0;
			Queue<Pair> queue = new LinkedList<Pair>();
			queue.add(new Pair(root, 0));
			int res = Integer.MIN_VALUE;
			while(!queue.isEmpty()){
				Pair p = queue.poll();
				if(p.level > res) res = p.level;
				for(int i = 0; i < p.node.numChildren; i++){
					queue.add(new Pair(p.node.children[i], p.level+1));
				}
			}
			return res;
		}

		private static class Pair{
			Node node;
			int level;
			Pair(Node node, int level){
				this.node = node;
				this.level = level;
			}
		}
		
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++){
			br.readLine();
			String[]parts = br.readLine().split("\\s+");
			int n = Integer.parseInt(parts[0]);
			int d = Integer.parseInt(parts[1]);
			Tree.maxNumChildren = d;
			Map<Integer, Set<Integer>> merged = new HashMap<Integer, Set<Integer>>();
			Tree tree = null;
			for(int j = 0; j < n-1; j++){
				parts = br.readLine().split("\\s+");
				int m1 = Integer.parseInt(parts[0]);
				int m2 = Integer.parseInt(parts[1]);
				if(j == 0){
					tree = new Tree(m1);
					tree.root.addChild(m2);
					Set<Integer> set = new HashSet<Integer>();
					set.add(m1);
					set.add(m2);
					merged.put(m1, set);
				} else {
					Monopoly.Tree.Node nodeM1 = tree.get(m1);
					if(nodeM1 == null){
						Monopoly.Tree.Node nodeM2 = tree.get(m2);
						
					}
				}
			}
			
			System.out.println("Case #" + (i+1) + ": ");
		}
	}

}
