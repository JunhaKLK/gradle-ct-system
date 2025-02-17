import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    // https://www.acmicpc.net/problem/1517

    private static long count;

    // (]
    private static void mergeSort(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }

        int mid = start + ((end - start) / 2);

        mergeSort(arr, start, mid - 1);
        mergeSort(arr, mid, end);

        merge(arr, start, mid - 1, mid, end);
    }

    private static void merge(int[] arr, int aStart, int aEnd, int bStart, int bEnd) {
        Queue<Integer> queueA = new ArrayDeque<>(aEnd - aStart);
        Queue<Integer> queueB = new ArrayDeque<>(bEnd - bStart);

        for (int i = 0; i <= aEnd - aStart; i++) {
            queueA.add(arr[aStart + i]);
        }

        for (int i = 0; i < bEnd - bStart; i++) {
            queueB.add(arr[bStart + i]);
        }

        int nextIdx = aStart;

        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            if (queueA.peek() > queueB.peek()) {
                count += queueA.size();
                arr[nextIdx++] = queueB.poll();
            } else {
                arr[nextIdx++] = queueA.poll();
            }
        }

        while (!queueA.isEmpty()) {
            arr[nextIdx++] = queueA.poll();
        }

        while (!queueB.isEmpty()) {
            arr[nextIdx++] = queueB.poll();
        }
    }

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        count = 0;
        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        mergeSort(arr, 0, n);

        bw.write(Long.toString(count));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}