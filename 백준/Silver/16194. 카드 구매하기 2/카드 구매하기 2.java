import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] costs = new int[n + 1];
        int[] dp = new int[n + 1]; // dp[i] = i개 카드를 모으는데 드는 최소 비용

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            dp[i] = costs[i];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
            }
        }
        System.out.println(dp[n]);
    }
}
