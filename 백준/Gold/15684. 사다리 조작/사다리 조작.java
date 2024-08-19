import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, h;
    static boolean[][] map;
    static int answer = 4;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 0; i <= 3; i++) {
            if (answer != 4) break;
            addLadder(i, 0);
        }

        if (answer == 4) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void addLadder(int maxCnt, int cnt) {

        if (finish()) {
            answer = cnt;
            return;
        }

        if (cnt == maxCnt) {
            return;
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n - 1; j++) {
                // 해당 위치에 사다리가 있으면 pass
                if (map[i][j]) continue;

                // 사다리가 좌우에 있으면 pass
                if (map[i][j - 1] || map[i][j + 1]) continue;

                map[i][j] = true;
                addLadder(maxCnt, cnt + 1);
                map[i][j] = false;
            }
        }
    }

    private static boolean finish() {

        for (int i = 1; i <= n; i++) {
            int y = 1;
            int x = i;
            while (y < h + 1) {
                if (map[y][x]) {
                    x++;
                } else if (map[y][x - 1]) {
                    x--;
                }
                y++;
            }

            if (i != x) return false;
        }

        return true;
    }
}
