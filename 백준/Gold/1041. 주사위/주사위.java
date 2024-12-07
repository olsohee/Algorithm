import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
       long n = Integer.parseInt(br.readLine());

       long[] nums = new long[6];
       st = new StringTokenizer(br.readLine());
       for (int i = 0; i < 6; i++) {
           nums[i] = Integer.parseInt(st.nextToken());
       }

        long answer = 0;

       if (n == 1) {
           Arrays.sort(nums);
           for (int i = 0; i < 5; i++) {
               answer += nums[i];
           }
           System.out.println(answer);
           return;
       }
       // 1개 면
       long cnt = 5 * n * n  - 16 * n + 12;
       long min = Long.MAX_VALUE;
       for (int i = 0; i < 6; i++) {
           min = Math.min(nums[i], min);
       }
       answer += min * cnt;

       // 2개 면
        cnt = 8 * n - 12;
        min = Long.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (i + j != 5) {
                    min = Math.min(min, nums[i] + nums[j]);
                }
            }
        }
        answer += min * cnt;

        // 3개 면
        cnt = 4;
        min = Math.min(nums[0], nums[5]) + Math.min(nums[1], nums[4]) + Math.min(nums[2], nums[3]);
        answer += min * cnt;

        System.out.println(answer);
    }
}