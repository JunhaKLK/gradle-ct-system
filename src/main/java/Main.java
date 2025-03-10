import java.io.*;
import java.time.LocalDate;
import java.time.Period;

public class Main {

    // https://www.acmicpc.net/problem/2730

    private static boolean isIn(LocalDate high, LocalDate low, LocalDate compare) {
        return high.isAfter(compare) && low.isBefore(compare);
    }

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] strings = br.readLine().split(" ");

            String[] dueDate = strings[0].split("/");
            String[] parseDate = strings[1].split("/");

            LocalDate dueLocalDate = LocalDate.of(Integer.parseInt(dueDate[2]), Integer.parseInt(dueDate[0]), Integer.parseInt(dueDate[1]));
            LocalDate lowerBound = LocalDate.of(Integer.parseInt(dueDate[2]), Integer.parseInt(dueDate[0]), Integer.parseInt(dueDate[1])).minusDays(8);
            LocalDate higherBound = LocalDate.of(Integer.parseInt(dueDate[2]), Integer.parseInt(dueDate[0]), Integer.parseInt(dueDate[1])).plusDays(8);

            LocalDate sameYearSubmitDate = LocalDate.of(dueLocalDate.getYear(), Integer.parseInt(parseDate[0]), Integer.parseInt(parseDate[1]));
            LocalDate lastYearSubmitDate = LocalDate.of(dueLocalDate.getYear() - 1, Integer.parseInt(parseDate[0]), Integer.parseInt(parseDate[1]));
            LocalDate nextYearSubmitDate = LocalDate.of(dueLocalDate.getYear() + 1, Integer.parseInt(parseDate[0]), Integer.parseInt(parseDate[1]));

            if (isIn(higherBound, lowerBound, sameYearSubmitDate)) {
                if (dueLocalDate.isEqual(sameYearSubmitDate)) {
                    sb.append("SAME DAY");
                    sb.append("\n");
                    continue;
                }

                attachDate(dueLocalDate, sameYearSubmitDate, sb);

            } else if (isIn(higherBound, lowerBound, lastYearSubmitDate)) {
                if (dueLocalDate.isEqual(lastYearSubmitDate)) {
                    sb.append("SAME DAY");
                    sb.append("\n");
                    continue;
                }

                attachDate(dueLocalDate, lastYearSubmitDate, sb);
            } else if (isIn(higherBound, lowerBound, nextYearSubmitDate)) {
                if (dueLocalDate.isEqual(nextYearSubmitDate)) {
                    sb.append("SAME DAY");
                    sb.append("\n");
                    continue;
                }

                attachDate(dueLocalDate, nextYearSubmitDate, sb);
            } else {
                sb.append("OUT OF RANGE");
            }

            sb.append("\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        br.close();
        bw.close();
    }

    private static void attachDate(LocalDate dueLocalDate, LocalDate localDate, StringBuilder sb) {
        sb.append(localDate.getMonthValue())
                .append("/")
                .append(localDate.getDayOfMonth())
                .append("/")
                .append(localDate.getYear())
                .append(" IS ");

        Period period = dueLocalDate.until(localDate);
        long diffDays = period.getDays();

        if (diffDays > 0L) {
            sb.append(diffDays).append(" DAY");
            if (diffDays != 1) sb.append("S");
            sb.append(" AFTER");
        } else {
            diffDays *= -1;
            sb.append(diffDays).append(" DAY");
            if (diffDays != 1) sb.append("S");
            sb.append(" PRIOR");
        }
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}