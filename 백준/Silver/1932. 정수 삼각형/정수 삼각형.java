import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp
        dp[0][0] = map[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + map[i][j];
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[n - 1][i]);
        }
        System.out.println(answer);
    }
}
