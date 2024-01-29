import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t;
    static int n;
    static int[][] input;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            input = new int[2][n + 1];
            dp = new int[2][n + 1];

            // input 배열 초기화
            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= n; k++) {
                    input[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // dp
            dp[0][1] = input[0][1];
            dp[1][1] = input[1][1];
            for (int j = 2; j <= n; j++) {
                dp[0][j] = Math.max((dp[1][j - 1] + input[0][j]), Math.max(dp[0][j - 2], dp[1][j - 2]) + input[0][j]);
                dp[1][j] = Math.max((dp[0][j - 1] + input[1][j]), Math.max(dp[0][j - 2], dp[1][j - 2]) + input[1][j]);
            }

            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
