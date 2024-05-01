import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Node>[][] light;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int cnt = 0;
    static ArrayList<Node> waiting = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        light = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                light[i][j] = new ArrayList<>();
            }
        }
         for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            light[y][x].add(new Node(a, b));
        }

        Queue<Node> que = new LinkedList<>();
        map[1][1] = 1;
        visited[1][1] = true;
        que.add(new Node(1, 1));
        cnt++;

        while (!que.isEmpty()) {
            Node now = que.poll();
            // 현재 위치에서 켤 수 있는 방 불 켜기
            if (!light[now.y][now.x].isEmpty()) {
                for (Node node : light[now.y][now.x]) {
                    if (map[node.y][node.x] == 1) continue;
                    map[node.y][node.x] = 1;
                    cnt++;
                }
            }

            // 현재 위치에서 bfs
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                if (!visited[ny][nx]) {
                    if (map[ny][nx] == 1) {
                        visited[ny][nx] = true;
                        que.add(new Node(ny, nx));
                    } else {
                        waiting.add(new Node(ny, nx));
                    }
                }
            }
            int size = waiting.size();

            for (int i = 0; i < size; i++) {
                Node poll = waiting.get(i);
                if (map[poll.y][poll.x] == 1 && !visited[poll.y][poll.x]) {
                    que.add(new Node(poll.y, poll.x));
                    visited[poll.y][poll.x] = true;
                }
            }
        }

        System.out.println(cnt);
    }

    public static class Node {
        int y, x;
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
