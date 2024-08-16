import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[1000001][2];
        dp[0][0] = 1;
        dp[1][0] = 2;
        dp[2][0] = 7;

        for (int i = 3; i <= n; i++) {
            dp[i][1] = (dp[i - 3][0] * 2 + dp[i - 1][1]) % 1000000007;
            dp[i][0] = (dp[i - 1][0] * 2 + dp[i - 2][0] * 3 + dp[i][1]) % 1000000007;
        }
        System.out.println(dp[n][0]);
    }

//    public static void main(String[] args) throws IOException {
//
//        int n = Integer.parseInt(br.readLine());
//        long[][] d = new long[1000001][2];
//        d[0][0] = 1;
//        d[1][0] = 2;
//        d[2][0] = 7;
//        d[2][1] = 0;
//
//        for (int i = 3; i <= n; i++) {
//            d[i][1] = (d[i - 3][0] + d[i - 1][1]) % 1000000007;
//            d[i][0] = (2 * d[i - 1][0] + 3 * d[i - 2][0] + 2 * d[i][1]) % 1000000007;
//        }
//        System.out.println(d[n][0]);
//    }
}
