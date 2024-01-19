
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N)
public class Main {

    static int n;
    static int s;
    static int[] arr;
    static int answer = Integer.MAX_VALUE; // 가장 작은 갯수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        while (start <= end && end <= n) {
            if (sum < s) {
                sum += arr[end];
                end++;
            }
            else {
                answer = Math.min(answer, end - start);
                sum -= arr[start];
                start++;
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}