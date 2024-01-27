import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 = O(N * logN)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int m;
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        long max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        // 이분 탐색
        long min = 1;
        max++;

        while (min < max) {

            long mid = (min + max) / 2;
            long count = 0;
            for (int i = 0; i < n; i++) {
                count += arr[i] / mid;
            }

            // 더 크게 자르기 (같은 경우에도 더 크게 자르기) (자르는 길이의 upper bound)
            if (count >= m) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        System.out.println(max - 1);
    }
}