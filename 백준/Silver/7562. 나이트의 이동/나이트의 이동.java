
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - BFS
     * - 출발 지점에서 bfs 시작
     * - 이동할 수 있는 경우의 수대로 탐색
     * - 이동한 위치에 이전 값 + 1 저장
     * - 도착 지점에 도착한 경우, 해당 위치의 값 출력(이동 횟수를 의미한다.)
     *
     * 2. 시간 복잡도
     * - BFS: O(V+E)
     *
     * 3. 자료구조
     * - 그래프 int[][][]
     * - 큐
     */

    static int T; // 테스트 케이스 개수
    static int L; // 한 변의 길기
    static int[][] map;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int startY;
    static int startX;
    static int endY;
    static int endX;
    static StringTokenizer st;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            L = Integer.parseInt(br.readLine());
            map = new int[L][L];

            // 시작 위치에 1 저장
            st = new StringTokenizer(br.readLine());
            startY = Integer.parseInt(st.nextToken());
            startX = Integer.parseInt(st.nextToken());
            map[startY][startX] = 1;

            st = new StringTokenizer(br.readLine());
            endY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());

            // bfs
            bfs(new Pair(startX, startY));
        }
    }

    static void bfs(Pair pair) {

        Queue<Pair> que = new LinkedList<>();
        que.offer(pair);

        while (!que.isEmpty()) {
            Pair p = que.poll();

            // 도착 지점인 경우 이동 횟수 출력 후 끝내기
            if(p.x == endX && p.y == endY) {
                System.out.println(map[p.y][p.x] - 1);
                return;
            }

            // 8방면 탐색
            for(int i = 0; i < 8; i++) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                // 그래프를 벗어나거나 이미 방문한 경우(0이 아닌 경우) pass
                if(ny < 0 || nx < 0 || ny >= L || nx >= L || map[ny][nx] != 0) {
                    continue;
                }

                // 방문 처리
                map[ny][nx] = map[p.y][p.x] + 1;
                que.offer(new Pair(nx, ny));
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
