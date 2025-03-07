package Crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class CommentExtractor {
    private static final String filePath = "src/main/java/Main.java";

    public static Optional<String> getFirstComment() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("//")) {
                    return Optional.of(line.substring(2).trim());
                } else if (line.startsWith("/*")) {
                    return Optional.of(line.substring(2).trim());
                } else if (line.startsWith("/**")) {
                    return Optional.of(line.substring(3).trim());
                }
            }
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
