
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - BFS
     * - 이동 순서: 불 -> 상근
     * - 불 위치부터 bfs 시작: 벽이 아니면서 방문 안한 경우 이동, 방문 처리
     * - 상근이 위치부터 bfs 시작: 빈 공간이면서 방문 안한 경우 이동, 방문 처리
     * - 상근이까지 이동했는데 불이랑 같은 위치이면 탈출 실패
     * - 상근이 위치가 그래프 밖이면 탈출 성공
     *
     * 2. 시간 복잡도
     * - BFS: O(V+E)
     *
     * 3. 자료구조
     * - 상훈 그래프, 방문 그래프
     * - 불 그래프, 방문 그래프
     * - 상훈 큐, 불 큐
     */

    static int tc; // 테스트 케이스 개수
    static int w; // 너비
    static int h; // 높이
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static StringTokenizer st;
    static BufferedReader br;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Pair> que;
    static Queue<Pair> fireQue;

    public static void main(String[] args) throws IOException {

        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc; t++) {

            que = new LinkedList<>();
            fireQue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];

            // 그래프 입력
            for(int i = 0; i < h; i++) {
                String s = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);

                    // 상근이
                    if(map[i][j] == '@') {
                        que.offer(new Pair(j, i));
                        visited[i][j] = true;
                        map[i][j] = '.';
                    }

                    // 불
                    if(map[i][j] == '*') {
                        fireQue.offer(new Pair(j, i));
                    }
                }
            }

            // bfs
            bfs();
        }
    }

    static void bfs() {

        int count = 0;

        while(!que.isEmpty()) {

            // 불
            int fireSize = fireQue.size();
            int size = que.size();
            for(int i = 0; i < fireSize; i++) {
                Pair fireP = fireQue.poll();
                for(int j = 0; j < 4; j++) {
                    int nx = fireP.x + dx[j];
                    int ny = fireP.y + dy[j];

                    if(nx < 0 || nx >= w || ny < 0 || ny >= h) {
                        continue;
                    }

                    // 빈공간이 아니거나 방문한 경우 패스
                    if(map[ny][nx] != '.') {
                        continue;
                    }

                    map[ny][nx] = '*';
                    fireQue.offer(new Pair(nx, ny));
                }
            }

            // 상근
            for(int i = 0; i < size; i++) {
                Pair p = que.poll();
                for(int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    // 그래프 밖이면 탈출 성공
                    if(nx < 0 || nx >= w || ny < 0 || ny >= h) {
                        System.out.println(++count);
                        return;
                    }

                    // 빈 공간이 아니면 패스(벽, 불), 이미 방문했으면 패스
                    if(map[ny][nx] != '.' || visited[ny][nx]) {
                        continue;
                    }

                    que.offer(new Pair(nx, ny));
                    visited[ny][nx] = true;

                    // 더이상 이동할 곳이 없을 때 큐에 offer을 안하므로 큐가 빈다.
                }
            }
            count++;
        }
        System.out.println("IMPOSSIBLE");
    }

    static class Pair {

        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}