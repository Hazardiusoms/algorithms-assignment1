package metrics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Metrics {
    private final AtomicLong comparisons = new AtomicLong(0);
    private final AtomicLong allocations  = new AtomicLong(0);
    private final AtomicLong elapsedNanos = new AtomicLong(0);

    private final AtomicInteger currentDepth = new AtomicInteger(0);
    private final AtomicInteger maxDepth = new AtomicInteger(0);


    public void incComparisons() { comparisons.incrementAndGet(); }
    public void incAllocations()  { allocations.incrementAndGet(); }
    public void addElapsedNanos(long nanos) { elapsedNanos.addAndGet(nanos); }

    public void pushDepth() {
        int d = currentDepth.incrementAndGet();
        maxDepth.updateAndGet(prev -> Math.max(prev, d));
    }

    public void popDepth() {
        currentDepth.updateAndGet(prev -> Math.max(0, prev - 1));
    }


    public long getComparisons() { return comparisons.get(); }
    public long getAllocations()  { return allocations.get(); }
    public long getElapsedNanos() { return elapsedNanos.get(); }
    public int  getCurrentDepth() { return currentDepth.get(); }
    public int  getMaxDepth()     { return maxDepth.get(); }


    public String toCsvRow(String algo, int size, int trial, long seed, int cutoff) {
        return String.format("%s,%d,%d,%d,%d,%d,%d",
                algo, size, trial, seed,
                getElapsedNanos(), getComparisons(), getAllocations());
    }
}
