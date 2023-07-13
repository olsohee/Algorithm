import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int K;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N + 1];
        dp = new int[K + 1];

        for(int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= K; i++) {
            dp[i] = 100001;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = coins[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        if(dp[K] == 100001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[K]);
        }
    }
}