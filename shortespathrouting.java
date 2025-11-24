import java.util.Scanner;

public class shortespathrouting {

    static final int INFINITY = 99999;
    
    private int numNodes;
    private int[][] costMatrix;
    private int[] distance;
    private boolean[] visited;

    public shortespathrouting(int numNodes) {
        this.numNodes = numNodes;
        this.costMatrix = new int[numNodes][numNodes];
        this.distance = new int[numNodes];
        this.visited = new boolean[numNodes];
    }

    public void readCostMatrix(Scanner sc) {
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                costMatrix[i][j] = sc.nextInt();
                if (i != j && costMatrix[i][j] == 0) {
                    costMatrix[i][j] = INFINITY;
                }
            }
        }
    }

    public void runDijkstra(int sourceNode) {
        sourceNode--;
        for (int i = 0; i < numNodes; i++) {
            distance[i] = INFINITY;
            visited[i] = false;
        }
        distance[sourceNode] = 0;
        for (int count = 0; count < numNodes; count++) {
            int u = findMinDistanceNode();
            if (u == -1) break;
            visited[u] = true;
            for (int v = 0; v < numNodes; v++) {
                if (!visited[v] && costMatrix[u][v] != INFINITY &&
                        distance[u] + costMatrix[u][v] < distance[v]) {
                    
                    distance[v] = distance[u] + costMatrix[u][v];
                }
            }
        }
    }

    private int findMinDistanceNode() {
        int min = INFINITY;
        int minIndex = -1;

        for (int v = 0; v < numNodes; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public void printShortestPaths(int sourceNode) {
        System.out.println("The Shortest Path from Source \t" + sourceNode + "\t to all other vertices are : \n");

        for (int i = 0; i < numNodes; i++) {
            if (i != (sourceNode - 1)) {
                System.out.println("source :" + sourceNode + "\t destination :" + (i + 1) + "\t MinCost is :" + distance[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Number of Nodes");
        int numNodes = sc.nextInt();

        shortespathrouting lsr = new shortespathrouting(numNodes);

        System.out.println("Enter the Cost Matrix Weights: ");
        lsr.readCostMatrix(sc);

        System.out.println("Enter the Source Vertex :");
        int sourceVertex = sc.nextInt();
        lsr.runDijkstra(sourceVertex);
        lsr.printShortestPaths(sourceVertex);

        sc.close();
    }
}







