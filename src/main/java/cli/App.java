package cli;

import algos.MergeSort;
import algos.QuickSort;
import algos.DeterministicSelect;
import algos.ClosestPair;
import algos.ClosestPair.Point;
import metrics.Metrics;
import metrics.MetricsCSVWriter;

import java.io.IOException;
import java.util.Random;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java -jar app.jar <algo> <size> [trials]");
            System.err.println("Algos: mergesort | quicksort | select | closest");
            return;
        }

        String algo = args[0].toLowerCase();
        int size = Integer.parseInt(args[1]);
        int trials = (args.length >= 3) ? Integer.parseInt(args[2]) : 1;

        MetricsCSVWriter writer = new MetricsCSVWriter("results.csv");

        for (int t = 0; t < trials; t++) {
            Metrics m = new Metrics();

            if (algo.equals("mergesort")) {
                int[] arr = randomArray(size);
                MergeSort.sort(arr, m);

            } else if (algo.equals("quicksort")) {
                int[] arr = randomArray(size);
                QuickSort.sort(arr, m);

            } else if (algo.equals("select")) {
                int[] arr = randomArray(size);
                int k = size / 2;
                DeterministicSelect.select(arr, 0, arr.length, k, m);

            } else if (algo.equals("closest")) {
                Point[] pts = randomPoints(size);
                ClosestPair.findClosest(pts, m);

            } else {
                System.err.println("Unknown algo: " + algo);
                return;
            }

            writer.writeRow(algo, size, m);
        }

        writer.close();
        System.out.println("✅ Done. Results saved to results.csv");
    }

    private static int[] randomArray(int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(1_000_000);
        return arr;
    }

    private static Point[] randomPoints(int n) {
        Random rnd = new Random();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(rnd.nextDouble(), rnd.nextDouble());
        }
        return pts;
    }
}
