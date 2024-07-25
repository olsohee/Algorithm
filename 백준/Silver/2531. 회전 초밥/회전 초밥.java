import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] eat = new int[d + 1];
        int typeCnt = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (i <= k - 1) {
                eat[arr[i]]++;
                if (eat[arr[i]] == 1) typeCnt++;
            }
        }

        // 슬라이딩 윈도우
        int answer = 0;
        int start = 0;
        int end = k - 1;

        while (true) {
            // 쿠폰 고려해서 답 갱신
            if (eat[c] > 0) {
                answer = Math.max(answer, typeCnt);
            } else {
                answer = Math.max(answer, typeCnt + 1);
            }

            // 이동
            if (start + 1 == n) {
                break;
            }

            eat[arr[start]]--;

            if (eat[arr[start]] == 0) typeCnt--;
            start++;
            
            if (end + 1 == n) {
                end = 0;
            } else {
                end++;
            }
            eat[arr[end]]++;
            if (eat[arr[end]] == 1) typeCnt++;
        }

        System.out.println(answer);
    }
}
