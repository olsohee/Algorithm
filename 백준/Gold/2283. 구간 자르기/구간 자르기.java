
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

        int[] pSum = new int[1000001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pSum[start]++;
            pSum[end]--;
        }

        // 누적합 더하기
        for (int i = 1; i <= 1000000; i++) {
            pSum[i] += pSum[i - 1];
        }

        // 투포인터로 구간 찾기
        int start = 0;
        int end = 0;
        int sum = pSum[0];
        while (end < 1000000) {
            if (sum == k) {
                System.out.println(start + " " + (end + 1));
                return;
            }
            if (sum < k) {
                end++;
                sum += pSum[end];
            }
            if (sum > k) {
                sum -= pSum[start];
                start++;
            }
        }

        System.out.println("0 0");
    }
}
