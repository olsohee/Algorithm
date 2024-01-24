import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N^3) = 1,000,000
public class Main {

    static int n;
    static int m;
    static long[][] dist;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new long[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i < n + 1; i++) {
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = Math.min(dist[start][end], cost);
        }

        // 플로이드
        for (int i = 1; i <= n; i++) {
            // i를 거쳐서 가는 경우 계산
            for (int j = 1; j <= n; j++) {
                if (j == i) continue;
                for (int k = 1; k <= n; k++) {
                    if (k == i || k == j) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] >= Integer.MAX_VALUE) {
                    sb.append("0 ");
                } else {
                    sb.append(dist[i][j] + " ");
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
