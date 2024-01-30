import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 = O(V + E) = 27,000 + 27,000 * 6
public class Main {
    /*
    반례
9 9
010001000
010101010
010101010
010101010
010101010
010101010
010101010
010101011
000100010
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static int[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Point> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new int[2][n][m];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        bfs();
    }

    private static void bfs() {
        que.add(new Point(0, 0, false));
        visited[0][0][0] = 1;

        while (!que.isEmpty()) {
            Point p = que.poll();

            // 도착한 경우
            if (p.y == n - 1 && p.x == m - 1) {
                System.out.println(Math.max(visited[0][n - 1][m - 1], visited[1][n - 1][m - 1]));
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                /*
                벽이 아닌 경우 -> 방문한 적이 없으면 -> 이동하기
                 */
                if (map[ny][nx] == 0) {
                    // 부신 적이 없는 경우
                    if (!p.destroy && visited[0][ny][nx] == 0){
                        visited[0][ny][nx] = visited[0][p.y][p.x] + 1;
                        que.add(new Point(ny, nx, p.destroy));
                    }
                    // 부신 적이 있는 경우
                    if (p.destroy && visited[1][ny][nx] == 0) {
                        visited[1][ny][nx] = visited[1][p.y][p.x] + 1;
                        que.add(new Point(ny, nx, p.destroy));
                    }
                }

                /*
                벽인 경우 -> 부신 적이 없고, 방문한 적이 없으면 -> 부시고 이동
                 */
                if (map[ny][nx] == 1) {
                    if (!p.destroy && visited[1][ny][nx] == 0) {
                        visited[1][ny][nx] = visited[0][p.y][p.x] + 1;
                        que.add(new Point(ny, nx, true));
                    }
                }
            }
        }

        // 도착하지 못한 경우
        System.out.println(-1);
    }

    static class Point {
        int y, x;
        boolean destroy;

        public Point(int y, int x, boolean destroy) {
            this.y = y;
            this.x = x;
            this.destroy = destroy;
        }
    }
}