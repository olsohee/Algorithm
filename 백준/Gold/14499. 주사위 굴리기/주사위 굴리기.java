
import java.io.*;
import java.util.*;


public class Main {

    static int[][] map;
    static int[] dice = new int[6];
    static int N;
    static int M;
    static int x;
    static int y;
    static int K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 길이
        M = Integer.parseInt(st.nextToken()); // 가로 길이
        map = new int[N][M];
        y = Integer.parseInt(st.nextToken()); // 세로 좌표
        x = Integer.parseInt(st.nextToken()); // 가로 좌표
        K = Integer.parseInt(st.nextToken());


        // 지도 초기화
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken());
            move(direction);
        }
    }

    private static void move(int direction) {

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 이동 후 주사위의 위치
        int nx = x + dx[direction - 1];
        int ny = y + dy[direction - 1];

        if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
            return;
        }

        x = nx;
        y = ny;
        roll(direction);

    }

    private static void roll(int direction) {

        switch (direction) {
            case 1:
                // 오른쪽으로 이동
                int temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
                break;

            case 2:
                // 왼쪽으로 이동
                temp = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[4];
                dice[4] = temp;
                break;

            case 3:
                // 위으로 이동
                temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
                break;

            case 4:
                // 아래로 이동
                temp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[3];
                dice[3] = temp;
                break;
        }

        if(map[y][x] == 0)  {
            map[y][x] = dice[0];
        } else {
            dice[0] = map[y][x];
            map[y][x] = 0;
        }

        System.out.println(dice[1]);
    }
}