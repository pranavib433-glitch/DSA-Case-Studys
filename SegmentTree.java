public class SegmentTree {

    static int[] tree = new int[64];
    static int[] lazy = new int[64];

    static void updateRange(int node, int start, int end, int l, int r, int val) {

        if (lazy[node] != 0) {
            tree[node] += lazy[node];

            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (start > end || start > r || end < l)
            return;

        if (start >= l && end <= r) {
            tree[node] += val;

            if (start != end) {
                lazy[node * 2] += val;
                lazy[node * 2 + 1] += val;
            }
            return;
        }

        int mid = (start + end) / 2;

        updateRange(node * 2, start, mid, l, r, val);
        updateRange(node * 2 + 1, mid + 1, end, l, r, val);

        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }

    static int queryMax(int node, int start, int end, int l, int r) {

        if (start > end || start > r || end < l)
            return Integer.MIN_VALUE;

        if (lazy[node] != 0) {
            tree[node] += lazy[node];

            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (start >= l && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        return Math.max(
                queryMax(node * 2, start, mid, l, r),
                queryMax(node * 2 + 1, mid + 1, end, l, r));
    }

    public static void main(String[] args) {

        updateRange(1, 0, 15, 3, 9, 5);
        updateRange(1, 0, 15, 7, 14, 3);

        System.out.println("Max [0,15] = " +
                queryMax(1, 0, 15, 0, 15));

        updateRange(1, 0, 15, 2, 6, 7);

        System.out.println("Max [4,10] = " +
                queryMax(1, 0, 15, 4, 10));
    }
}