import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] arr = new int[t + 1][3];
        int[][] dp = new int[t + 1][w + 1];

        for (int i = 1; i <= t; i++) {
            int tree = Integer.parseInt(br.readLine());
            if (tree == 1) {
                arr[i][1] = 1;
            }
            if (tree == 2) {
                arr[i][2] = 1;
            }
        }

        // dp 배열 초기화
        dp[1][0] = arr[1][1];
        dp[1][1] = arr[1][2];

        // dp
        for (int i = 2; i <= t; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][1]; // 1번 나무에서 쭉 내려오는 경우
            for (int j = 1; j <= w; j++) {
                int now = (j % 2 == 0 ? arr[i][1] : arr[i][2]);
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + now;
            }
        }

        // 결과 출력
        int answer = 0;
        for (int i = 0; i <= w; i++) {
            answer = Math.max(answer, dp[t][i]);
        }
        System.out.println(answer);
    }
}
