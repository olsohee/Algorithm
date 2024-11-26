import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        int[] height = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            sum += height[i];
        }

        // 3으로 나누어 떨어져야 함
        if (sum % 3 != 0) {
            System.out.println("NO");
        } else {
            // 각 나무 / 2 (= 2물뿌리개를 사용하는 일수) >= 총 일수
            int day = sum / 3;
            int count = 0;
            for (int i = 0; i < n; i++) {
                count += height[i] / 2;
            }
            if (count < day) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
