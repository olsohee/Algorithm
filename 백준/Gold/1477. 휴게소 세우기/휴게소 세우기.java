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
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] locations = new int[n];
        for (int i = 0; i < n; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(locations);

        int start = 1;
        int end = l;
        while (start <= end) {
            int mid = (start + end) / 2; // mid = 휴게소의 최대 간격
            int cnt = 0; // 새로 지은 휴게소 개수
            int beforeLocation = 0; // 이전 휴게소 위치

            for (int i = 0; i < n; i++) {
                while (locations[i] - beforeLocation > mid) {
                    cnt++;
                    beforeLocation += mid;
                }
                beforeLocation = locations[i];
            }
            while (l - beforeLocation > mid) {
                cnt++;
                beforeLocation += mid;
            }

            if (cnt <= m) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }
}
