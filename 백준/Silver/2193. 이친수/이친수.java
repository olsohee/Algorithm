
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

public class Main {

    static long[] dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1];
        dp[1] = 1;
        if(n >= 2) {
            dp[2] = 1;
        }

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        System.out.println(dp[n]);
    }
}
