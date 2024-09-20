import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    public static List<Path> findTextFiles(Path dir) throws IOException {
        List<Path> files = new ArrayList<>();
        Files.walk(dir)
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".txt"))
            .forEach(files::add);
        return files;
    }
}
