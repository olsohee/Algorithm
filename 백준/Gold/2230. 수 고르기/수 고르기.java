
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N)
public class Main {

    static int n;
    static int m;
    static int[] arr;
    static long answer = Long.MAX_VALUE; // 차이가 m보다 큰 것 중 가장 작은 수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = 0;
        while (right < n && left <= right) {
            long diff = arr[right] - arr[left];
            if (diff < m) {
                right++;
            }
            else if (diff == m) {
                answer = m;
                break;
            }
            else {
                answer = Math.min(answer, diff);
                left++;
            }
        }
        System.out.println(answer);
    }
}