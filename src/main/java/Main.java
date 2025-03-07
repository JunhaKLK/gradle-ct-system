import java.io.*;
import java.util.*;

public class Main {

    // https://www.acmicpc.net/problem/21049

    private static boolean validate(List<int[]> list) {
        for (int[] n : list) {
            if (n[0] < 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean isAllZero(List<int[]> list) {
        for (int[] n : list) {
            if (n[0] != 0) {
                return false;
            }
        }

        return true;
    }

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<int[]> vectors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vectors.add(new int[] {arr[i], i});
        }

        long sum = 0;
        for (int[] vector : vectors) {
            sum += vector[0];
        }

        boolean couldWin = true;

        if (sum % 2 == 1) {
            couldWin = false;
        }

        StringBuilder sb = new StringBuilder();
        if (couldWin) {

            int minIdx = 0;
            while (validate(vectors)) {
                vectors.sort(Comparator.comparingInt((int[] x) -> x[0]));

                while (minIdx < arr.length && vectors.get(minIdx)[0] == 0) {
                    minIdx++;
                }

                if (minIdx >= arr.length) {
                    break;
                }

                sb.append(vectors.get(minIdx)[1] + 1).append(" ").append(vectors.get(n - 1)[1] + 1).append("\n");

                vectors.get(minIdx)[0]--;
                vectors.get(arr.length - 1)[0]--;
            }

            if (!isAllZero(vectors)) {
                couldWin = false;
            }
        }

        if (couldWin) {
            bw.write("yes");
            bw.newLine();
            bw.write(sb.toString().trim());
        } else {
            bw.write("no");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}