import java.util.*;

class Edge {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

public class BellmanFordPath {

    static int[][] bellmanFordWithPath(int n, List<Edge> edges, int source) {

        int[] dist = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[source] = 0;

        // V-1 relaxations
        for (int iter = 0; iter < n - 1; iter++) {

            boolean changed = false;

            for (Edge e : edges) {

                if (dist[e.u] != Integer.MAX_VALUE &&
                    dist[e.u] + e.weight < dist[e.v]) {

                    dist[e.v] = dist[e.u] + e.weight;
                    parent[e.v] = e.u;
                    changed = true;
                }
            }

            if (!changed)
                break;
        }

        // Negative cycle check
        for (Edge e : edges) {

            if (dist[e.u] != Integer.MAX_VALUE &&
                dist[e.u] + e.weight < dist[e.v]) {

                throw new RuntimeException("Negative Cycle Detected");
            }
        }

        return new int[][] { dist, parent };
    }

    static List<Integer> reconstructPath(int[] parent, int target) {

        LinkedList<Integer> path = new LinkedList<>();

        while (target != -1) {
            path.addFirst(target);
            target = parent[target];
        }

        return path;
    }

    public static void main(String[] args) {

        String[] zones = {
            "IND", "KOR", "MGR",
            "HSR", "BTM", "JPN", "EC"
        };

        int IND = 0;
        int KOR = 1;
        int MGR = 2;
        int HSR = 3;
        int BTM = 4;
        int JPN = 5;
        int EC  = 6;

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(IND, KOR, 8));
        edges.add(new Edge(IND, MGR, 5));
        edges.add(new Edge(KOR, HSR, 7));
        edges.add(new Edge(KOR, BTM, -5));
        edges.add(new Edge(MGR, BTM, 4));
        edges.add(new Edge(HSR, JPN, -3));
        edges.add(new Edge(BTM, JPN, 10));
        edges.add(new Edge(JPN, EC, 8));
        edges.add(new Edge(HSR, EC, 14));
        edges.add(new Edge(MGR, EC, 20));

        int[][] result =
                bellmanFordWithPath(7, edges, IND);

        int[] dist = result[0];
        int[] parent = result[1];

        System.out.println("Shortest Distances:");

        for (int i = 0; i < 7; i++) {
            System.out.println(
                zones[i] + " = " + dist[i]
            );
        }

        System.out.println("\nShortest Path IND -> EC:");

        List<Integer> path =
                reconstructPath(parent, EC);

        for (int i = 0; i < path.size(); i++) {

            System.out.print(zones[path.get(i)]);

            if (i != path.size() - 1)
                System.out.print(" -> ");
        }

        System.out.println(
            "\nTotal Cost = " + dist[EC]
        );
    }
}