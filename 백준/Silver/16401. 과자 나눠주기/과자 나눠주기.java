import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: log1,000,000,000 * n
public class Main {

    static int m; // 조카의 수
    static int n; // 과자의 수
    static int[] snack;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        snack = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snack);
        int minLen = 1;
        int maxLen = snack[n - 1];
        int midLen = 0;
        int result = 0;
        while (minLen <= maxLen) {
            midLen = (minLen + maxLen) / 2;


            int cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += snack[i] / midLen;
            }

            if (cnt >= m) {
                minLen = midLen + 1;
                result = midLen;
            } else {
                maxLen = midLen - 1;
            }
        }

        System.out.println(result);
    }
}
