import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int l;
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로 줄 확인
        for (int i = 0; i < n; i++) {
            if (calculateRow(i, true)) answer++; // 행 검사
            if (calculateRow(i, false)) answer++; // 열 검사
        }

        System.out.println(answer);
    }

    // flag : 참 -> 행 검사, 거짓 -> 열 검사
    private static boolean calculateRow(int idx, boolean flag) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (flag) arr[i] = map[idx][i];
            else arr[i] = map[i][idx];
        }

        boolean[] isAdd = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            int diff = Math.abs(arr[i] - arr[i + 1]);
            // 1. 높이가 같을 때
            if (diff == 0) {
                continue;
            }

            // 2. 높이차가 1을 넘는 경우
            if (diff > 1) {
                return false;
            }

            // 3. 높이차가 1인 경우
            // 3 - 1. 다음 계단이 더 낮은 경우
            if (arr[i] - arr[i + 1] == 1) {
                for (int j = i + 1; j <= i + l; j++) {
                    // l만큼의 길이 판단 (범위 벗어나거나, 높이가 다르거나, 이미 경사로 있으면 X)
                    if (j >= n || arr[j] != arr[i + 1] || isAdd[j]) {
                        return false;
                    }
                    isAdd[j] = true;
                }
            }

            // 3 - 2. 다음 계단이 더 높은 경우
            else {
                for (int j = i; j > i - l; j--) {
                    if (j < 0 || arr[j] != arr[i] || isAdd[j]) {
                        return false;
                    }
                    isAdd[j] = true;
                }
            }
        }

        return true;
    }
}