
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - BFS
     * - 이중 for문 돌면서 bfs의 시작점부터 bfs 시작(큐에 넣고 bfs 시작)
     * - 이때 적록색약인 사람은 R, G을 같은 취급
     * - bfs의 시작점을 카운트하기
     *
     * 2. 시간 복잡도
     * - BFS: O(V+E)
     *
     * 3. 자료구조
     * - 그래프 char[][]
     * - 큐, 방문 기록
     * - 카운드 int count
     */

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Pair> que = new LinkedList<>();
    static int count1;
    static int count2;

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for(int  i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // bfs(적록색약 X)
        for(int  i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    bfs1(i, j);
                }
            }
        }
        System.out.println(count1);

        // 방문기록 초기화
        for(int  i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }

        // bfs(적록색약 O)
        for(int  i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    bfs2(i, j);
                }
            }
        }
        System.out.println(count2);
    }

    static void bfs1(int y, int x) {

        char color = map[y][x];
        que.offer(new Pair(x, y));
        visited[y][x] = true;
        count1++;

        while(!que.isEmpty()) {
            Pair p = que.poll();

            // 사방면 탐색
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                // 그래프 밖인 경우 pass
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                // 이미 방문했거나 다른 색일 경우 경우 pass
                if(visited[ny][nx] || map[ny][nx] != color) {
                    continue;
                }

                que.offer(new Pair(nx, ny));
                visited[ny][nx] = true;
            }
        }
    }

    static void bfs2(int y, int x) {

        char color = map[y][x];
        que.offer(new Pair(x, y));
        visited[y][x] = true;
        count2++;

        while(!que.isEmpty()) {
            Pair p = que.poll();

            // 사방면 탐색
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                // 그래프 밖인 경우 pass
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                // 이미 방문했거나 다른 색이면 pass
                if(visited[ny][nx]) {
                    continue;
                }

                if(color == 'G' || color == 'R') {
                    if(map[ny][nx] == 'B') {
                        continue;
                    }
                } else {
                    if(map[ny][nx] != color) {
                        continue;
                    }
                }

                que.offer(new Pair(nx, ny));
                visited[ny][nx] = true;
            }
        }
    }

    static class Pair {

        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
