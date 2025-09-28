package metrics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Metrics {

    public long comparisons = 0;
    public long allocations = 0;
    public long elapsedNanos = 0L;

    private int currentDepth = 0;
    public int maxDepth = 0;

    public void incComparisons() { comparisons++; }
    public void incAllocations()  { allocations++; }

    public void addElapsedNanos(long nanos) { elapsedNanos += nanos; }

    public void pushDepth() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void popDepth() {
        if (currentDepth > 0) currentDepth--;
    }


    public String toCsvRow(String algo, int n, int trial, long seed, int cutoff) {
        return String.format("%s,%d,%d,%d,%d,%d,%d,%d,%d",
                algo, n, trial, seed, elapsedNanos, comparisons, allocations, maxDepth, cutoff);
    }
}
