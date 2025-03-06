import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

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

        int arrSize = 5;
        int[] arr = new int[arrSize];
        Random random = new Random();

        int bound = 10;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(bound);
        }

        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append(arr.length).append("\n");
        for (int n : arr) {
            inputBuilder.append(n).append(" ");
        }

        String input = inputBuilder.toString().trim();

        bubbleSort(arr);

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(exp);

        Main.problemSolver();

        String result = out.toString(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedBuilder.toString().trim(), result.trim());
    }

    private static int exp = 0;

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ++exp;
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
