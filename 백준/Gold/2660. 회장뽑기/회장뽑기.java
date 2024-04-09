import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        // 플로이드 (O(N^3) = 125,000)
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 입력
        while (true) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if (num1 == -1 && num2 == -1) {
                break;
            }
            map[num1][num2] = map[num2][num1] = 1;
        }

        // 플로이드
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    // j -> k (i경유)
                    if (map[j][i] != Integer.MAX_VALUE && map[i][k] != Integer.MAX_VALUE) {
                        map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                    }
                }
            }
        }

        int[] score = new int[n + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] > max) {
                    max = map[i][j];
                }
            }
            score[i] = max;
            min = Math.min(min, max);
        }

        int cnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == min) {
                cnt++;
                list.add(i);
            }
        }

        System.out.println(min + " " + cnt);
        for (int i : list) {
            System.out.print(i + " ");
        }

    }
}
