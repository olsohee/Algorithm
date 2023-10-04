
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - BFS
     * - 그래프 탐색하면서 익은 토마토들 큐에 넣기
     * - 큐를 대상으로 bfs 시작
     * - 큐에서 요소 빼서 6방면 살피기
     *
     * 2. 시간 복잡도
     * - BFS: O(V+E)
     *
     * 3. 자료구조
     * - 그래프 int[][][]
     * - 큐
     */

    static int H; // 높이
    static int M; // 가로
    static int N; // 세로
    static int[][][] map;
    static Queue<Pair> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H =  Integer.parseInt(st.nextToken()); // 높이
        map = new int[H][N][M];


        // 그래프 초기화, 익은 토미토 큐에 넣기
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    int input = Integer.parseInt(st.nextToken());
                    map[i][j][k] = input;
                    if(input == 1) {
                        que.offer(new Pair(k, j, i));
                    }
                }
            }
        }

        // bfs
        bfs();

        // 전체 탐색 후 최대값 출력
        int max = 0;
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    if(map[i][j][k] == 0) {
                        // 안익은 토마토가 있으면 -1 출력
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, map[i][j][k]);
                }
            }
        }
        System.out.println(max - 1);
    }

    static void bfs() {

        int[] dh = {0, 0, 0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0, 0, 0};
        int[] dx = {0, 0, 1, -1, 0, 0};

        while (!que.isEmpty()) {
            Pair p = que.poll();
            // 6방면 탐색
            for(int i = 0; i < 6; i++) {
                int nh = p.h + dh[i];
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                // 그래프를 벗어나는 경우 pass
                if(nh < 0 || ny < 0 || nx < 0 || nh >= H || ny >= N || nx >= M) {
                    continue;
                }

                // 0이 아닌 경우(이미 방문했거나 토마토가 없는 경우) pass
                if(map[nh][ny][nx] != 0) {
                    continue;
                }

                // 방문 처리
                map[nh][ny][nx] = map[p.h][p.y][p.x] + 1;
                que.offer(new Pair(nx, ny, nh));
            }

        }
    }

    static class Pair {

        int x, y, h;
        public Pair(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}