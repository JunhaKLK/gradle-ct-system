import java.io.*;
import java.util.Arrays;

public class Main {

    // https://www.acmicpc.net/problem/10211

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int max = arr[0];
            int localMax = arr[0];

            for (int j = 1; j < n; j++) {
                localMax = Math.max(localMax + arr[j], arr[j]);
                max = Math.max(max, localMax);
            }

            sb.append(max).append("\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}