public class Main {
    private static int ARR_SIZE = 10_000_000;

    public static void main(String[] args) {
        int[] testArr = RandomArray.generateRandoms(ARR_SIZE, -1_000_000, 1_000_000);
        for (int i = 0; i < 10; i++) {
            BasicQuickSort.sort(testArr);
            ParallelQuickSort.sort(testArr, 1);
            ParallelQuickSort.sort(testArr, 2);
            ParallelQuickSort.sort(testArr, 3);
            ParallelQuickSort.sort(testArr, 4);
        }
        StatisticCollector.printStat();
    }
}
