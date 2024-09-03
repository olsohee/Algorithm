import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int[][][] dp;
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

        dp = new int[3][n + 1][n + 1]; // dp[k][i][j] : k 방향으로 (i, j)에 파이프를 놓는 방법의 수

        //dp
        // dir: 0(가로), 1(세로), 2(대각선)
        dp[0][1][2] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                // 가로
                if (map[i][j] == 0) {
                    dp[0][i][j] += dp[0][i][j - 1] + dp[2][i][j - 1];
                }

                // 세로
                if (map[i][j] == 0) {
                    dp[1][i][j] += dp[1][i - 1][j] + dp[2][i - 1][j];
                }

                // 대각선
                if (map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                    dp[2][i][j] += dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
                }
            }
        }

        System.out.println(dp[0][n][n] + dp[1][n][n] + dp[2][n][n]);
    }
}
