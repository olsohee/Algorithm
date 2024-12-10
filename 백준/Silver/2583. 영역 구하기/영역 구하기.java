import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int m, n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            fill(x1, m - y1, x2, m - y2);
        }

        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    answerList.add(bfs(i, j));
                }
            }
        }
        Collections.sort(answerList);
        System.out.println(answerList.size());
        for (Integer cnt : answerList) {
            System.out.print(cnt + " ");
        }
    }

    private static void fill(int x1, int y1, int x2, int y2) {
        for (int i = y2; i < y1; i++) {
            for (int j = x1; j < x2; j++) {
                map[i][j] = 1;
            }
        }
    }

    private static int bfs(int y, int x) {
        visited[y][x] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        int cnt = 1;

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || ny >= m || nx < 0 || nx >= n || visited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx));
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
