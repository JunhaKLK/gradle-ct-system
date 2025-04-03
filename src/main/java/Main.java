import java.io.*;
import java.util.*;

public class Main {

    // https://www.acmicpc.net/problem/3181

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        String[] arr = new String[]{"i", "pa", "te", "ni", "niti", "a", "ali", "nego", "no", "ili"};
        Set<String> useless = new HashSet<>(Arrays.asList(arr));

        sb.append(st.nextToken().toUpperCase(Locale.ROOT).charAt(0));
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (!useless.contains(token)) {
                sb.append(token.toUpperCase(Locale.ROOT).charAt(0));
            }
        }

        bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}
