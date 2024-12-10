import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            // i번에서 시작 및 도착
            visited[i] = true;
            dfs(i, i, 1, 0);
        }

        System.out.println(answer);
    }

    private static void dfs(int start, int now, int cnt, int cost) {
        // 시작점부터 모든 노드를 방문한 경우 (다시 시작점으로 가야 함)
        if (cnt == n) {
            if (map[now][start] != 0) {
                cost += map[now][start];
                answer = Math.min(answer, cost);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && map[now][i] != 0) {
                visited[i] = true;
                dfs(start, i, cnt + 1, cost + map[now][i]);
                visited[i] = false;
            }
        }
    }
}
