import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        List<Homework> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            list.add(new Homework(end, score));
        }

        Collections.sort(list, (o1, o2) -> {
            return o2.score - o1.score; // 점수가 높은 순으로 정렬
        });

        int scoreSum = 0;
        boolean[] doHomework = new boolean[1001]; // doHomework[i] : i 날짜에 과제했는지 유무
        for (Homework homework : list) {
            for (int i = homework.end - 1; i >= 0; i--) {
                if (doHomework[i]) {
                    continue;
                }
                doHomework[i] = true;
                scoreSum += homework.score;
                break;
            }
        }

        System.out.println(scoreSum);
    }

    private static class Homework {

        int end;
        int score;

        public Homework(int end, int score) {
            this.end = end;
            this.score = score;
        }
    }
}
