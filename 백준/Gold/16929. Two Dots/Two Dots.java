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

        // 모든 노드를 시작으로 dfs 
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

        // map[y][x]에 인접하면서 같은 알파벳인 노드들 중
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            if (map[y][x] != map[ny][nx]) continue;

            // 시작 노드와 다음 노드가 같으면 사이클 완성 (이때 직전 노드는 제외)
            if ((ny == startY && nx == startX) && (beforeY != startY) && (beforeX != startX)) {
                answer = true;
                return;
            }
            // 그렇지 않은 경우, 방문하지 않은 노드 방문 (이때 이미 방문한 노드는 제외)
            else {
                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    dfs(ny, nx, startY, startX, y, x);
                    visited[ny][nx] = false;
                }
            }
        }
    }
}

