import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int target = Integer.parseInt(br.readLine());

        int[] dp = new int[5001];
        dp[3] = 1;
        dp[5] = 1;
        for (int i = 6; i <= target; i++) {
            if (dp[i - 3] != 0) {
                dp[i] = dp[i - 3] + 1;
            }
            if (dp[i - 5] != 0) {
                if (dp[i] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - 5] + 1);
                } else {
                    dp[i] = dp[i - 5] + 1;
                }
            }
//            System.out.println("dp[" + i + "] =" + dp[i]);
        }
        if (dp[target] == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[target]);
    }
}