package store;

import java.io.File;
import java.io.IOException;

public class SolvedQueueManager {
    public void store() {

    }

    private static File getNewFile(long pid) {
        File file = new File("src/main/java/backups/backup" + pid);

        if (!file.canRead()) {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("already exists");
        }
    }
}
