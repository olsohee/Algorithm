
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 복잡도: O(N) = 1,500,000
public class Main {

    static int n;
    static int[][] input;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 2];
        input = new int[2][n + 2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            input[0][i] = Integer.parseInt(st.nextToken());
            input[1][i] = Integer.parseInt(st.nextToken());
        }

        int max = 0; // i날짜까지의 최대 수입
        for (int i = 1; i <= n + 1; i++) {
            max = Math.max(max, dp[i]);

            int nextDay = i + input[0][i];
            int revenue = input[1][i];
            if (nextDay <= n + 1) {
                dp[nextDay] = Math.max(dp[nextDay], revenue + max);
            }
        }

        System.out.println(dp[n + 1]);
    }
}
