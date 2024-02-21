import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished;
    static int answer;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            answer = n;

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            // dfs
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    dfs(j);
                }
            }

            System.out.println(answer);
        }
    }

    private static void dfs(int now) {
        visited[now] = true;
        int next = arr[now];

        // 다음 노드 방문 X
        if (!visited[next]) {
            dfs(next); // 방문
        }
        // 다음 노드 방문 O
        else {
            if (!finished[next]) { // 아직 팀 소속이 아닌 경우 팀원 계산
                answer--;
                for (int i = next; i != now; i = arr[i]) {
                    answer--;
                }
            }
        }

        finished[now] = true;
    }
}