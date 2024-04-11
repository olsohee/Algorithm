import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int m; // 가로
    static int n; // 세로
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) throws IOException {

        // 입력
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        // 1. 0,0을 시작으로 bfs
        Queue<Node> que = new PriorityQueue<>();
        visited[0][0] = true;
        que.add(new Node(0, 0, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();

            // 도착점에 도착한 경우, 해당 경로에서 벽을 부순 카운트를 answer에 갱신
            if (now.x == m - 1 && now.y == n - 1) {
                answer = now.breakCnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[ny][nx]) continue;

                // 다음 노드가 0이라면 그대로 이동
                if (map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx, now.breakCnt));
                }
                // 다음 노드가 1(벽)이라면 부수고 이동 (breakCnt++, 0으로 만들지 않음, 방문 처리)
                if (map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx, now.breakCnt + 1));
                }
            }
        }

        System.out.println(answer);
    }

    public static class Node implements Comparable<Node> {
        int y, x;
        int breakCnt;
        public Node (int y, int x, int breakCnt) {
            this.y = y;
            this.x = x;
            this.breakCnt = breakCnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.breakCnt - o.breakCnt;
        }
    }
}
