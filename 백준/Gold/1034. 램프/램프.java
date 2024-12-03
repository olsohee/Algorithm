import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static int k;
    static int answer;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int lightOutCnt = 0;
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    lightOutCnt++;
                }
            }

            if (lightOutCnt <= k && (k % 2 == lightOutCnt % 2)) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (isSame(i, j)) {
                        cnt++;
                    }
                }
                answer = Math.max(answer, cnt);
            }
        }

        System.out.println(answer);
    }

    private static boolean isSame(int i, int j) {
        for (int idx = 0; idx < m; idx++) {
            if (map[i][idx] != map[j][idx]) {
                return false;
            }
        }
        return true;
    }
}