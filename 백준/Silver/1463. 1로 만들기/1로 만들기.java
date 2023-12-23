import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

// dp[i]: 정수 i를 1로 만드는 연산의 사용 횟수의 최솟값
// 시간복잡도: O(N) = 10^6
public class Main {

    static int n;
    static long[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1];

        // 초기화
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            
            // 3으로 나누는 경우
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }

            // 2로 나누는 경우
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}