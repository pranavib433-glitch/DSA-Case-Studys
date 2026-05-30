import java.util.*;

public class CycleDetection {

    static Map<String, List<String>> graph = new HashMap<>();
    static Map<String, Integer> color = new HashMap<>();

    static final int WHITE = 0;
    static final int GREY = 1;
    static final int BLACK = 2;

    static boolean dfs(String node) {

        color.put(node, GREY);

        for (String next : graph.get(node)) {

            if (color.get(next) == GREY) {
                System.out.println("Cycle Detected:");
                System.out.println(node + " -> " + next);
                return true;
            }

            if (color.get(next) == WHITE) {
                if (dfs(next))
                    return true;
            }
        }

        color.put(node, BLACK);
        return false;
    }

    public static void main(String[] args) {

        graph.put("admin-ui", Arrays.asList("payments"));
        graph.put("payments", Arrays.asList("auth"));
        graph.put("auth", Arrays.asList("ledger"));
        graph.put("ledger", Arrays.asList("fraud"));
        graph.put("fraud", Arrays.asList("ledger"));

        for (String node : graph.keySet()) {
            color.put(node, WHITE);
        }

        for (String node : graph.keySet()) {
            if (color.get(node) == WHITE) {
                if (dfs(node))
                    break;
            }
        }
    }
}