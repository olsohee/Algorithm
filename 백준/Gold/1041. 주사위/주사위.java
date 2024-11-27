import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        long n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] nums = new int[6];
        for (int i = 0; i < 6; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            int sum = 0;
            Arrays.sort(nums);
            for (int i = 0; i < 5; i++) {
                sum += nums[i];
            }
            System.out.println(sum);
            return;
        }

        long answer = 0;

        // 3면이 보이는 경우
        long sum = Math.min(nums[0], nums[5]) + Math.min(nums[2], nums[3]) +Math.min(nums[1], nums[4]);
        answer += sum * 4;

        // 2면이 보이는 경우
        long min = Long.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if ((i + j) != 5) {
                    min = Math.min(min, nums[i] + nums[j]);
                }
            }
        }
        answer += min * (8 * n - 12);

        // 1면이 보이는 경우
        min = Long.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            min = Math.min(min, nums[i]);
        }
        answer += min * (5 * n * n  - 16 * n + 12);

        System.out.println(answer);
    }
}