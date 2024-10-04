
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
        int[][] tree = new int[t + 1][3];
        for (int i = 1; i <= t; i++) {
            int treeNum = Integer.parseInt(br.readLine());
            tree[i][treeNum] = 1;
        }

        // dp
        int[][] dp = new int[t + 1][w + 1];
        dp[1][0] = tree[1][1]; // 1초에 1번 나무에 자두 있는 경우
        dp[1][1] = tree[1][2]; // 1초에 2번 나무에 자두 있는 경우

        for (int i = 2; i <= t; i++) {
            for (int j = 0; j <= w; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][0] + tree[i][1];
                    continue;
                }

                // 홀수번 움직인 경우 -> 현재 2번 나무에 있음
                if (j % 2 != 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + tree[i][2];
                }
                // 짝수번 움직인 경우 -> 현재 1번 나무에 있음
                else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + tree[i][1];
                }
            }
        }

        // t초까지 자두를 받은 후, 최대로 받은 자두 개수 찾기(꼭 w번 움직인 것이 아님)
        int answer = 0;
        for (int i = 0; i <= w; i++) {
            answer = Math.max(answer, dp[t][i]);
        }

        System.out.println(answer);
    }
}