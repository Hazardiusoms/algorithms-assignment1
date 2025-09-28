package algos;

import metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPairTest {

    @Test
    void testSimple() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(1, 1)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(5, 0)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);
        assertEquals(5.0, d, 1e-9);
    }

    @Test
    void testAllEqualPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(2, 2)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);
        assertEquals(0.0, d, 1e-9);
    }

    @Test
    void testLargeRandom() {
        java.util.Random rnd = new java.util.Random(42);
        ClosestPair.Point[] pts = new ClosestPair.Point[1000];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new ClosestPair.Point(rnd.nextDouble(), rnd.nextDouble());
        }
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);


        double brute = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                brute = Math.min(brute, Math.hypot(
                        pts[i].x() - pts[j].x(),
                        pts[i].y() - pts[j].y()
                ));
            }
        }
        assertEquals(brute, d, 1e-9);
    }
}
