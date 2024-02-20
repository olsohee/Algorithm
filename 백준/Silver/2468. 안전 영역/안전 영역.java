import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int minH = Integer.MAX_VALUE;
        int maxH = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, map[i][j]);
                maxH = Math.max(maxH, map[i][j]);
            }
        }

        int h = minH - 1;
        while (h <= maxH + 1) {
            // dfs
            boolean[][] visited = new boolean[n][n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > h && !visited[i][j]) {
                        cnt++;
                        dfs(i, j, visited, h);
                    }
                }
            }
            answer = Math.max(answer, cnt);
            h++;
        }

        System.out.println(answer);
    }

    private static void dfs(int y, int x, boolean[][] visited, int h) {
        visited[y][x] = true;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visited[ny][nx]) continue;
            if (map[ny][nx] > h) {
                dfs(ny, nx, visited, h);
            }
        }
    }
}
