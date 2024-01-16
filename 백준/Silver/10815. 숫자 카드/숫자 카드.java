import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(logN + M) = log100,000 + 2^31
public class Main {

    static int n;
    static int m;
    static int[] cards;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        // 정렬
        Arrays.sort(cards);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(target) + " ");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int target) {
        int startIdx = 0;
        int endIdx = n - 1;
        int midIdx;

        while (startIdx <= endIdx) {
            midIdx = (startIdx + endIdx) / 2;
            if (cards[midIdx] == target) {
                return 1;
            }

            if (cards[midIdx] > target) {
                endIdx = midIdx - 1;
            } else {
                startIdx = midIdx + 1;
            }
        }

        return 0;
    }
}