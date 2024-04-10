import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 그릇 개수
        int d = Integer.parseInt(st.nextToken()); // 초밥 개수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        int[] arr = new int[n];
        int[] eat = new int[d + 1];

        int answer = 0;
        int cnt = 0;

        // 0. 초밥 초기화
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 1. 0번째 ~ k-1번째까지 먹고, 먹은 초밥 종류 개수 반영
        for (int i = 0; i < k; i++) {
            eat[arr[i]]++;
        }
        for (int i = 1; i < eat.length; i++) {
            if (eat[i] != 0) cnt++;
        }

        // 2. 슬라이딩 윈도우 시작
        int start = 0;
        int end = k - 1;

        while (true) {
            // 2 - 1. answer 반영
            if (eat[c] != 0) {
                answer = Math.max(answer, cnt);
            } else {
                answer = Math.max(answer, cnt + 1);
            }

            // 2 - 2. start, end 한 칸씩 오른쪽으로 이동

            // 만약 start가 n - 1이라면, 오른쪽으로 이동했을 때 원점이므로 끝내기
            if (start == n - 1) {
                break;
            }

            // start 이동
            eat[arr[start]]--;
            if (eat[arr[start]] == 0) cnt--;
            start++;

            // end 이동
            end = (end == n - 1) ? 0 : end + 1;
            eat[arr[end]]++;
            if (eat[arr[end]] == 1) cnt++;
        }

        System.out.println(answer);
    }
}