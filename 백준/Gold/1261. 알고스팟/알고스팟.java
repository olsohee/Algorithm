
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 가로
        int n = Integer.parseInt(st.nextToken()); // 세로

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = input[j] - '0';
            }
        }
        
        boolean[][] visited = new boolean[n][m]; 
        visited[0][0] = true;
        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt); // 적게 부슨 경우를 우선으로
        que.add(new Node(0, 0, 0));
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        int answer = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.y == n - 1 && now.x == m - 1) {
                answer = now.cnt;
                break; // 도착점으로 오는 여러 경우 중 벽을 가장 적게 부슨 경우를 제일 먼저 만나게 됨
            }

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= m ) continue;
                
                if (visited[ny][nx]) continue;

                // 길인 경우
                if (map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx, now.cnt));
                }
                // 벽인 경우
                else {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx, now.cnt + 1));
                }
            }
        }

        System.out.println(answer);
    }

    private static class Node {
        int y, x;
        int cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
