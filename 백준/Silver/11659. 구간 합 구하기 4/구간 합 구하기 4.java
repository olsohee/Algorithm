import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp[i]: 인덱스 i에 담긴 값까지의 합
// 시간복잡도: O(N) = 100,000
public class Main {

    static int n;
    static int m;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int startIdx = Integer.parseInt(st.nextToken());
            int endIdx = Integer.parseInt(st.nextToken());
            System.out.println(dp[endIdx] - dp[startIdx - 1]);
        }
    }
}