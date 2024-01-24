import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N^3) = 1,000,000
public class Main {

    static int n;
    static int m;
    static int r;
    static int[][] dist;
    static int[] item;
    static final int INF = 1000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    dist[i][j] = INF;
                }
            }
        }

        item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = cost;
            dist[end][start] = cost;
        }

        // 플로이드
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                for (int k = 1; k <= n; k++) {
                    if (i == k || j == k) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        int answer = 0;
        // 거리 그래프를 탐색하며 각 위치에서 m 반경 내에서 얻을 수 있는 아이템 수 구하기
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    sum += item[j];
                }
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}