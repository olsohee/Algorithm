import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static boolean[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    map[i][j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());
            map[min][max] = true;
        }

        // 플로이드
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }

        int cnt = 0;
        outer:
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!map[i][j] && !map[j][i]) {
                    continue outer;
                }
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}