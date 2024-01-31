import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int m;
    static int n;
    static int k;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    answer = Math.max(answer, dfs(i, j));
                }
            }
        }
        System.out.println(answer);
    }

    private static int dfs(int y, int x) {

        int count = 0;
        Stack<Point> stack = new Stack<>();
        stack.add(new Point(y, x));
        visited[y][x] = true;
        count++;

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[ny][nx]) {
                    continue;
                }
                if (map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    stack.add(new Point(ny, nx));
                    count++;
                }
            }
        }

        return count;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}