import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort {
    public static class ParallelRunner extends RecursiveAction {
        public int[] input;
        public int l;
        public int h;

        public ParallelRunner(int[] input, int l, int h) {
            this.input = input;
            this.l = l;
            this.h = h;
        }

        private int partition(int[] input, int l, int h) {
            double pivot = (input[l]+input[h]) / 2.0;
            int i = l;
            int j = h;
            while (true) {
                while (input[i] < pivot) {
                    i += 1;
                }
                while (input[j] > pivot) {
                    j -= 1;
                }
                if (i >= j) {
                    return j;
                }
                int tmp = input[i];
                input[i++] = input[j];
                input[j--] = tmp;
            }
        }

        public void compute() {
            if (l < h) {
                int p = partition(input, l, h);
                invokeAll(new ParallelRunner(input,l,p), new ParallelRunner(input,p+1,h));
            }
        }
    }

    private static ForkJoinPool pool;

    public static int[] sort(int[] input, int threadCount) {
        pool = new ForkJoinPool(threadCount);
        int[] sorted = Arrays.copyOf(input, input.length);
        long time1 = System.currentTimeMillis();
        pool.invoke(new ParallelRunner(sorted, 0, sorted.length - 1));
        long time2 = System.currentTimeMillis();
        System.out.println("Parallel sort with " + threadCount + " threads: " + (time2 - time1) + " ms");
        StatisticCollector.addResult(threadCount,time2-time1);
        return sorted;
    }
}
