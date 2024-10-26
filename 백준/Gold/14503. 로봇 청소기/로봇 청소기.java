
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static int dir;
    static int nowY, nowX;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        nowY = Integer.parseInt(st.nextToken());
        nowX = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        start();

        System.out.println(answer);
    }

    private static void start() {
        // 1. 현재 칸 청소
        if (map[nowY][nowX] == 0) {
            map[nowY][nowX] = -1;
            answer++;
        }

        // 2. 주변 네 칸 중 청소되지 않은 경우
        for (int i = 0; i < 4; i++) {
            int ny = nowY + dy[i];
            int nx = nowX + dx[i];
            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;

            if (map[ny][nx] == 0) {
                while (true) {
                    dir = (dir + 3) % 4;

                    // 청소 X인 경우 전진
                    if (map[nowY + dy[dir]][nowX + dx[dir]] == 0) {
                        nowY = nowY + dy[dir];
                        nowX = nowX + dx[dir];
                        start();
                        return;
                    }
                }
            }
        }

        // 3. 주변 네 칸이 모두 청소된 경우
        int ny = nowY + dy[(dir + 2) % 4];
        int nx = nowX + dx[(dir + 2) % 4];
        // 후진 가능하면 후진, 불가하면 끝내기
        if (ny >= 0 && ny < n && nx >= 0 && nx < m && map[ny][nx] != 1) {
            nowY = ny;
            nowX = nx;
            start();
        }
    }
}