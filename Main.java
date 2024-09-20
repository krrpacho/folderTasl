import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            Path rootDir = Paths.get("path/to/root/directory"); 
            Path outputFile = Paths.get("path/to/output/file.txt");  

            List<Path> allFiles = FileFinder.findTextFiles(rootDir);

            Map<Path, List<Path>> dependencies = new HashMap<>();
            for (Path file : allFiles) {
                dependencies.put(file, (List<Path>) FileParser.getFileDependencies(file, allFiles));
            }

            List<Path> sortedFiles = TopologicalSorter.sortFiles(dependencies);

            FileCombiner.combineFiles(sortedFiles, outputFile);

            System.out.println("Files combined successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
