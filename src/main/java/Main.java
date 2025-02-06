import java.io.*;

public class Main {

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String input = br.readLine();
        int sum = 0;
        for (int num : input.toCharArray()) {
            sum += num - '0';
        }

        bw.write(Integer.toString(sum));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}