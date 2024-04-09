import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        // 투포인터 (O(N) = 1,000,000)
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int answer = 0;
        int len = 0;
        int removeCnt = 0;

        while (end < n) {
           

            // 짝수이면 길이 늘리기
            if (arr[end] % 2 == 0) {
                end++;
            }

            // 홀수이면
            else {
                // 홀수 지울 수 있으면, 지우고 길이 늘리기
                if (removeCnt < k) {
                    removeCnt++;
                    end++;
                }
                // 지울 수 없으면
                else {
                    if (arr[start] % 2 != 0) {
                        removeCnt--;
                        start++;
                    } else {
                        start++;
                    }
                }
            }
             answer = Math.max(answer, (end - start - removeCnt));
        }

        System.out.println(answer);
    }
}
