import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long minDiff = Long.MAX_VALUE;
        List<Integer> answer = new ArrayList<>();

        outer: for (int i = 0; i < n - 2; i++) {
            // 투포인터로 sum이 0과 가장 가까운 조합 찾기
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                long sum = (long)(arr[i] + arr[left]) + arr[right];
                if (minDiff > Math.abs(sum)) {
                    minDiff = Math.abs(sum);
                    answer = List.of(arr[i], arr[left], arr[right]);
                }

                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
                if (sum == 0) {
                    break outer;
                }
            }
        }

        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }
}
