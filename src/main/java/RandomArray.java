import java.util.Random;

public class RandomArray {
    public static int[] generateRandoms(int arrSize, int minValue, int maxValue) {
        int[] randoms = new int[arrSize];
        Random random = new Random();
        for (int i = 0; i < arrSize; i++) {
            randoms[i] = minValue + random.nextInt(maxValue);
        }
        return randoms;
    }
}
