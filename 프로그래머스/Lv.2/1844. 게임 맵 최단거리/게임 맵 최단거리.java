import java.util.LinkedList;
import java.util.Queue;

// 시간 복잡도: O(V+E) = 10,000 + 40,000
class Solution {

    int[][] maps;
    boolean[][] visited;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int m;
    int n;
    Queue<Point> que = new LinkedList<>();

    public int solution(int[][] maps) {
        this.maps = maps;
        this.visited = new boolean[maps.length][maps[0].length];
        this.m = maps.length; // 세로
        this.n = maps[0].length; // 가로

        visited[0][0] = true;
        que.add(new Point(0, 0));

        while (!que.isEmpty()) {
            Point p = que.poll();
            for (int i = 0; i < 4; i++) {
                // 탈출
                if (p.x == n - 1 && p.y == m - 1) {
                    return maps[p.y][p.x];
                }

                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[ny][nx] || maps[ny][nx] == 0) {
                    continue;
                }

                que.add(new Point(ny, nx));
                maps[ny][nx] = maps[p.y][p.x] + 1;
                visited[ny][nx] = true;
            }
        }

        return -1;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
