import java.util.Scanner;
public class DVR {
    static final int INFINITY = 99999;
    int numVertices;
    int[][] cost;       
    int[][] distance;   
    public DVR(int numVertices) {
        this.numVertices = numVertices;
        this.cost = new int[numVertices][numVertices];
        this.distance = new int[numVertices][numVertices];
    }
    public void initializeMatrices() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    cost[i][j] = 0;
                } else {
                    cost[i][j] = INFINITY;
                }
            }
        }
    }
    public void addEdge(int source, int destination, int cost) {
        source--;
        destination--;
        this.cost[source][destination] = cost;
        this.cost[destination][source] = cost; 
    }
    public void runDVR() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                distance[i][j] = cost[i][j];
            }
        }
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (i == j) continue;
                    for (int k = 0; k < numVertices; k++) {
                        if (cost[i][k] != INFINITY) { 
                            int newDist = cost[i][k] + distance[k][j];
                            if (newDist < distance[i][j]) {
                                distance[i][j] = newDist;
                                changed = true;
                            }
                        }
                    }
                }
            }
        } while (changed);
    }
    public void printRoutingTables() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print("Dist: " + distance[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the number of Vertices:");
        int numVertices = sc.nextInt();
        
        DVR dvr = new DVR(numVertices);
        dvr.initializeMatrices();

        System.out.println("Please enter the number of Edges:");
        int numEdges = sc.nextInt();

        for (int i = 0; i < numEdges; i++) {
            System.out.println("Please enter data for Edge " + (i + 1) + ":");
            System.out.println("Source:");
            int source = sc.nextInt();
            System.out.println("Destination:");
            int dest = sc.nextInt();
            System.out.println("Cost:");
            int cost = sc.nextInt();
            dvr.addEdge(source, dest, cost);
        }
        dvr.runDVR();

        System.out.println("\nThe initial Routing Tables are:");
        dvr.printRoutingTables();

        System.out.println("\nPlease enter the Source Node for the edge whose cost has changed:");
        int changeSource = sc.nextInt();
        System.out.println("Please enter the Destination Node for the edge whose cost has changed:");
        int changeDest = sc.nextInt();
        System.out.println("Please enter the new cost:");
        int newCost = sc.nextInt();
        dvr.addEdge(changeSource, changeDest, newCost);
        dvr.runDVR();
        System.out.println("\nThe new Routing Tables are:");
        dvr.printRoutingTables();
        sc.close();
    }
}
