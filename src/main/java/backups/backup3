import java.io.*;
import java.util.Arrays;

public class Main {

    // https://www.acmicpc.net/problem/12267

    private static boolean[] checker;

    private static boolean check() {
        for (boolean b : checker) {
            if (!b) return false;
        }

        return true;
    }

    private static boolean checkRowInternal(int[][] board, int row) {
        Arrays.fill(checker, false);

        for (int n : board[row]) {
            if (checker[n - 1]) {
                return false;
            } else {
                checker[n - 1] = true;
            }
        }

        return check();
    }

    private static boolean checkColInternal(int[][] board, int col) {
        Arrays.fill(checker, false);

        for (int[] rows : board) {
            int n = rows[col];

            if (checker[n - 1]) {
                return false;
            } else {
                checker[n - 1] = true;
            }
        }

        return check();
    }

    private static boolean checkBoxInternal(int[][] board, int startRow, int startCol, int n) {
        Arrays.fill(checker, false);

        for (int row = startRow; row < startRow + n; row++) {
            for (int col = startCol; col < startCol + n; col++) {
                int num = board[row][col];

                if (checker[num - 1]) {
                    return false;
                } else {
                    checker[num - 1] = true;
                }
            }
        }

        return check();
    }

    private static boolean checkRows(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (!checkRowInternal(board, i)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkCols(int[][] board) {
        for (int i = 0; i < board[0].length; i++) {
            if (!checkColInternal(board, i)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkBoxes(int[][] board, int n) {
        for (int startRow = 0; startRow < board.length; startRow += n) {
            for (int startCol = 0; startCol < board[startRow].length; startCol += n) {
                if (!checkBoxInternal(board, startRow, startCol, n)) return false;
            }
        }

        return true;
    }

    private static boolean validate(int[][] board, int n) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] < 1 || board[i][j] > n * n) return false;
            }
        }

        return true;
    }

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            checker = new boolean[n * n];

            int[][] board = new int[n * n][];

            for (int j = 0; j < n * n; j++) {
                board[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            sb.append("Case #").append(i + 1).append(": ");
            if (validate(board, n) && checkRows(board) && checkCols(board) && checkBoxes(board, n)) {
                sb.append("Yes").append("\n");
            } else {
                sb.append("No").append("\n");
            }
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