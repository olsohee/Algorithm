import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N ^ 3) = 8,000,000
public class Main {

    static int n;
    static int m;
    static int k;
    static int[][] dist;
    static int[] house;
    static final int INF = 39800000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    dist[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = cost;
        }

        k = Integer.parseInt(br.readLine());
        house = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            house[i] = Integer.parseInt(st.nextToken());
        }

        // 플로이드
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if (j == k || j == i) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 왕복시간의 최대 값의 최소를 찾기
        int minDist = Integer.MAX_VALUE;
        List<Integer> answer = new ArrayList<>();
        // 도착지가 i인 경우
        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 0; j < k; j++) {
                int house = Main.house[j];
                max = Math.max(max, dist[house][i] + dist[i][house]);
            }
            if (minDist > max) {
                minDist = max;
                answer.clear();
                answer.add(i);
            } else if (minDist == max) {
                answer.add(i);
            }
        }

        // 오름차순 후 출력
        Collections.sort(answer);
        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }
}