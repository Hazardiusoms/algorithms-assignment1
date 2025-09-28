package util;

import metrics.Metrics;

public class PartitionUtils {

    public static int[] partition3(int[] a, int lo, int hi, int pivot, Metrics m) {
        int lt = lo, i = lo, gt = hi;
        while (i < gt) {
            m.incComparisons();
            if (a[i] < pivot) {
                ArrayUtils.swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                ArrayUtils.swap(a, i, --gt);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }
}
