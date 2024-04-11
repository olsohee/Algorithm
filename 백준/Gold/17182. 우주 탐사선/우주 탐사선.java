import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int start;
    static int[][] map;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        // 입력
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 플로이드로 map에 최단거리 갱신
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        // 2. 시작점에서 dfs로 모든 노드 탐색하기
        visited = new boolean[n];
        dfs(0, start, 0);

        System.out.println(answer);
    }

    public static void dfs(int cnt, int start, int sum) {
        if (cnt == n) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(cnt + 1, i, sum + map[start][i]);
                visited[i] = false;
            }
        }
    }
}
