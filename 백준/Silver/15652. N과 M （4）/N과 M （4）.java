import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도: O(N^2) = 64
public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dfs(0, new int[m], 1);
        System.out.println(sb);
    }

    private static void dfs(int cnt, int[] arr, int startNum) {
        if (cnt == m) {
            for (int i : arr) {
                sb.append(i + " ");
            }
            sb.append('\n');
            return;
        }

        for (int i = startNum; i <= n; i++) {
            int[] cloneArr = arr.clone();
            cloneArr[cnt] = i;
            dfs(cnt + 1, cloneArr, i);
        }
    }
}