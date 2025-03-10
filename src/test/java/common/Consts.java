package common;

public class Consts {
    private static final String tcCsvPath = "src/test/resources/TestCase.csv";
    private static final String metadataCsvPath = "src/test/resources/TestCase_Meta.csv";
    private static final String filePath = "src/main/java/Main.java";

    public static String getTcCsvPath() {
        return tcCsvPath;
    }

    public static String getMetadataCsvPath() {
        return metadataCsvPath;
    }
    public static String getFilePath() {
        return filePath;
    }
}
