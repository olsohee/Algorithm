import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int d;
    static int k;
    static int c;
    static int[] arr;
    static int[] eat;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 그릇 개수
        d = Integer.parseInt(st.nextToken()); // 초밥 개수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        arr = new int[n];
        eat = new int[d + 1];

        int answer = 0;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 0번째 ~ k-1번째까지 먹음
        for (int i = 0; i < k; i++) {
            eat[arr[i]]++;
        }
        // 먹은 초밥의 종류 개수 반영
        for (int i = 1; i < eat.length; i++) {
            if (eat[i] != 0) cnt++;
        }

        int start = 0;
        int end = k - 1;

        while (true) {
            // 1. answer 반영
            // arr[c]를 이미 먹었으면, 추가 초밥 X
            // arr[c]를 먹지 않았으면, 추가 초밥 O
            if (eat[c] != 0) {
                answer = Math.max(answer, cnt);
            } else {
                answer = Math.max(answer, cnt + 1);
            }

            // 2. start, end 한 칸씩 오른쪽으로 이동

            // arr[start] 초밥 빼기
            eat[arr[start]]--;
            if (eat[arr[start]] == 0) cnt--;
            // start 오른쪽 이동
            if (start == n - 1) {
                break;
            } else {
                start++;
            }

            // end 오른쪽 이동
            if (end == n - 1) {
                end = 0;
            } else {
                end++;
            }
            // arr[end] 초밥 더하기
            eat[arr[end]]++;
            if (eat[arr[end]] == 1) cnt++;
        }

        System.out.println(answer);
    }
}
