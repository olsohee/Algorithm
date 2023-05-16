
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int t, l;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    static Pair start, end;
    static int dx[] = {2, 2, -2, -2, 1, -1, 1, -1};
    static int dy[] = {1, -1, 1, -1, 2, 2, -2, -2};

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++) {
            l = Integer.parseInt(br.readLine());
            map = new int[l][l];
            visited = new boolean[l][l];
            dist = new int[l][l];

            st = new StringTokenizer(br.readLine());
            start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            st = new StringTokenizer(br.readLine());
            end = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            bfs(start);
        }
    }

    static void bfs(Pair p) {
        Queue<Pair> que = new LinkedList<>();
        que.offer(p);
        visited[p.y][p.x] = true;

        while(!que.isEmpty()) {
            Pair temp = que.poll();

            if(end.x == temp.x && end.y == temp.y) {
                System.out.println(dist[end.y][end.x]);
                return;
            }

            for(int i = 0; i < 8; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];

                if(nx < 0 || nx >= l || ny < 0 || ny >= l || visited[ny][nx]) continue;

                que.offer(new Pair(nx, ny));
                visited[ny][nx] = true;
                dist[ny][nx] = dist[temp.y][temp.x] + 1;
            }
        }
    }

    public static class Pair {

        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
