package Crawler;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class MetadataManager {
    private static final Consts consts = new Consts();

    private static long getIdFromMetadata() {
        try {
            Reader in = new FileReader(consts.getMetadataCsvPath());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            Iterator<CSVRecord> recordIterator = records.iterator();

            if (recordIterator.hasNext()) {
                String id = records.iterator().next().get(0);

                return Long.parseLong(id);
            } else {
                return -1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean needUpdate(long id) {
        long storedId = getIdFromMetadata();

        return storedId != id;
    }

    public static void update(long id) {
        try {
            FileWriter tcMetadataWriter = new FileWriter(consts.getMetadataCsvPath());
            CSVPrinter tcMetadataPrinter = new CSVPrinter(tcMetadataWriter, CSVFormat.DEFAULT);

            tcMetadataPrinter.printRecord(id);

            tcMetadataPrinter.flush();
            tcMetadataPrinter.close();
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}
