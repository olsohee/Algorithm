
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 복잡도: O(N) = 100,000
public class Main {

    static int n;
    static int[] input;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = new int[n + 1];
        dp = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(input[i], dp[i - 1] + input[i]);
        }

        long answer = dp[1];
        for (int i = 2; i <= n; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
