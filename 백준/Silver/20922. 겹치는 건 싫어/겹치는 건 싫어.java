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
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 1);

        while (right + 1 < n) {
            int next = arr[right + 1];
            if (map.getOrDefault(next, 0) + 1 <= k) {
                map.put(next, map.getOrDefault(next, 0) + 1);
                right++;
                len++;
                maxLen = Math.max(maxLen, len);
            } else {
                map.put(arr[left], map.getOrDefault(arr[left], 0) - 1);
                left++;
                len--;
            }
        }

        System.out.println(maxLen);
    }
}
