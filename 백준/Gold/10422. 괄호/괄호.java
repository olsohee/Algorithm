import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        final long MOD = 1000000007;
        int t = Integer.parseInt(br.readLine());
        long[] dp = new long[5001];
        dp[0] = 1;
        dp[2] = 1;

        for (int i = 4; i <= 5000; i += 2) {
            for (int j = 0; j < i; j += 2) {
                dp[i] += dp[i - j - 2] * dp[j];
                dp[i] %= 1000000007;
            }
        }

        for (int i = 0; i < t; i++) {
            System.out.println(dp[Integer.parseInt(br.readLine())]);
        }
    }
}
