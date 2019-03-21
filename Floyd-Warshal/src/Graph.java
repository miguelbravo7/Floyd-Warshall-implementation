import java.util.ArrayList;
import java.util.Random;

public class Graph {
	final static int INF = 999999;

	Node[] _nodos;

	public static void main(String[] args) {
		Graph grafo = new Graph(4);
		grafo.genRandArcs(6);
		System.out.println(grafo);
	}

	public Graph(int num_nodes) {
		_nodos = new Node[num_nodes];
		for (int i = 0; i < num_nodes; i++) {
			_nodos[i] = new Node(i);
		}
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < _nodos.length; i++) {
			output += _nodos[i];
		}
		return output;
	}

	public void genRandArcs(int arcs) {
		Random random = new Random();
		for (int i = 0; i <= arcs; i++) {
			int index = random.nextInt(_nodos.length);
			int node;
			do {
				node = Math.abs(random.nextInt(_nodos.length));
			} while (index == node);
			_nodos[index].addNode(node, Math.abs(random.nextInt() % 20));
		}
	}

	public int[][] toArray() {
		int[][] map = new int[_nodos.length][_nodos.length];
		for (int i = 0; i < _nodos.length; i++) {
			ArrayList<Integer> pos = _nodos[i].get_joint();
			ArrayList<Integer> cost = _nodos[i].get_cost();
			for (int j = 0; j < pos.size(); j++) {
				map[i][pos.get(j)] = cost.get(j);
			}
			for (int j = 0; j < _nodos.length; j++) {
				map[i][j] = map[i][j] == 0 ? INF : map[i][j];
			}
			map[i][i] = 0;
		}
		return map;
	}
}
