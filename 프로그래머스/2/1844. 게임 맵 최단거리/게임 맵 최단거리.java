
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    int[][] maps;
    boolean[][] visited;
    int m;
    int n;
    int answer;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(int[][] maps) {

        this.maps = maps;
        m = maps.length;
        n = maps[0].length;
        visited = new boolean[m][n];

        bfs(0,0);

        return answer;
    }

    public void bfs(int y, int x) {

        // 시작점(0,0)부터 bfs 시작
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(y, x)); // 큐에 넣기
        visited[y][x] = true; // 방문 처리
        answer++;

        while(!que.isEmpty()) {

            Point p = que.poll();
            // 사방면 탐색
            for(int i = 0; i < 4; i++) {
                int ny = dy[i] + p.y;
                int nx = dx[i] + p.x;

                // 도달한 경우
                if((ny == m - 1) && (nx == n - 1)) {
                    maps[ny][nx] = maps[p.y][p.x] + 1;
                    answer = maps[ny][nx];
                    return;
                }

                // 그래프 밖인 경우
                if(ny < 0 || ny >= m || nx < 0 || nx >= n) {
                    continue;
                }

                // 벽이거나(0) 이미 방문한 경우
                if(maps[ny][nx] == 0 || visited[ny][nx]) {
                    continue;
                }

                que.offer(new Point(ny, nx));
                visited[ny][nx] = true;
                maps[ny][nx] = maps[p.y][p.x] + 1;
            }
        }

        // 도달하지 못한 경우
        answer = -1;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}