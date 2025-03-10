package common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class Extractor {
    private static final String filePath = Consts.getFilePath();

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

    public static byte[] getMainClassContent() throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}
