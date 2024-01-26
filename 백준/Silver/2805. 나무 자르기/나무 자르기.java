
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 = O(N * log나무높이) = 1,000,000 * log1,000,000,000
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] arr;
    static int m;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        m = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, arr[i]);
        }

        // 이분 탐색
        while (start < end) { // start와 end가 같아질 때까지
            int mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] > mid) {
                    sum += (arr[i] - mid);
                }
            }
            // 자르는 높이 낮추기
            if (sum < m) {
                end = mid;
            }
            // 자르는 높이 높히기
            if (sum >= m) { // 같더라도 높히기. 즉, 자르는 높이의 Upper Bound 구하기
                start = mid + 1;
            }
        }
        System.out.println(start - 1);
    }
}
