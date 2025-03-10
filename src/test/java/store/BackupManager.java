package store;

import common.Extractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class BackupManager {
    public static void store() {
        long pid = getProblemId().get();
        File file = getFileWithPid(pid);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(Extractor.getMainClassContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("file = " + file);
        System.out.println("write complete");
    }

    private static File getFileWithPid(long pid) {
        File file = new File("src/main/java/solutions/" + pid);

        if (!file.canRead()) {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return file;
    }

    private static Optional<Long> getProblemId() {
        String firstComment = Extractor.getFirstComment().orElse("").trim();

        if (!Pattern.matches("https://www.acmicpc.net/problem/\\d+$", firstComment)) {
            return Optional.empty();
        }

        List<Character> idCharList = new ArrayList<>(5);

        char[] chars = firstComment.toCharArray();
        for (int i = firstComment.length() - 1; i >= 0; i--) {
            if (chars[i] == '/') {
                break;
            } else {
                if ('0' > chars[i] || '9' < chars[i]) return Optional.empty();

                idCharList.add(chars[i]);
            }
        }

        for (int i = 0; i < idCharList.size() / 2; i++) {
            char tmp = idCharList.get(i);
            idCharList.set(i, idCharList.get(idCharList.size() - 1 - i));
            idCharList.set(idCharList.size() - 1 - i, tmp);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : idCharList) {
            sb.append(c);
        }

        try {
            Long id = Long.parseLong(sb.toString());

            return Optional.of(id);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        store();
    }
}
