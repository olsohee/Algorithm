import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] value = new int[n + 1][2]; // value[i] = i번 물건의 무게, 가치

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            value[i][0] = Integer.parseInt(st.nextToken());
            value[i][1] = Integer.parseInt(st.nextToken());
        }

        // dp
        int[][] dp = new int[n + 1][k + 1];
        // dp[i][j] = i번 물건까지 고려했을 때, j 무게를 채우는데 얻는 최대 가치
        for (int i = 1; i <= n; i++) {
            int w = value[i][0]; // i번 물건의 무게
            int v = value[i][1]; // i번 물건의 가치
            for (int j = 0; j <= k; j++) {
                if (j >= w) {
                    dp[i][j] = Math.max(v + dp[i - 1][j - w], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= k; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[n][k]);
    }
}