package bench;

import algos.DeterministicSelect;
import algos.MergeSort;
import algos.QuickSort;
import metrics.Metrics;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectVsSortBenchmark {

    @Param({"1000", "10000", "100000"})
    private int size;

    private int[] data;
    private Random rnd;

    @Setup(Level.Invocation)
    public void setup() {
        rnd = new Random(42);
        data = rnd.ints(size, -1_000_000, 1_000_000).toArray();
    }

    @Benchmark
    public int deterministicSelect() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        return DeterministicSelect.select(copy, 0, copy.length, copy.length / 2, m);
    }

    @Benchmark
    public int quickSortMedian() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        QuickSort.sort(copy, m);
        return copy[copy.length / 2];
    }

    @Benchmark
    public int mergeSortMedian() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        MergeSort.sort(copy, m);
        return copy[copy.length / 2];
    }
}
