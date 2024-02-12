import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N^3)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int s;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new boolean[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start][end] = true;
        }

        // 플로이드
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                for (int k = 1; k <= n; k++) {
//                    if (map[i][k] && map[k][j]) {
//                        map[i][j] = true;
//                    }
//                }
//            }
//        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }

        // 결과 출력
        s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start < 1 || start > n || end < 1 || end > n) {
                System.out.println(0);
            }
            else if (map[start][end]) {
                System.out.println(-1);
            } 
            else if (map[end][start]) {
                System.out.println(1);
            } 
            else {
                System.out.println(0);
            }
        }
    }
}