package algos;

import metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeterministicSelectTest {

    @Test
    void testMiddleElement() {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        Metrics m = new Metrics();
        int median = DeterministicSelect.select(arr, 0, arr.length, arr.length / 2, m);

        java.util.Arrays.sort(arr);
        assertEquals(arr[arr.length / 2], median);
    }

    @Test
    void testSmallArray() {
        int[] arr = {42};
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(arr, 0, arr.length, 0, m);
        assertEquals(42, result);
    }

    @Test
    void testAllEqual() {
        int[] arr = {5, 5, 5, 5, 5};
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(arr, 0, arr.length, 2, m);
        assertEquals(5, result);
    }

    @Test
    void testRandomLarge() {
        int[] arr = new java.util.Random().ints(1000, -1000, 1000).toArray();
        int k = 500;
        Metrics m = new Metrics();

        int selected = DeterministicSelect.select(arr.clone(), 0, arr.length, k, m);

        java.util.Arrays.sort(arr);
        assertEquals(arr[k], selected);
    }
}
