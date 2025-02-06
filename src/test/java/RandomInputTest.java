import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class RandomInputTest {
    @Test
    public void randomInputTest() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        int[] arr = new int[1000];
        Random random = new Random();

        int bound = 1000;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(bound);
        }

        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append(arr.length).append("\n");
        for (int n : arr) {
            inputBuilder.append(n).append("\n");
        }

        String input = inputBuilder.toString();

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Arrays.sort(arr);
        StringBuilder expectedBuilder = new StringBuilder();
        for (int n : arr) {
            expectedBuilder.append(n).append("\n");
        }

        Main.problemSolver();

        String result = out.toString(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedBuilder.toString().trim(), result.trim());
    }
}
