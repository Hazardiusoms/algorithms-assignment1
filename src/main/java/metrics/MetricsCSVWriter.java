package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MetricsCSVWriter implements AutoCloseable {
    private final PrintWriter out;

    public MetricsCSVWriter(String filename) throws IOException {
        this.out = new PrintWriter(new FileWriter(filename, true));
        // header row if empty file
        out.println("algo,size,comparisons,allocations,maxDepth");
    }

    public void writeRow(String algo, int size, Metrics m) {
        out.printf("%s,%d,%d,%d,%d%n",
                algo, size, m.getComparisons(), m.getAllocations(), m.getMaxDepth());
        out.flush();
    }

    @Override
    public void close() {
        out.close();
    }
}
