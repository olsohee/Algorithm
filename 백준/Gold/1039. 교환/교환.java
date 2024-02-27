import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int answer = -1;
    static boolean[][] visited = new boolean[1000001][11];

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited[n][0] = true;
        dfs(n, 0);
        System.out.println(answer);
    }

    private static void dfs(int n, int cnt) {
        if (cnt == k) {
            answer = Math.max(n, answer);
            return;
        }

        char[] arr = String.valueOf(n).toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int next = swap(i, j, arr);
                if (next != -1 && !visited[next][cnt + 1]) {
                    visited[next][cnt + 1] = true;
                    dfs(next, cnt + 1);
                }
            }
        }
    }

    private static int swap(int i, int j, char[] arr) {
        char[] cloneArr = arr.clone();
        cloneArr[i] = arr[j];
        cloneArr[j] = arr[i];

        if (cloneArr[0] == '0') {
            return -1;
        }
        return Integer.parseInt(new String(cloneArr));
    }
}