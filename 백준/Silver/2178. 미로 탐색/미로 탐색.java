
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] miro = new int[n][m]; //미로 저장할 배열
        int[][] dist = new int[n][m]; //거리 저장할 배열
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Pair> que = new LinkedList<>();

        //미로 배열 초기화
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                miro[i][j] = str.charAt(j) - '0';
                dist[i][j] = -1; //아직 방문하지 않은 경우에는 -1
            }
        }

        que.offer(new Pair(0, 0)); //시작점
        dist[0][0] = 0; //시작점의 거리는 0

        while(!que.isEmpty()) {
            Pair p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                //벽에 부딫히거나, 범위를 넘어가면 패스
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                //길이 아니거나, 방문했으면 패스
                if(miro[nx][ny] == 0 || dist[nx][ny] != -1) {
                    continue;
                }

                que.offer(new Pair(nx, ny));
                dist[nx][ny] = dist[p.x][p.y] + 1;
            }
        }

        System.out.println(dist[n-1][m-1] + 1);
    }

    public static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}