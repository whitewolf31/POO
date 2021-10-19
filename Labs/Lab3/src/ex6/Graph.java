package ex6;

public class Graph {
    private int graphMatrix[][];
    private final int Infinite = 9500;
    private int nodeNumber;

    public Graph(int nodeNumber) {
        this.nodeNumber = nodeNumber;
        graphMatrix = new int[nodeNumber + 1][nodeNumber + 1];
    }

    public int getSize() {
        return nodeNumber;
    }

    public void addArc(int v, int w, int cost){
        if (v > nodeNumber || w > nodeNumber) return;
        graphMatrix[v][w] = cost;
        graphMatrix[w][v] = cost;
    }

    public boolean isArc(int v, int w) {
        return graphMatrix[v][w] > 0;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 1; i <= nodeNumber; i++) {
            for (int j = i + 1; j <= nodeNumber; j++) {
                if (graphMatrix[i][j] > 0) {
                    s.append("[" + i + ", " + j + "]" + " Cost: " + graphMatrix[i][j] + "\n");
                }
            }
        }

        return s.toString();
    }

    public int[][] floydWarshall() {
        int result[][];
        result = new int[nodeNumber+1][nodeNumber+1];
        int k, i, j;
        for(i = 1; i <= nodeNumber; i++) {
            for(j = 1; j <= nodeNumber; j++) {
                if(i == j) {
                    result[i][j] = 0;
                } else if(isArc(i, j)) {
                    result[i][j] = graphMatrix[i][j];
                } else {
                    result[i][j] = Infinite;
                }
            }
        }
        for(k = 1; k <= nodeNumber; k++) {
            for(i = 1; i <= nodeNumber; i++) {
                for(j = 1; j <= nodeNumber; j++) {
                    int dist;
                    dist = result[i][k] + result[k][j];
                    if(result[i][j] > dist) {
                        result[i][j] = dist;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);
        g.addArc(1, 3, 2);
        g.addArc(1, 2, 3);
        g.addArc(2, 4, 6);
        g.addArc(2, 3, 2);
        System.out.println(g);
        System.out.println("Floyd-Warshall");
        int [][] my_matrix = g.floydWarshall();
        System.out.println("distanta minima dintre nodurile 1 si 4 este "+ my_matrix[1][4]); // rezultat - 9
    }
}
