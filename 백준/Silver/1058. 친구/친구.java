import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (arr[j] == 'Y') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE) {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }

        // n명 각각의 2-친구 수 구하기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int friendCnt = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (map[i][j] == 1 || map[i][j] == 2) {
                    friendCnt++;
                }
            }
            answer = Math.max(answer, friendCnt);
        }

        System.out.println(answer);
    }
}
