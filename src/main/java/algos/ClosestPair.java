package algos;

import metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosest(Point[] points, Metrics m) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("at least two points required");
        }

        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[points.length];

        return closest(sortedByX, aux, 0, points.length, m);
    }

    private static double closest(Point[] pts, Point[] aux, int lo, int hi, Metrics m) {
        int n = hi - lo;
        if (n <= 3) {
            return bruteForce(pts, lo, hi, m);
        }

        int mid = lo + n / 2;
        double midX = pts[mid].x;

        double d1 = closest(pts, aux, lo, mid, m);
        double d2 = closest(pts, aux, mid, hi, m);
        double d = Math.min(d1, d2);


        mergeByY(pts, aux, lo, mid, hi);


        int stripCount = 0;
        for (int i = lo; i < hi; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                aux[stripCount++] = pts[i];
            }
        }


        for (int i = 0; i < stripCount; i++) {
            for (int j = i + 1; j < stripCount && (aux[j].y - aux[i].y) < d; j++) {
                m.incComparisons();
                d = Math.min(d, distance(aux[i], aux[j]));
            }
        }

        return d;
    }

    private static double bruteForce(Point[] pts, int lo, int hi, Metrics m) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
            for (int j = i + 1; j < hi; j++) {
                m.incComparisons();
                min = Math.min(min, distance(pts[i], pts[j]));
            }
        }
        Arrays.sort(pts, lo, hi, Comparator.comparingDouble(p -> p.y));
        return min;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
            if (pts[i].y <= pts[j].y) {
                aux[k++] = pts[i++];
            } else {
                aux[k++] = pts[j++];
            }
        }
        while (i < mid) aux[k++] = pts[i++];
        while (j < hi) aux[k++] = pts[j++];
        for (i = lo; i < hi; i++) pts[i] = aux[i];
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }


    public static record Point(double x, double y) {}
}
