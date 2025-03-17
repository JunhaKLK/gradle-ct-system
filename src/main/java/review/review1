import java.io.*;

public class Main {

    // https://www.acmicpc.net/problem/24839

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            char[] inputTarget = br.readLine().toCharArray();
            char[] produced = br.readLine().toCharArray();

            int iPointer = 0;

            int miss = 0;

            for (char p : produced) {
                if (iPointer == inputTarget.length) {
                    miss += produced.length - iPointer;
                    break;
                }

                if (inputTarget[iPointer] == p) {
                    iPointer++;
                } else {
                    ++miss;
                }
            }

            sb.append("Case #").append(i + 1).append(": ");
            sb.append(iPointer == inputTarget.length ? miss : "IMPOSSIBLE").append("\n");
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