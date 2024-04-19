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
        boolean[][][] visited = new boolean[k + 1][n][m];

        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }

        // 0,0에서부터 bfs
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, 0, true, 1));
        visited[0][0][0] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.y == n - 1 && now.x == m - 1) {
                answer = now.moveCnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                // 길인 경우 이동
                if (map[ny][nx] == 0 && !visited[now.breakCnt][ny][nx]) {
                    que.add(new Node(ny, nx, now.breakCnt, !now.isDayTime, now.moveCnt + 1));
                    visited[now.breakCnt][ny][nx] = true;
                }

                // 벽인 경우
                if (map[ny][nx] == 1) {
                    // 낮이면 벽을 깨고 이동
                    if (now.isDayTime && now.breakCnt + 1 <= k && !visited[now.breakCnt + 1][ny][nx]) {
                        que.add(new Node(ny, nx, now.breakCnt + 1, !now.isDayTime, now.moveCnt + 1));
                        visited[now.breakCnt + 1][ny][nx] = true;
                    }

                    // 밤이면 제자리
                    if (!now.isDayTime) {
                        que.add(new Node(now.y, now.x, now.breakCnt, !now.isDayTime, now.moveCnt + 1));
                        visited[now.breakCnt][now.y][now.x] = true;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static class Node {
        int y, x;
        int breakCnt;
        boolean isDayTime;
        int moveCnt;
        public Node (int y, int x, int breakCnt, boolean isDayTime, int moveCnt) {
            this.y = y;
            this.x = x;
            this.breakCnt = breakCnt;
            this.isDayTime = isDayTime;
            this.moveCnt = moveCnt;
        }
    }
}
