import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][m];
            boolean[][] visited = new boolean[n][m];

            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[b][a] = 1;
            }

            //bfs 시작
            Queue<Pair> que = new LinkedList<>();
            int[] dx = {1, -1, 0, 0} ;
            int[] dy = {0, 0, 1, -1};
            int count = 0;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(arr[i][j] == 0 || visited[i][j]) continue;

                    //각 배추 영역의 시작점
                    que.offer(new Pair(j, i));
                    visited[i][j] = true;
                    count++;
                    while (!que.isEmpty()) {
                        Pair p = que.poll();
                        for(int index = 0; index < 4; index++) {
                            int nx = p.x + dx[index];
                            int ny = p.y + dy[index];

                            if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                            if(arr[ny][nx] == 0 || visited[ny][nx]) continue;

                            que.offer(new Pair(nx, ny));
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
            System.out.println(count);
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