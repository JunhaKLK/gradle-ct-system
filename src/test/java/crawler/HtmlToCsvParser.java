package crawler;

import common.Extractor;
import common.Consts;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class HtmlToCsvParser {
    private static Consts consts = new Consts();

    private static Elements parseOutputExamples(Document doc, long id) {
        String prefix = "#sample-output-";

        return doc.select(prefix + id);
    }

    private static Elements parseInputExamples(Document doc, long id) {
        String prefix = "#sample-input-";

        return doc.select(prefix + id);
    }

    private static void writeToCSV(Document doc) {
        try {
            int sampleId = 1;

            FileWriter tcWriter = new FileWriter(consts.getTcCsvPath());

            CSVPrinter tcPrinter = new CSVPrinter(tcWriter, CSVFormat.DEFAULT.builder().setRecordSeparator('\n').get());

            Elements inputs = parseInputExamples(doc, sampleId);
            Elements outputs = parseOutputExamples(doc, sampleId);

            while (!inputs.isEmpty()) {
                String input = inputs.text().trim();
                String output = outputs.text().trim();

                tcPrinter.printRecord(input, output);
                sampleId++;

                inputs = parseInputExamples(doc, sampleId);
                outputs = parseOutputExamples(doc, sampleId);
            }

            tcPrinter.flush();
            tcPrinter.close();

            System.out.println("CSV 파일이 성공적으로 생성되었습니다.");

        } catch (IOException e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    private static Document getHTML(long id) {
        try {
            Document doc = Jsoup.connect("https://www.acmicpc.net/problem/" + id).timeout(10000).get();

            return doc;
        } catch (IOException e) {
            throw new IllegalStateException(e.getCause());
        }
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

    public static void setCSV() {
        Long id = getProblemId().orElseThrow(IllegalStateException::new);

        if (!MetadataManager.needUpdate(id)) return;

        Document html = getHTML(id);
        MetadataManager.update(id);
        writeToCSV(html);
        CSVLineEndingConverter.convert();
    }
}
