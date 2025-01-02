import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int[][] target = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = arr[j] - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                target[i][j] = arr[j] - '0';
            }
        }

        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                if (map[i][j] != target[i][j]) {
                    // 뒤집기
                    reverse(i, j);
                    answer++;
                }
            }
        }

        boolean isSame = true;
        outer: for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != target[i][j]) {
                    isSame = false;
                    break outer;
                }
            }
        }

        if (isSame) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }

    private static void reverse(int y, int x) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[y + i][x + j] == 0) {
                    map[y + i][x + j] = 1;
                } else {
                    map[y + i][x + j] = 0;
                }
            }
        }
    }
}
