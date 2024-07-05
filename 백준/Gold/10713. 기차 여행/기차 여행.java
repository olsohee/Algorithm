import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 도시의 수
    static int m; // 여행 기간

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] route = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        int[][] price = new int[n + 1][3];
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            price[i][0] = Integer.parseInt(st.nextToken()); // 티켓
            price[i][1] = Integer.parseInt(st.nextToken()); // ic 카드
            price[i][2] = Integer.parseInt(st.nextToken()); // ic 카드 구매
        }

        int[] p = new int[n + 1]; // 누적합을 위한 배열

        // m개의 경로를 돌면서 p 배열 채우기
        for (int i = 0; i < m - 1; i++) {
            int start = route[i];
            int end = route[i + 1];

            if (start < end) {
                p[start]++;
                p[end]--;
            } else {
                p[end]++;
                p[start]--;
            }
        }

        // 각 철도의 탑승 금액 구하기
        int sum = 0;
        int trainCnt = 0;
        for (int i = 1; i <= n; i++) {
            trainCnt += p[i];
            int ticket = price[i][0] * trainCnt;
            int card = price[i][2] + price[i][1] * trainCnt;
            sum += Math.min(ticket, card);
        }

        System.out.println(sum);

    }
}
