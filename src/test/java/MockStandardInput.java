import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.*;
import java.nio.charset.StandardCharsets;

class MockStandardInputTest {

    @Test
    void initTest() {
        Assertions.assertTrue(true);
    }

    @ParameterizedTest(name = "TC {index} from CSV - {0}")
    @CsvFileSource(files = "src/test/resources/TestCase.csv")
    void twoParameterFromCsvTest(String input, String output) throws IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        Main.problemSolver();

        String result = out.toString(StandardCharsets.UTF_8);

        Assertions.assertEquals(output, result.substring(0, result.length() - 1));

        System.setIn(sysInBackup);
    }
}
