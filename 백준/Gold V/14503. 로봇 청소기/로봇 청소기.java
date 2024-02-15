import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N; // 세로
    static int M; // 가로
    static int d;
    static int[][] map;
    static int nowY;
    static int nowX;
    static int answer = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int reverseDx[] = {0, -1, 0, 1};
    static int reverseDy[] = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        nowY = Integer.parseInt(st.nextToken());
        nowX = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        start();
        System.out.println(answer);
    }

    private static void start() {
        // 현재 칸이 청소되지 않았으면 청소
        if (map[nowY][nowX] == 0) {
            map[nowY][nowX] = -1;
            answer++;
        }

        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nx = nowX + dx[d];
            int ny = nowY + dy[d];
            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            // 주변에 청소되지 않은 벽이 있는 경우, 90도 회전하고(d 변경) 전진하기(nowY, nowX 변경)
            if (map[ny][nx] == 0) {
                nowX = nx;
                nowY = ny;
                start();
                return;
            }
        }

        // 주변에 청소할 빈칸이 없는 경우, 후진할 수 있으면 후진
        int dir = (d + 2) % 4;
        int bx = nowX + dx[dir];
        int by = nowY + dy[dir];
        if (bx >= 0 && bx < M && by >= 0 && by < N && map[by][bx] != 1) {
            nowX = bx;
            nowY = by;
            start();
        }
    }
}