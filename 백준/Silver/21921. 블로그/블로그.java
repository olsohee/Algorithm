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
        int x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        boolean isSad = true;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] != 0) isSad = false;
        }

        if (isSad) {
            System.out.println("SAD");
            return;
        }

        int sum = 0;
        for (int i = 0; i < x; i++) {
            sum += arr[i];
        }

        int start = 0;
        int end = x - 1;
        int answer = 0;
        int cnt = 0;

        while (true) {
            if (sum >= answer) {
                if (sum == answer) {
                    cnt++;
                } else {
                    cnt = 1;
                    answer = sum;
                }
            }

            if (end + 1 == n) {
                break;
            }
            sum -= arr[start++];
            sum += arr[++end];
        }

        System.out.println(answer);
        System.out.println(cnt);
    }
}
