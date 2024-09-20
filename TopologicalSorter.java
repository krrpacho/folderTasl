import java.nio.file.Path;
import java.util.*;

public class TopologicalSorter {
    public static List<Path> sortFiles(Map<Path, List<Path>> dependencies) throws Exception {
        Map<Path, Integer> inDegree = new HashMap<>();
        for (Path file : dependencies.keySet()) {
            inDegree.put(file, 0);
        }
        for (List<Path> dependents : dependencies.values()) {
            for (Path dependent : dependents) {
                inDegree.put(dependent, inDegree.get(dependent) + 1);
            }
        }

        Queue<Path> zeroInDegree = new LinkedList<>();
        for (Map.Entry<Path, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                zeroInDegree.add(entry.getKey());
            }
        }

        List<Path> sortedFiles = new ArrayList<>();
        while (!zeroInDegree.isEmpty()) {
            Path file = zeroInDegree.poll();
            sortedFiles.add(file);
            for (Path dependent : dependencies.get(file)) {
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    zeroInDegree.add(dependent);
                }
            }
        }

        if (sortedFiles.size() != dependencies.size()) {
            throw new Exception("Cycle detected");
        }
        return sortedFiles;
    }
}
