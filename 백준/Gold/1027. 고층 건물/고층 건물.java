import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            int cnt = 0;

            // 오른쪽 빌딩: 최대 기울기 찾기
            double maxSlope = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                double slope = (double)(arr[j] - arr[i]) / (j - i);
                if (maxSlope < slope) {
                    cnt++;
                    maxSlope = slope;
                }
            }

            // 왼쪽 빌딩: 최저 기울기 찾기
            double minSlope = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                double slope = (double)(arr[i] - arr[j]) / (i - j);
                if (minSlope > slope) {
                    cnt++;
                    minSlope = slope;
                }
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
