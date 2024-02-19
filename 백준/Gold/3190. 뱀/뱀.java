import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int l;
    static int[][] map;
    static int time;
    static int[] times;
    static String[] dirs;
    static List<Position> positions = new ArrayList<>();
    static int startY;
    static int startX;
    static int[] dx = {1, 0, -1, 0}; // dir: 0(오), 1(아래), 2(왼), 3(위)
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        l = Integer.parseInt(br.readLine());
        times = new int[l];
        dirs = new String[l];
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            dirs[i] = st.nextToken();
        }

        startX = 1;
        startY = 1;
        positions.add(new Position(startY, startX));
        time = 0;
        int dir = 0;
        while (true) {
            int nx = positions.get(0).x + dx[dir];
            int ny = positions.get(0).y + dy[dir];

            // 그래프 범위 확인
            if (nx <= 0 || nx > n || ny <= 0 || ny > n) break;

            if (map[ny][nx] == -1) {
                break;
            }
            // 이동
            if (map[ny][nx] == 1) {
                // 사과가 있는 경우
                map[ny][nx] = -1;
                positions.add(0, new Position(ny, nx)); // 앞머리 추가
            } else {
                // 사과가 없는 경우

                map[ny][nx] = -1;
                map[positions.get(positions.size() - 1).y][positions.get(positions.size() - 1).x] = 0;
                positions.remove(positions.size() - 1); // 꼬리 제거
                positions.add(0, new Position(ny, nx)); // 앞머리 추가
            }
            time++;

            // 방향 회전 판단
            for (int i = 0; i < l; i++) {
                if (time == times[i]) {
                    if (dirs[i].equals("L")) {
                        if (dir == 0) {
                            dir = 3;
                        } else {
                            dir -= 1;
                        }
                    }
                    if (dirs[i].equals("D")) {
                        if (dir == 3) {
                            dir = 0;
                        } else {
                            dir += 1;
                        }
                    }
                    break;
                }
            }
        }

        System.out.println(time + 1);
    }

    static class Position {
        int y, x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}