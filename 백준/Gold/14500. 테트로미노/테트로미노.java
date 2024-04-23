import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, map[i][j], 1);
                visited[i][j] = false;

                combi(i, j, map[i][j], 1);
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int y, int x, int sum, int cnt) {
        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny < 0 || ny >= n || nx < 0 || nx >= m || visited[ny][nx]) continue;
            visited[ny][nx] = true;
            dfs(ny, nx, sum + map[ny][nx], cnt + 1);
            visited[ny][nx] = false;
        }
    }

    public static void combi(int y, int x, int sum, int cnt) {
        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= n || nx < 0 || nx >= m || visited[ny][nx]) {
                continue;
            }
            visited[ny][nx] = true;
            combi(y, x, sum + map[ny][nx], cnt + 1);
            visited[ny][nx] = false;
        }
    }
}
