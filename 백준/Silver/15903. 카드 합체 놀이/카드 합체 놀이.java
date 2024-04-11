import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Long> que = new PriorityQueue<>(); // 작은 수가 우선인 우선순위 큐
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            que.add(Long.parseLong(st.nextToken()));
        }

        // m번 카드 합체
        for (int i = 0; i < m; i++) {
            long n1 = que.poll();
            long n2 = que.poll();
            que.add(n1 + n2);
            que.add(n1 + n2);
        }

        // 결과 계산
        long answer = 0;
        while (!que.isEmpty()) {
            answer += que.poll();
        }

        System.out.println(answer);
    }
}
