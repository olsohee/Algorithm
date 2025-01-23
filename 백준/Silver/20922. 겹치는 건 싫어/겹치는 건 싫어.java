import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int[] cnt = new int[100001];

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int maxLen = 1;
        int len = 1;
        cnt[arr[0]]++;

        while (right + 1 < n) {
            int next = arr[right + 1];
            if (cnt[next] + 1 <= k) {
                cnt[next]++;
                right++;
                len++;
                maxLen = Math.max(maxLen, len);
            } else {
                cnt[arr[left]]--;
                left++;
                len--;
            }
        }

        System.out.println(maxLen);
    }
}
