import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] time = new int[n];
        int[] revenue = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            revenue[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int max = 0; // 현재 날짜까지 최대로 받은 수익
        for (int i = 0 ; i < n; i++) {
            int endDay = i + time[i] - 1;
            if (endDay < n) {
                dp[endDay] = Math.max(dp[endDay], revenue[i] + max);
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
