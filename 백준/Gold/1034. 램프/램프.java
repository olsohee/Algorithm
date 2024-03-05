import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int k;
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int zeroCnt = 0;
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    zeroCnt++;
                }
            }

            // 하나의 행에서 0의 개수가 k개 초과이면 켜진 행이 될 수 없음
            // k와 0의 개수가 홀/짝 맞아야 함
            if (zeroCnt <= k && zeroCnt % 2 == k % 2) {
                int sameCnt = 0;
                for (int j = 0; j < n; j++) {
                    if (isSame(map[i], map[j])) {
                        sameCnt++;
                    }
                }
                answer = Math.max(answer, sameCnt);
            }
        }

        System.out.println(answer);
    }

    private static boolean isSame(int[] arr1, int[] arr2) {
        for (int i = 0; i < m; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}