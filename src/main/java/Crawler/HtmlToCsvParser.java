package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlToCsvParser {
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

            FileWriter out = new FileWriter("src/test/resources/TestCase.csv");
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT);

            Elements inputs = parseInputExamples(doc, sampleId);
            Elements outputs = parseOutputExamples(doc, sampleId);

            while (!inputs.isEmpty()) {
                String input = inputs.text().trim();
                String output = outputs.text().trim();

                printer.printRecord(input, output);
                sampleId++;

                inputs = parseInputExamples(doc, sampleId);
                outputs = parseOutputExamples(doc, sampleId);
            }

            // 파일 닫기
            printer.flush();
            printer.close();

            System.out.println("CSV 파일이 성공적으로 생성되었습니다.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Document getHTML(long id) {
        try {
            Document doc = Jsoup.connect("https://www.acmicpc.net/problem/" + id).timeout(3000).get();
            System.out.println(doc.html());
            return doc;
        } catch (IOException e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    private static long getProblemId() {
        // todo
        return 32154L;
    }

    public static void setCSV() {
        Document html = getHTML(getProblemId());
        writeToCSV(html);
    }

    public static void main(String[] args) {
        setCSV();
    }
}
