import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m;
    static int[][] map;
    static int[][][] visited;
    static int answer = -1;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[2][n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.y == n - 1 && now.x == m - 1) {
                answer = visited[now.breakCnt][n - 1][m - 1];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    continue;
                }

                // 길인 경우
                if (map[ny][nx] == 0) {
                    if (visited[now.breakCnt][ny][nx] != 0) {
                        continue;
                    }
                    visited[now.breakCnt][ny][nx] = visited[now.breakCnt][now.y][now.x] + 1;
                    que.add(new Node(ny, nx, now.breakCnt));
                }

                // 벽인 경우
                if (map[ny][nx] == 1) {
                    if (now.breakCnt >= 1) {
                        continue;
                    }

                    if (visited[now.breakCnt + 1][ny][nx] != 0) {
                        continue;
                    }

                    visited[now.breakCnt + 1][ny][nx] = visited[now.breakCnt][now.y][now.x] + 1;
//                    System.out.println(visited[1][ny][nx]);
//                    System.out.println();
                    que.add(new Node(ny, nx, now.breakCnt + 1));
                }
            }
        }

    }

    private static class Node {

        int y, x;
        int breakCnt;

        public Node(int y, int x, int breakCnt) {
            this.y = y;
            this.x = x;
            this.breakCnt = breakCnt;
        }
    }
}
