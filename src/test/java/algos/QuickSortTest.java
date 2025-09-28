package algos;

import metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        java.util.Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testAllEqual() {
        int[] arr = {7, 7, 7, 7};
        int[] expected = arr.clone();

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }
}
