import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 시간 복잡도:
public class Main {

    static char[][] map = new char[12][6];
    static int pop = 0;
    static boolean isPop = false;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 그래프 초기화
        for (int i = 0; i < 12; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 6; j++) {
                map[i][j] = input[j];
            }
        }

        // bfs
        while (true) {
            isPop = false; // 초기화
            bfs();

            // 터트리지 않았으면 끝내기
            if (!isPop) {
                break;
            }

            // 터트렸으면 카운트하고 아래로 내리기
            pop++;
            onFloor();
        }

        System.out.println(pop);
    }

    private static void bfs() {
        boolean[][] visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (!visited[i][j] && map[i][j] != '.') {
                    Queue<Point> que = new LinkedList<>();
                    List<Point> list = new ArrayList<>();
                    // bfs
                    que.add(new Point(i, j));
                    visited[i][j] = true;
                    list.add(new Point(i, j));

                    while (!que.isEmpty()) {
                        Point p = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = dx[k] + p.x;
                            int ny = dy[k] + p.y;
                            if (nx < 0 || nx >= 6 || ny < 0 || ny >= 12) {
                                continue;
                            }
                            if (map[ny][nx] == map[i][j] && !visited[ny][nx]) {
                                que.add(new Point(ny, nx));
                                visited[ny][nx] = true;
                                list.add(new Point(ny, nx));
                            }
                        }
                    }

                    // 인접한 같은 색이 4개 이상이면 터트리기(. 표시)
                    if (list.size() >= 4) {
                        for (int k = 0; k < list.size(); k++) {
                            Point p = list.get(k);
                            map[p.y][p.x] = '.';
                        }
                        isPop = true;
                    }
                }
            }
        }
    }

    private static void onFloor() {

        for (int i = 0; i < 6; i++) {
            Queue<Character> que = new LinkedList<>();
            for (int j = 11; j >= 0; j--) {
                if (map[j][i] != '.') {
                    que.add(map[j][i]);
                }
            }

            for (int j = 11; j >= 0; j--) {
                if (!que.isEmpty()) {
                    map[j][i] = que.poll();
                } else {
                    map[j][i] = '.';
                }
            }
        }
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}