import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int d; // 오븐의 깊이
    static int n; // 피자 개수
    static int[] ovens;
    static int[] pizza;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        ovens = new int[d];
        pizza = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < d; i++) {
            ovens[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 오븐 크기를 피자가 들어가게 되는 크기로 재조정 (-> 정렬된 상태가 됨)
        for (int i = 1; i < d; i++) {
            ovens[i] = Math.min(ovens[i], ovens[i - 1]);
        }

        // 2. 피자 오븐에 넣기 (이분탐색)
        int previousIdx = d;
        for (int i = 0; i < n; i++) {
            if (previousIdx == 0 || ovens[0] < pizza[i]) { // 넣을 공간이 없거나, 안들어가는 경우
                System.out.println(0);
                return;
            }
            int start = 0;
            int end = previousIdx - 1;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (ovens[mid] >= pizza[i]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            previousIdx = end;
        }
        
        System.out.println(previousIdx + 1);
    }
}