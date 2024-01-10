
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

// 시간복잡도: O(N) = 100
public class Main {

    static int t;
    static int n;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int j = 4; j <= 100; j++) {
            dp[j] = dp[j - 2] + dp[j - 3];
        }

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}