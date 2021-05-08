import org.junit.Test;
import org.junit.Assert;

public class QuickSortTest {
    @Test
    public void BasicQuickSortOrderTest() {
        int[] randoms = RandomArray.generateRandoms(10_000, -1_000_000, 1_000_000);
        int[] result = BasicQuickSort.sort(randoms);
        for (int i=0; i<result.length-1; i++) {
              Assert.assertTrue(result[i+1]>=result[i]);
        }
    }

    @Test
    public void ParallelQuickSortOrderTest() {
        int[] randoms = RandomArray.generateRandoms(10_000, -1_000_000, 1_000_000);
        int[] result = ParallelQuickSort.sort(randoms, 8);
        for (int i=0; i<result.length-1; i++) {
            Assert.assertTrue(result[i+1]>=result[i]);
        }
    }

    @Test
    public void NoLossTest() {
        final int length = 1000;
        int[] count1 = new int[length];
        int[] count2 = new int[length];
        int[] count3 = new int[length];
        int[] randoms = RandomArray.generateRandoms(10_000, 0, length);
        int[] result = BasicQuickSort.sort(randoms);
        int[] result2 = ParallelQuickSort.sort(randoms, 8);
        Assert.assertNotEquals(randoms,result);
        Assert.assertNotEquals(randoms,result2);
        for (int i=0; i<randoms.length; i++) {
            count1[randoms[i]] += 1;
            count2[result[i]] += 1;
            count3[result2[i]] += 1;
        }
        Assert.assertArrayEquals(count1, count2);
        Assert.assertArrayEquals(count1, count3);
    }
}
