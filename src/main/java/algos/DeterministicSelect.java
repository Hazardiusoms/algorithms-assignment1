package algos;

import metrics.Metrics;
import util.ArrayUtils;
import util.PartitionUtils;

public class DeterministicSelect {


    public static int select(int[] a, int lo, int hi, int k, Metrics m) {
        if (lo + 1 == hi) return a[lo];
        if (k < lo || k >= hi) throw new IllegalArgumentException("k out of range");

        while (true) {
            m.pushDepth();

            int pivot = medianOfMedians(a, lo, hi, m);
            int[] parts = PartitionUtils.partition3(a, lo, hi, pivot, m);
            int lt = parts[0], gt = parts[1];

            if (k < lt) {
                hi = lt;
            } else if (k >= gt) {
                lo = gt;
            } else {
                m.popDepth();
                return pivot;
            }

            m.popDepth();
        }
    }

    private static int medianOfMedians(int[] a, int lo, int hi, Metrics m) {
        int n = hi - lo;
        if (n <= 5) {
            insertionSort(a, lo, hi, m);
            return a[lo + n / 2];
        }

        int numMedians = 0;
        for (int i = lo; i < hi; i += 5) {
            int subHi = Math.min(i + 5, hi);
            insertionSort(a, i, subHi, m);
            int median = a[i + (subHi - i) / 2];
            ArrayUtils.swap(a, lo + numMedians, i + (subHi - i) / 2);
            numMedians++;
        }
        return medianOfMedians(a, lo, lo + numMedians, m);
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
