import java.util.*;

public class KWayMergeDemo {

    static class HeapEntry {
        long value;
        int chunkId;
        int index;

        HeapEntry(long value, int chunkId, int index) {
            this.value = value;
            this.chunkId = chunkId;
            this.index = index;
        }
    }

    public static void kWayMerge(List<long[]> chunks) {

        PriorityQueue<HeapEntry> heap =
                new PriorityQueue<>((a, b) ->
                        Long.compare(a.value, b.value));

        // TODO 1: Seed heap with first element of each chunk
        for (int i = 0; i < chunks.size(); i++) {
            if (chunks.get(i).length > 0) {
                heap.offer(
                    new HeapEntry(
                        chunks.get(i)[0],
                        i,
                        0
                    )
                );
            }
        }

        List<Long> output = new ArrayList<>();

        while (!heap.isEmpty()) {

            HeapEntry e = heap.poll();

            output.add(e.value);

            // TODO 2: Push next element from same chunk
            int nextIndex = e.index + 1;

            if (nextIndex < chunks.get(e.chunkId).length) {

                heap.offer(
                    new HeapEntry(
                        chunks.get(e.chunkId)[nextIndex],
                        e.chunkId,
                        nextIndex
                    )
                );
            }
        }

        System.out.println("Merged Output:");
        for (long x : output) {
            System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {

        List<long[]> chunks = new ArrayList<>();

        chunks.add(new long[]{10, 25, 47, 89});
        chunks.add(new long[]{12, 30, 51, 75});
        chunks.add(new long[]{8, 19, 41, 92});
        chunks.add(new long[]{15, 33, 60, 80});
        chunks.add(new long[]{22, 38, 55, 88});
        chunks.add(new long[]{11, 28, 49, 85});

        kWayMerge(chunks);
    }
}