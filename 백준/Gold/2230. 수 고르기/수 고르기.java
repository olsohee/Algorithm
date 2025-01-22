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
        int m = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        int right = 0;
        int left = 0;
        int minDiff = Integer.MAX_VALUE;
        while (right < n && left <= right) {
            int diff = nums[right] - nums[left];

            if (diff >= m) {
                minDiff = Math.min(minDiff, diff);
            }

            if (diff <= m) {
                right++;
            } else {
                left++;
            }
        }

        System.out.println(minDiff);
    }
}
