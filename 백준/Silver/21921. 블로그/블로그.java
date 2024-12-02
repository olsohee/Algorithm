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
        
        int answer = sum;
        int cnt = 1;
        for (int i = x; i < n; i++) {
            sum += (arr[i] - arr[i - x]);
            if (sum == answer) {
                cnt++;
            } else if (sum > answer) {
                answer = sum;
                cnt = 1;
            }
        }

        System.out.println(answer);
        System.out.println(cnt);
    }
}
