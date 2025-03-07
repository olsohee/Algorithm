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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int left = 0;
        int right = 0;
        int answer = arr[n - 1] - arr[0];

        while (right < n) {
            if (left == right) {
                right++;
                continue;
            }
            int diff = arr[right] - arr[left];
            if (diff == m) {
                answer = m;
                break;
            } else if (diff > m) {
                answer = Math.min(answer, diff);
                left++;
            } else {
                right++;
            }
        }

        System.out.println(answer);
    }
}
