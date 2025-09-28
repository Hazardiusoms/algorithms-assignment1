package algos;

import metrics.Metrics;
import util.ArrayUtils;
import util.PartitionUtils;

import java.util.Random;

public class QuickSort {

    private static final int DEFAULT_CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (ArrayUtils.isTrivial(a)) return;
        Random rnd = new Random();
        quickSort(a, 0, a.length, rnd, m, DEFAULT_CUTOFF);
    }

    private static void quickSort(int[] a, int lo, int hi,
                                  Random rnd, Metrics m, int cutoff) {
        while (hi - lo > cutoff) {
            m.pushDepth();

            int pivotIndex = lo + rnd.nextInt(hi - lo);
            int pivot = a[pivotIndex];

            int[] parts = PartitionUtils.partition3(a, lo, hi, pivot, m);
            int lt = parts[0], gt = parts[1];


            if (lt - lo < hi - gt) {
                quickSort(a, lo, lt, rnd, m, cutoff);
                lo = gt;
            } else {
                quickSort(a, gt, hi, rnd, m, cutoff);
                hi = lt;
            }

            m.popDepth();
        }
        insertionSort(a, lo, hi, m);
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i < hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.incComparisons();
                if (a[j] <= key) break;
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}
