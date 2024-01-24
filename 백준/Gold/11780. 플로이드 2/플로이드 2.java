import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N ^ 3) = 1,000,000
public class Main {

    static int n;
    static int m;
    static long[][] dist;
    static List<Integer>[][] route;
    static final long INF = 10000000001L;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new long[n + 1][n + 1];
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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = Math.min(dist[start][end], cost);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if (j == k || j == i) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        findRoute(k, i, j);
                    }
                }
            }
        }

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
                if (dist[i][j] == 0 || dist[i][j] >= INF) {
                    sb.append(0).append('\n');
                } else {
                    sb.append(route[i][j].size() + 2 + " " + i + " ");
                    for (Integer num : route[i][j]) {
                        sb.append(num + " ");
                    }
                    sb.append(j).append('\n');
                }
            }
        }

        System.out.println(sb);
    }

    private static void findRoute(int k, int i, int j) {
        route[i][j].clear();
        for (Integer node : route[i][k]) {
            route[i][j].add(node);
        }
        route[i][j].add(k);
        for (Integer node : route[k][j]) {
            route[i][j].add(node);
        }
    }
}
