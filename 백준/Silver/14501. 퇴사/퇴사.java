
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;
    static int[][] input;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 2];
        input = new int[2][n + 2];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            input[0][i] = Integer.parseInt(st.nextToken());
            input[1][i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = n; i >= 1; i--) {
            int time = input[0][i];
            int revenue = input[1][i];
            if(time + i <= n + 1) {
//                System.out.println("i = " + i);
//                System.out.println(revenue + dp[i + time]);
                int availableMaxRevenue = 0;
                for(int j = i + time; j <= n; j++) {
                    availableMaxRevenue = Math.max(availableMaxRevenue, dp[j]);
                }
                dp[i] = revenue + availableMaxRevenue;
                max = Math.max(max, dp[i]);
            }
        }

        System.out.println(max);
    }
}