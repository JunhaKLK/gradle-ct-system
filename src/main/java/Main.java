import java.io.*;

public class Main {

    // https://www.acmicpc.net/problem/16195

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        final long mod = 1_000_000_009L;

        long[][] dp = new long[1001][1001];

        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 1;

        dp[2][2] = 1;
        dp[2][3] = 2;
        dp[2][4] = 3;
        dp[2][5] = 2;
        dp[2][6] = 1;


        for (int i = 3; i < 1001; i++) {
            for (int j = i; j < 1001; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + dp[i - 1][j - 3];
                dp[i][j] %= mod;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] token = br.readLine().split(" ");
            int n = Integer.parseInt(token[0]);
            int m = Integer.parseInt(token[1]);

            long r = 0;

            for (int pos = 0; pos <= m; pos++) {
                r += dp[pos][n];
                r %= mod;
            }

            sb.append(r).append("\n");
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