import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int m;
    static int x;
    static int y;
    static int k;
    static int[][] map;
    static int[] dice = new int[6];

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                if (y + 1 >= m) continue;
                moveRight();
                check();
            }
            if (command == 2) {
                if (y - 1 < 0) continue;
                moveLeft();
                check();
            }
            if (command == 3) {
                if (x - 1 < 0) continue;
                moveUp();
                check();
            }

            if (command == 4) {
                if (x + 1 >= n) continue;
                moveDown();
                check();
            }
            System.out.println(dice[0]);
        }
    }

    private static void check() {
        if (map[x][y] == 0) {
            map[x][y] = dice[2]; // 바닥면 -> map으로 복사
        } else {
            dice[2] = map[x][y]; // map -> 바닥면으로 복사
            map[x][y] = 0;
        }
    }

    private static void moveRight() {
        y++; // 주사위 위치 이동

        // 주사위 값 이동
        int temp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
        dice[5] = temp;
    }

    private static void moveLeft() {
        y--; // 주사위 위치 이동

        // 주사위 값 이동
        int temp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[2];
        dice[2] = dice[4];
        dice[4] = temp;
    }

    private static void moveUp() {
        x--; // 주사위 위치 이동

        // 주사위 값 이동
        int temp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[3];
        dice[3] = temp;
    }

    private static void moveDown() {
        x++; // 주사위 위치 이동

        // 주사위 값 이동
        int temp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[2];
        dice[2] = dice[1];
        dice[1] = temp;
    }
}