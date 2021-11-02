package ex2;

public class Graph {
    private final MyList[] adjList;
    private final boolean[] visited;
    private final int nodeNumber;

    public Graph(int x) {
        adjList = new MyList[x + 1];
        visited = new boolean[x + 1];
        nodeNumber = x + 1;
        for (int i = 1; i < nodeNumber; i++) {
            adjList[i] = new MyList();
            adjList[i].add(i);
            visited[i] = false;
        }
    }

    public void add(int x, int y) {
        adjList[x].add(y);
    }

    public void dfs(int currentNode) {
        visited[currentNode] = true;
        System.out.println("Visited node: " + currentNode);
        for (int i = 0; i < adjList[currentNode].size(); i++) {
            int nextNode = (int) adjList[currentNode].get(i);
            if (!visited[nextNode]) {
                dfs(nextNode);
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 1; i < nodeNumber; i++) {
            s.append(i + ": " + adjList[i].toString() + '\n');
        }

        return s.toString();
    }

}
