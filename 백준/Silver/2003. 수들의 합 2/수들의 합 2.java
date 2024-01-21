import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N)
public class Main {

    static int n;
    static int m;
    static int[] arr;
    static long answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        long sum = 0;
        while (end <= n) {
            if (sum == m) {
                answer++;
            }

            if (sum <= m) {
                sum += arr[end++];
            } else {
                sum -= arr[start++];
            }
        }

        System.out.println(answer);
    }
}