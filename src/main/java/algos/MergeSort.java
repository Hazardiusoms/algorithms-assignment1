package algos;

import metrics.Metrics;

public class MergeSort {

    private static final int DEFAULT_CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;

        int[] aux = new int[a.length];
        m.incAllocations();
        mergeSort(a, aux, 0, a.length, m, DEFAULT_CUTOFF);
    }

    private static void mergeSort(int[] a, int[] aux, int lo, int hi,
                                  Metrics m, int cutoff) {
        m.pushDepth();

        int n = hi - lo;
        if (n <= cutoff) {
            insertionSort(a, lo, hi, m);
            m.popDepth();
            return;
        }

        int mid = lo + n / 2;
        mergeSort(a, aux, lo, mid, m, cutoff);
        mergeSort(a, aux, mid, hi, m, cutoff);

        merge(a, aux, lo, mid, hi, m);

        m.popDepth();
    }

    private static void merge(int[] a, int[] aux,
                              int lo, int mid, int hi, Metrics m) {
        for (int k = lo; k < hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i >= mid) {
                a[k] = aux[j++];
            } else if (j >= hi) {
                a[k] = aux[i++];
            } else {
                m.incComparisons();
                if (aux[i] <= aux[j]) {
                    a[k] = aux[i++];
                } else {
                    a[k] = aux[j++];
                }
            }
        }
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
