import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: 이차원 배열 = 3 * N = 3,000
public class Main {

    static int n;
    static int[][] cost;
    static int[][] totalMinCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][3];
        totalMinCost = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        totalMinCost[0][0] = cost[0][0];
        totalMinCost[0][1] = cost[0][1];
        totalMinCost[0][2] = cost[0][2];

        for (int i = 1; i < n; i++) {
            // i번째 집을 빨간색으로 칠하는 경우(i-1번째 집은 초록, 파랑 중 총 비용이 더 적은 색으로)
            totalMinCost[i][0] = Math.min(totalMinCost[i - 1][1], totalMinCost[i - 1][2]) + cost[i][0];
            // i번째 집을 초록색으로 칠하는 경우(i-1번째 집은 빨강, 파랑 중 총 비용이 더 적은 색으로)
            totalMinCost[i][1] = Math.min(totalMinCost[i - 1][0], totalMinCost[i - 1][2]) + cost[i][1];
            // i번째 집을 파란색으로 칠하는 경우(i-1번째 집은 빨강, 초록 중 총 비용이 더 적은 색으로)
            totalMinCost[i][2] = Math.min(totalMinCost[i - 1][0], totalMinCost[i - 1][1]) + cost[i][2];
        }

        System.out.println(Math.min(totalMinCost[n - 1][0], Math.min(totalMinCost[n - 1][1], totalMinCost[n - 1][2])));
    }
}