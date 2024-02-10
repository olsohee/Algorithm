import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i >= j * j; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }

        System.out.println(dp[n]);
    }
}
