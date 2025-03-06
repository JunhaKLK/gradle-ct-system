import java.io.*;

public class Main {

    // https://www.acmicpc.net/problem/8896

    private static int afterTaxIncome(int prevIncome) {
        if (prevIncome > 5000000) {
            return (int) (prevIncome * 0.8);
        } else if (prevIncome > 1000000) {
            return (int) (prevIncome * 0.9);
        } else {
            return prevIncome;
        }
    }

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String in = br.readLine();

        StringBuilder sb = new StringBuilder();
        while (!"0".equals(in)) {
            sb.append(afterTaxIncome(Integer.parseInt(in))).append("\n");

            in = br.readLine();
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}