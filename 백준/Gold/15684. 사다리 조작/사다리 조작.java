import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, h;
    static int[][] map;
    static int answer = 4;
    static boolean isFinish = false;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            map[num1][num2] = 1;
            map[num1][num2 + 1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            addLadder(1, 0);
            if (isFinish) break;
        }

        System.out.println((isFinish) ? answer : -1);
    }

    private static void addLadder(int x, int cnt) {
        if (isFinish) return;
        if (answer == cnt) {
            if (finish()) isFinish = true;
            return;
        }

        for (int i = x; i <= h; i++) {
            for (int j = 1; j <= n - 1; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    addLadder(i, cnt + 1);
                    map[i][j] = map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean finish() {

        for (int i = 1; i <= n; i++) {
            int y = 1;
            int x = i;
            for (int j = 0; j < h; j++) {
                if (map[y][x] == 1) x++;
                else if (map[y][x] == 2) x--;
                y++;
            }

            if (i != x) return false;
        }
        return true;
    }
}
