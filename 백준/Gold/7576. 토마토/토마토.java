import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int m;
    static int n;
    static int[][] board;
    static Queue<Pair> que;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        que = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1) {
                    que.offer(new Pair(j, i));
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        while(!que.isEmpty()) {
            Pair p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && nx < m && ny >= 0 && ny < n && board[ny][nx] == 0) {
                    que.offer(new Pair(nx, ny));
                    board[ny][nx] = board[p.y][p.x] + 1;
                }
            }
        }

        int result = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 0) return -1; //안익은 토마토가 있으면 -1 반환
                result = Math.max(result, board[i][j]);
            }
        }

        if(result == 1)
            return 0; //토마토가 모두 익은 상태였다면 0 반환
        else
            return result -1; //걸린 일수는 -1을 빼줘야 함
    }

    public static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
