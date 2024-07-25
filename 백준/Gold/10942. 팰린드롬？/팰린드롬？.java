import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        boolean[][] dp = new boolean[n + 1][n + 1];
        int[] input = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // dp 배열 초기화
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i <= n - 1; i++) {
            if (input[i] == input[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // dp
        for (int i = n - 2; i >= 1; i--) {
            for (int j = i + 2; j <= n; j++) {
                if (dp[i + 1][j - 1] && input[i] == input[j]) {
                    dp[i][j] = true;
                }
            }
        }

        // 답 출력
        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int answer = dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] ? 1 : 0;
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
