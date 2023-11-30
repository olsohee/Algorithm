import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] dp;
    static int[] input;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1];
        input = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            input[i + 1] = Integer.parseInt(st.nextToken());
            dp[i + 1] = input[i + 1];
        }

        long max = dp[1];

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                if(input[j] < input[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + input[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.println(max);
    }
}