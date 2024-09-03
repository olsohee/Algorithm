import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs
        dfs(1, 2, 1);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int dir) {

        if (y == n && x == n) {
            answer++;
            return;
        }

        if (dir == 1) {
            // 오른쪽 이동
            if (x + 1 <= n) {
                if (map[y][x + 1] == 0) {
                    dfs(y, x + 1, 1);
                }
            }

            // 대각선 이동
            if (y + 1 <= n && x + 1 <= n) {
                if (map[y + 1][x] == 0 && map[y + 1][x + 1] == 0 && map[y][x + 1] == 0) {
                    dfs(y + 1, x + 1, 3);
                }
            }
        }

        if (dir == 2) {
            // 아래 이동
            if (y + 1 <= n) {
                if (map[y + 1][x] == 0) {
                    dfs(y + 1, x, 2);
                }
            }

            // 대각선 이동
            if (y + 1 <= n && x + 1 <= n) {
                if (map[y + 1][x] == 0 && map[y + 1][x + 1] == 0 && map[y][x + 1] == 0) {
                    dfs(y + 1, x + 1, 3);
                }
            }
        }

        if (dir == 3) {
            // 오른쪽 이동
            if (x + 1 <= n) {
                if (map[y][x + 1] == 0) {
                    dfs(y, x + 1, 1);
                }
            }

            // 아래 이동
            if (y + 1 <= n) {
                if (map[y + 1][x] == 0) {
                    dfs(y + 1, x, 2);
                }
            }

            // 대각선 이동
            if (y + 1 <= n && x + 1 <= n) {
                if (map[y + 1][x] == 0 && map[y + 1][x + 1] == 0 && map[y][x + 1] == 0) {
                    dfs(y + 1, x + 1, 3);
                }
            }
        }
    }
}
