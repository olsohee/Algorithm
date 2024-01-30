import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 = O(V + E) = 27,000 + 27,000 * 6
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int l; // 높이
    static int r; // 세로
    static int c; // 가로
    static String[][][] map;
    static int[][][] visited;
    static Queue<Point> que;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dl = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0) {
                break;
            }

            map = new String[l][r][c];
            visited = new int[l][r][c];
            que = new LinkedList<>();

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String[] input = br.readLine().split("");
                    for (int k = 0; k < c; k++) {
                        map[i][j][k] = input[k];
                        // 시작점 큐에 넣기
                        if (map[i][j][k].equals("S")) {
                            que.add(new Point(i, j, k));
                            visited[i][j][k] = 1;
                        }
                    }
                }
                br.readLine(); // 공백라인 입력
            }

            bfs();
        }
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            Point p = que.poll();
            if (map[p.l][p.y][p.x].equals("E")) {
                System.out.println("Escaped in " + (visited[p.l][p.y][p.x] - 1) + " minute(s).");
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int nl = p.l + dl[i];
                if (nx < 0 || nx >= c || ny < 0 || ny >= r || nl < 0 || nl >= l) {
                    continue;
                }
                // 이미 방문했거나 길이 아니면 이동 X
                if (visited[nl][ny][nx] != 0 || map[nl][ny][nx].equals("#")) {
                    continue;
                }
                visited[nl][ny][nx] = visited[p.l][p.y][p.x] + 1;
                que.add(new Point(nl, ny, nx));
            }
        }

        // 탈출 못한 경우
        System.out.println("Trapped!");
    }

    static class Point {

        int l, y, x;

        public Point(int l, int y, int x) {
            this.l = l;
            this.y = y;
            this.x = x;
        }
    }
}