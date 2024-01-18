
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: log1,000,000,000 * n
public class Main {

    static int n; // 나무 개수
    static int m; // 필요한 길이
    static int[] trees;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        trees = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);

        int minLen = 1;
        int maxLen = trees[n - 1];
        int midLen = 0;
        int result = 0;
        while (minLen <= maxLen) {
//            midLen = (minLen + maxLen) / 2;
            midLen = minLen + (maxLen - minLen) / 2;

            long totalGetLen = 0; // 가져가는 나무 높이
            for (int i = 0; i < n; i++) {
                if (trees[i] > midLen) {
                    totalGetLen += trees[i] - midLen;
                }
            }

            // 절단 높이 높히기
            if (totalGetLen >= m) {
                minLen = midLen + 1;
                result = midLen;
            }
            // 절단 높이 낮추기
            else {
                maxLen = midLen - 1;
            }
        }
        System.out.println(result);
    }
}