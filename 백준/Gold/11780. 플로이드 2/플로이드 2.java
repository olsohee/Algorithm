import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N^3) = 1,000,000
public class Main {

    static int n;
    static int m;
    static int[][] dist;
    static List<Integer>[][] route;
    static final int INF = 10000001;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n + 1][n + 1];
        route = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    dist[i][j] = INF;
                }
                route[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = Math.min(dist[start][end], cost);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                for (int k = 1; k <= n; k++) {
                    if (i == k || j == k) continue;
                    // 갱신되는 경우
                    if (dist[j][k] >= dist[j][i] + dist[i][k]) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                        findRoute(j, k, i); // j에서 k로 가는데 i를 경유
                    }
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] >= INF) {
                    sb.append(0 + " ");
                } else {
                    sb.append(dist[i][j] + " ");
                }
            }
            sb.append('\n');
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || dist[i][j] >= INF) {
                    sb.append(0).append('\n');
                    continue;
                }
                sb.append(route[i][j].size() + 2 + " " + i + " ");
                for (Integer num : route[i][j]) {
                    sb.append(num + " ");
                }
                sb.append(j).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static void findRoute(int start, int end, int mid) {
        route[start][end].clear();
        for (Integer i : route[start][mid]) {
            route[start][end].add(i);
        }
        route[start][end].add(mid);
        for (Integer i : route[mid][end]) {
            route[start][end].add(i);
        }
    }
}