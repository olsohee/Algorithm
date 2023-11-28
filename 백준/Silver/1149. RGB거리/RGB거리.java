
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] cost; // 각 집의 색칠 가격
    static int[][] dp; // dp[i]: i번째 집을 칠하는 최소 가격

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        cost = new int[n + 1][3];
        dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        // 초기값
        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        dp[1][2] = cost[1][2];

        // dp(Bottom-Up)
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }

        // n번째 집을 색칠하는데 드는 최소 비용 구하기
        System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
    }
}
