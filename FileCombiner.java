import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileCombiner {
    public static void combineFiles(List<Path> sortedFiles, Path outputFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            for (Path file : sortedFiles) {
                List<String> lines = Files.readAllLines(file);
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
