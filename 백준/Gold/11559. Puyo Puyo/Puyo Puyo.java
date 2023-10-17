
import java.io.*;
import java.util.*;


public class Main {


    static int result;
    static char[][] map = new char[12][6];
    static boolean isContinue = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }

        while (true) {

            // bfs로 터트릴 뿌요 체크 후 .로 변경
            isContinue = false;
            bfs();

            if(isContinue == false) {
                System.out.println(result);
                break;
            }

            // 아래로 밀기
            map = down();

            // 뿌요 한 번 터트릴 때마다 값 증가시키기
            result++;
        }
    }

    private static char[][] down() {

        char[][] newMap = new char[12][6];
        for(int i = 0; i < 6; i++) {
            int index = 11;
            for(int j = 11; j >= 0; j--) {
                newMap[j][i] = '.';
                if(map[j][i] != '.') {
                    newMap[index][i] = map[j][i];
                    index--;
                } else {
                    newMap[j][i] = '.';
                }
            }
        }
        return newMap;
    }

    public static void bfs() {

        boolean[][] visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if(map[i][j] != '.' && !visited[i][j]) {
                    // 한가지 색에 대한 bfs 시작
                    Queue<Point> que = new LinkedList<>();
                    List<Point> list = new ArrayList<>();

                    que.offer(new Point(j, i));
                    visited[i][j] = true;
                    list.add(new Point(j, i));

                    int[] dx = {1, -1, 0, 0};
                    int[] dy = {0, 0, 1, -1};

                    while (!que.isEmpty()) {
                        Point p = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = dx[k] + p.x;
                            int ny = dy[k] + p.y;

                            if (nx < 0 || nx >= 6 || ny < 0 || ny >= 12 || map[ny][nx] != map[p.y][p.x] || visited[ny][nx]) {
                                continue;
                            }

                            que.offer(new Point(nx, ny));
                            list.add(new Point(nx, ny));
                            visited[ny][nx] = true;
                        }
                    }

                    // 해당 색에 대한 bfs 끝나면 뿌요 터트리기
                    if(list.size() >= 4) {
                        isContinue = true;
                        for(int k = 0; k < list.size(); k++) {
                            Point p = list.get(k);
                            map[p.y][p.x] = '.';
                        }
                    }
                }
            }
        }
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}