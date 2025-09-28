package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class MetricsCSVWriter {
    private final Path out;
    private final boolean headerWritten;

    public MetricsCSVWriter(Path out) throws IOException {
        this.out = out;
        headerWritten = Files.exists(out) && Files.size(out) > 0;
        if (!headerWritten) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(out.toFile(), true))) {
                pw.println("algo,n,trial,seed,time_ns,comparisons,allocations,maxDepth,cutoff");
            }
        }
    }

    public synchronized void appendRow(String csvRow) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(out.toFile(), true))) {
            pw.println(csvRow);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV row", e);
        }
    }
}
