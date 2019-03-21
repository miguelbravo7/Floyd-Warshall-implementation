
class AllPairShortestPath {
	final static int INF = 999999, V = 4;
	int dist[][] = new int[V][V];
	int recorridos[][] = new int[V][V];

	void floydWarshall(int graph[][]) {
		int i, j, k;

		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++) {
				if (i == j) {
					recorridos[i][j] = -1;
				} else {
					recorridos[i][j] = j;
				}
			}

		System.out.println("PRE");
		printSolution(graph, recorridos);
		/*
		 * Initialize the solution matrix same as input graph matrix. Or we can say the
		 * initial values of shortest distances are based on shortest paths considering
		 * no intermediate vertex.
		 */
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];

		traceRoute(0, 1, dist);
		/*
		 * Add all vertices one by one to the set of intermediate vertices. ---> Before
		 * start of an iteration, we have shortest distances between all pairs of
		 * vertices such that the shortest distances consider only the vertices in set
		 * {0, 1, 2, .. k-1} as intermediate vertices. ----> After the end of an
		 * iteration, vertex no. k is added to the set of intermediate vertices and the
		 * set becomes {0, 1, 2, .. k}
		 */
		for (k = 0; k < V; k++) {
			// Pick all vertices as source one by one
			for (i = 0; i < V; i++) {
				// Pick all vertices as destination for the
				// above picked source
				for (j = 0; j < V; j++) {
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						recorridos[i][j] = k;
					}
				}
			}
		}

		// Print the shortest distance matrix
		System.out.println("POST");
		printSolution(dist, recorridos);
		traceRoute(0, 1, dist);
	}

	public void traceRoute(int start, int end, int cost[][]) {
		System.out.println("Camino de " + start + " a " + end + ":");
		if (dist[start][end] == INF) {
			System.out.print("\tNo existe un camino de " + start + " a " + end);
		} else {
			System.out.print("\t|" + start);
			
			while (recorridos[start][end] != end && recorridos[start][end] != -1) {
				System.out.print("| --" + cost[start][end] + "--> |" + recorridos[start][end]);
				start = recorridos[start][end] != -1 ? recorridos[start][end] : start;
			}
			
			System.out.print("| --" + cost[start][end] + "--> |" + recorridos[start][end] + "|");
		}
		System.out.println();
	}

	static void printSolution(int dist[][], int trace[][]) {
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				if (dist[i][j] == INF)
					System.out.print(" INF");
				else
					System.out.print(String.format("%1$4s", dist[i][j]));
			}
			System.out.print("\t|\t");
			for (int j = 0; j < V; ++j) {
				System.out.print(String.format("%1$4s", trace[i][j]));
			}
			System.out.println();
		}
	}

	// Driver program to test above function
	public static void main(String[] args) {
        int grafo[][] = { {0,   15,  10, INF}, 
                          {INF, 0,   INF, 17}, 
                          {INF, 1, 0,   INF}, 
                          {16, INF, 6, 0} 
                        }; 
//		Graph grafo = new Graph(V);
//		grafo.genRandArcs(2 * V);
//		System.out.println(grafo);.toArray()
		AllPairShortestPath a = new AllPairShortestPath();

		// Print the solution
		a.floydWarshall(grafo);
	}
}