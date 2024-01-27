
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 = O(N * logN)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int k;
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[k];
        long end = 0;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, arr[i]);
        }

        // 이분 탐색
        long start = 0;
        end++;

        while (start < end) { // start와 end가 같아질 때까지
            long mid = (start + end) / 2;
            long count = 0;
            for (int i = 0; i < k; i++) {
                count += arr[i] / mid;
            }

            // 더 짧게 자르기
            if (count < n) {
                end = mid;
            } else {
                // 더 길게 자르기 (같은 경우에도 더 길게)
                start = mid + 1;
            }
        }
        System.out.println(start - 1); // upper bound를 구했으므로
    }
}
