import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] coins = new int[n];
            for (int j = 0; j < n; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());

            // dp
            int[] dp = new int[m + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int j = coin; j <= m; j++) {
                    dp[j] += dp[j - coin];
                }
            }
            
            System.out.println(dp[m]);
        }
    }
}