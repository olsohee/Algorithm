import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, getCnt(i));
        }

        System.out.println(answer);
    }

    private static int getCnt(int idx) {
        int cnt = 0;
        double maxSlope = 0;

        // 오른쪽
        for (int i = idx + 1; i <= n; i++) {
            double slope = (double)(arr[i] - arr[idx]) / (i - idx);
            if (i == idx + 1 || maxSlope < slope) {
                maxSlope = slope;
                cnt++;
            }
        }

        // 왼쪽
        maxSlope = 0;
        for (int i = idx - 1; i >= 1; i--) {
            double slope = (double)(arr[i] - arr[idx]) / (i - idx);
            if (i == idx - 1 || maxSlope > slope) {
                maxSlope = slope;
                cnt++;
            }
        }

        return cnt;
    }
}
