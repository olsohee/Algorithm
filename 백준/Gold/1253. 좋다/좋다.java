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

        Arrays.sort(nums);
        int answer = 0;

        for (int i = 0; i < n; i++) {
            // nums[i]가 좋은 수인지 확인
            int start = 0;
            int end = n - 1;
            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                }
                int sum = nums[start] + nums[end];
                if (sum == nums[i]) {
                    answer++;
//                    System.out.println(nums[i]);
                    break;
                }
                if (sum > nums[i]) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        System.out.println(answer);

    }
}
