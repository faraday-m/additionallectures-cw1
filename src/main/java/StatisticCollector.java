import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticCollector {
    private static Map<Integer, List<Long>> sortTimer = new HashMap<>();

    public static void addResult(int threads, long time) {
        if (!sortTimer.containsKey(threads)) {
            sortTimer.put(threads, new ArrayList<>());
        }
        sortTimer.get(threads).add(time);
    }

    public static void printStat() {
        Map<Integer, Long> avgs = new HashMap<>();
        for (int i = 0; i < sortTimer.size(); i++) {
            avgs.put(i, sortTimer.get(i).stream().mapToLong(Long::longValue).sum() / sortTimer.get(i).size());
        }
        avgs.entrySet().stream().forEach(e -> System.out.printf("%d threads: %d ms\n", e.getKey(), e.getValue()));
        System.out.println("===");
        avgs.entrySet().stream().forEach(e -> System.out.printf("%d threads: %f faster than basic\n", e.getKey(), (double)avgs.get(0) / e.getValue()));
    }
}
