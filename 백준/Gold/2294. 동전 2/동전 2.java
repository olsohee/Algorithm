import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        dp[0] = 0;

        for (int i = 1; i <= k; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin > i || dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                }
                min = Math.min(min, dp[i - coin] + 1);
            }

            dp[i] = min;
        }

        int answer = (dp[k] == Integer.MAX_VALUE) ? -1 : dp[k];
        System.out.println(answer);
    }
}