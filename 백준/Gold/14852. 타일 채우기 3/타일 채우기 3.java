import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[1000001][2];
        dp[0][0] = 1; dp[0][1] = 1;
        dp[1][0] = 2; dp[1][1] = 3;
        dp[2][0] = 7; dp[2][1] = 10;

        for (int i = 3; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] * 2 + dp[i - 2][0] * 3 + dp[i - 3][1] * 2) % 1000000007;
            dp[i][1] = (dp[i][0] + dp[i - 1][1]) % 1000000007;
        }
        System.out.println(dp[n][0]);
    }
}
