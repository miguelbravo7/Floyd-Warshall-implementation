
class AllPairShortestPath {
	//final 
	static int INF = 999999, V = 4;
	int dist[][] = new int[V][V];
	int recorridos[][] = new int[V][V];

	// A utility function to find the vertex with minimum distance value,
	// from the set of vertices not yet included in shortest path tree
	int minDistance(int dist[], Boolean sptSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	// Funtion that implements Dijkstra's single source shortest path
	// algorithm for a graph represented using adjacency matrix
	// representation
	void dijkstra(int graph[][], int src) {
		int dist[] = new int[V]; // The output array. dist[i] will hold
									// the shortest distance from src to i

		// sptSet[i] will true if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized
		Boolean sptSet[] = new Boolean[V];

		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		// Distance of source vertex from itself is always 0
		dist[src] = 0;

		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices
			// not yet processed. u is always equal to src in first
			// iteration.
			int u = minDistance(dist, sptSet);

			// Mark the picked vertex as processed
			sptSet[u] = true;

			// Update dist value of the adjacent vertices of the
			// picked vertex.
			for (int v = 0; v < V; v++)

				// Update dist[v] only if is not in sptSet, there is an
				// edge from u to v, and total weight of path from src to
				// v through u is smaller than current value of dist[v]
				if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}

		// print the constructed distance array
		//printSolutionDijk(dist, V);
	}

	// A utility function to print the constructed distance array
	void printSolutionDijk(int dist[], int n) {
		System.out.println("Vertex   Distance from Source");
		for (int i = 0; i < V; i++)
			System.out.println(i + " tt " + dist[i]);
	}

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

//		System.out.println("PRE");
//		printSolution(graph, recorridos);
		/*
		 * Initialize the solution matrix same as input graph matrix. Or we can say the
		 * initial values of shortest distances are based on shortest paths considering
		 * no intermediate vertex.
		 */
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];

		//traceRoute(0, 1, dist);
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
//		System.out.println("POST");
//		printSolution(dist, recorridos);
//		traceRoute(0, 1, dist);
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
		
//		int grafo[][] = { { 0, 15, 10, INF },
//						  { INF, 0, INF, 17 },
//						  { INF, 1, 0, INF },
//						  { 16, INF, 6, 0 } };
		for (int j = 2560; j < 30000; j*=2) {
			V=j;
			System.out.println("nodos "+V+" arcos "+V*2+":");
			Graph grafo = new Graph(V);
			grafo.genRandArcs(2 * V);
			// System.out.println(grafo);
			AllPairShortestPath a = new AllPairShortestPath();

			// Compute the solution
			
			Clock temporizador = new Clock().start();
			a.floydWarshall(grafo.toArray());
			temporizador.stop();
			System.out.println("\tFloyd-Warshall :"+temporizador);
			
			Clock temporizador2 = new Clock().start();
			for (int i = 0; i < V; i++) {
				a.dijkstra(grafo.toArray(), i);
			}
			temporizador2.stop();
			System.out.println("\tDijkstra :      "+temporizador2);
		}
		
		
	}
}