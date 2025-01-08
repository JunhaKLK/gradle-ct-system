import java.io.*;
import java.util.StringTokenizer;

public class Main {

    // https://www.acmicpc.net/problem/31846

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        char[] chars = br.readLine().toCharArray();

        int loop = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < loop; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int max = 0;

            for (int fold = l; fold < r; fold++) {
                int count = 0;
                for (int adder = 0; adder <= Math.min((fold - l),(r - fold - 1)); adder++) {
                    if (chars[fold - adder - 1] == chars[fold + adder]) {
                        ++count;
                    }
                }

                if (count > max) {
                    max = count;
                }
            }

            sb.append(Integer.toString(max)).append("\n");
        }

        bw.write(sb.substring(0, sb.length() - 1));

        bw.newLine();
        bw.close();
        br.close();
    }


    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}