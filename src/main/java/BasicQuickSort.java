import java.util.Arrays;

public class BasicQuickSort {
    private static int partition(int[] input, int l, int h) {
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

    private static void sortArray(int[] input, int l, int h) {
        if (l < h) {
            int p = partition(input, l, h);
            sortArray(input, l, p);
            sortArray(input, p+1, h);
        }
    }

    public static int[] sort(int[] input) {
        int[] sorted = Arrays.copyOf(input, input.length);
        long time1 = System.currentTimeMillis();
        sortArray(sorted, 0, sorted.length-1);
        long time2 = System.currentTimeMillis();
        System.out.println("Basic sort: " + (time2-time1) + " ms");
        StatisticCollector.addResult(0,time2-time1);
        return sorted;
    }
}
