import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = -1;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][][] visited = new int[k + 1][n][m];

        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }

        // 0,0에서부터 bfs
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.y == n - 1 && now.x == m - 1) {
                answer = visited[now.cnt][now.y][now.x];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                // 길인 경우 이동
                if (map[ny][nx] == 0 && visited[now.cnt][ny][nx] == 0) {
                    visited[now.cnt][ny][nx] = visited[now.cnt][now.y][now.x] + 1;
                    que.add(new Node(ny, nx, now.cnt));
                }

                // 벽인 경우 이동
                if (now.cnt + 1 <= k) {
                    if (map[ny][nx] == 1 && visited[now.cnt + 1][ny][nx] == 0) {
                        visited[now.cnt + 1][ny][nx] = visited[now.cnt][now.y][now.x] + 1;
                        que.add(new Node(ny, nx, now.cnt + 1));
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static class Node {
        int y, x;
        int cnt;
        public Node (int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
