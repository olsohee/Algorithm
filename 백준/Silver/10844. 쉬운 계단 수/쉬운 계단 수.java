
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(N) = 100
public class Main {

    static int n;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][10];

        // n = 1
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i - 1][1] % 1000000000;
                }
                else if (j == 9) {
                    dp[i][9] += dp[i - 1][8] % 1000000000;
                }
                else {
                    dp[i][j] = (dp[i - 1][j - 1] +dp[i - 1][j + 1]) % 1000000000;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer += dp[n][i];
        }
        System.out.println(answer % 1000000000);
    }
}
