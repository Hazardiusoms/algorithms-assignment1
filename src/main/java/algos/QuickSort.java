package algos;

import metrics.Metrics;

import java.util.Random;

public class QuickSort {

    private static final int DEFAULT_CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        Random rnd = new Random();
        quickSort(a, 0, a.length, rnd, m, DEFAULT_CUTOFF);
    }

    private static void quickSort(int[] a, int lo, int hi,
                                  Random rnd, Metrics m, int cutoff) {
        while (hi - lo > cutoff) {
            m.pushDepth();

            int pivotIndex = lo + rnd.nextInt(hi - lo);
            int pivot = a[pivotIndex];

            int lt = lo, i = lo, gt = hi;
            while (i < gt) {
                m.incComparisons();
                if (a[i] < pivot) {
                    swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    swap(a, i, --gt);
                } else {
                    i++;
                }
            }

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

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
