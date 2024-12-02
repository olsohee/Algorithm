import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int start;
    static int destination;
    static int[] visited = new int[100001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        Queue<Integer> que = new LinkedList<>();
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;
        que.add(start);

        int answerCnt = 0;
        int answerTime = Integer.MAX_VALUE;

        while (!que.isEmpty()) {
            int now = que.poll();
            if (visited[now] > answerTime) {
                continue;
            }

            if (now == destination) {
                answerTime = visited[destination];
                answerCnt++;
                continue;
            }

            if (now - 1 >= 0 && visited[now - 1] >= visited[now] + 1) {
                visited[now - 1] = visited[now] + 1;
                que.add(now - 1);
            }

            if (now + 1 < 100001 && visited[now + 1] >= visited[now] + 1) {
                visited[now + 1] = visited[now] + 1;
                que.add(now + 1);
            }

            if (now * 2 < 100001 && visited[now * 2] >= visited[now] + 1) {
                visited[now * 2] = visited[now] + 1;
                que.add(now * 2);
            }
        }

        System.out.println(answerTime);
        System.out.println(answerCnt);
    }
}