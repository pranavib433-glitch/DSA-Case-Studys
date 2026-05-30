public class RankAVL {

    static class RankNode {
        int key;
        RankNode left, right;
        int size = 1;

        RankNode(int key) {
            this.key = key;
        }
    }

    static int size(RankNode n) {
        return n == null ? 0 : n.size;
    }

    static int rankOf(RankNode root, int key) {
        int rank = 1;

        while (root != null) {
            if (key == root.key) {
                rank += size(root.left);
                return rank;
            }

            if (key > root.key) {
                root = root.left;
            } else {
                rank += size(root.left) + 1;
                root = root.right;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Rank = 6");
    }
}