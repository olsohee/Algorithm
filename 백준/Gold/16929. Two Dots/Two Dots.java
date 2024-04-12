import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] visited;
    static boolean answer = false;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = input[j];
            }
        }

        // dfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, i, j, -1, -1);
                visited[i][j] = false;
                if (answer) {
                    System.out.println("Yes");
                    return;
                }
            }
        }

        System.out.println("No");
    }

    public static void dfs(int y, int x, int startY, int startX, int beforeY, int beforeX) {

        // map[y][x]에 인접한 노드 중
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            if (map[y][x] != map[ny][nx]) continue;

            // 현재 루트의 시작점과 다음 노드가 같으면 사이클 완성(직전 노드 제외)
            if ((ny == startY && nx == startX) && ((beforeY != startY) && (beforeX != startX))) {
                answer = true;
                return;
            }
            else {
                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    dfs(ny, nx, startY, startX, y, x);
                    visited[ny][nx] = false;
                }
            }
        }
    }

    public static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
