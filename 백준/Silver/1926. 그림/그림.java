import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간복잡도: O(N) = 300
// dp[i] = i까지 올라오는 데 얻을 수 있는 점수의 최댓값
public class Main {

    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static boolean[][] visited;
    static int count = 0; // 그림의 개수
    static int maxArea = 0; // 그림 중 가장 큰 것의 넓이
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 그림이면서 아직 방문하지 않은 부분 -> 탐색 시작하기
                if (map[i][j] == 1 && !visited[i][j]) {
                    count++;
                    visited[i][j] = true;
                    bfs(new Pair(j, i));
                }
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }

    private static void bfs(Pair pair) {
        int area = 0;
        Queue<Pair> que = new LinkedList<>();
        que.offer(pair);
        area++;

        while (!que.isEmpty()) {
            Pair p = que.poll();

            // 사방 탐색
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] == 1) {
                    que.offer(new Pair(nx, ny));
                    area++;
                    visited[ny][nx] = true;
                }
            }
        }
        maxArea = Math.max(maxArea, area);
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}