package Crawler;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class CSVLineEndingConverter {
    public static void convert() {
        String inputFilePath = Consts.getTcCsvPath(); // 원본 CSV 파일 경로
        String outputFilePath = Consts.getTcCsvPath(); // 변환된 CSV 파일 경로

        try {
            // 파일 내용을 읽어오기
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)), StandardCharsets.UTF_8);

            // CRLF (\r\n)을 LF (\n)로 변환
            String convertedContent = content.replace("\r\n", "\n");

            // 변환된 내용을 새 파일에 쓰기
            Files.write(Paths.get(outputFilePath), convertedContent.getBytes(StandardCharsets.UTF_8));

            System.out.println("변환 완료: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
