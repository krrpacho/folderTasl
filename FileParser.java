import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class FileParser {
    private static final Pattern REQUIRE_PATTERN = Pattern.compile("\\*require\\s+'(.*)'");

    public static Map<Path, List<Path>> getFileDependencies(Path filePath, List<Path> allFiles) throws IOException {
        Map<Path, List<Path>> dependencies = new HashMap<>();
        for (Path file : allFiles) {
            dependencies.put(file, new ArrayList<>());
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            Matcher matcher = REQUIRE_PATTERN.matcher(line);
            if (matcher.find()) {
                Path requiredFile = Paths.get(matcher.group(1));
                if (dependencies.containsKey(requiredFile)) {
                    dependencies.get(filePath).add(requiredFile);
                }
            }
        }
        return dependencies;
    }
}
