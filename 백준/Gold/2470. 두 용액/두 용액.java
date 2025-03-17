import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 정렬
        Arrays.sort(nums);

        // 2. 투포인터
        int minDiff = Integer.MAX_VALUE;
        int start = 0;
        int end = n - 1;
        int[] answer = new int[2];

        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == 0) {
                answer[0] = nums[start];
                answer[1] = nums[end];
                break;
            }

            if (minDiff > Math.abs(sum)) {
                minDiff = Math.abs(sum);
                answer[0] = nums[start];
                answer[1] = nums[end];
            }

            if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }

        // 출력: 오름차순으로
        System.out.print(answer[0] + " " + answer[1]);
    }
}
