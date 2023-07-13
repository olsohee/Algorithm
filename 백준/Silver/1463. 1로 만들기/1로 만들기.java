import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        Arrays.fill(dp, -1);

        StringBuilder sb = new StringBuilder();
        sb.append(func(n));
        System.out.println(sb);
    }

    public static int func(int n) {

        if(n == 1) return 0;

        // 이전에 계산한 적이 있는 경우
        if(dp[n] != -1) return dp[n];

        if(n % 6 == 0) {
            dp[n] = Math.min(Math.min(func(n/2) + 1, func(n/3) + 1), func(n-1) + 1);
        } else if(n % 3 == 0) {
            dp[n] = Math.min(func(n-1) + 1, func(n/3) + 1);
        } else if(n % 2 == 0) {
            dp[n] = Math.min(func(n-1) + 1, func(n/2) + 1);
        } else {
            dp[n] = func(n-1) + 1;
        }

        return dp[n];
    }
}