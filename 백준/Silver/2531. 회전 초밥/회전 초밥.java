
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

        int[] eat = new int[d + 1];

        int[] sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int start = 0;
        int end = k - 1;
        int typeCnt = 0;
        for(int i = start; i <= end; i++) {
            if (eat[sushi[i]] == 0) typeCnt++;
            eat[sushi[i]]++; // 먹은 초밥 기록
        }

        int maxTypeCnt = 0;

        while (true) {
            maxTypeCnt = Math.max(maxTypeCnt, eat[c] > 0 ? typeCnt : typeCnt + 1);
            // start++
            eat[sushi[start]]--;
            if (eat[sushi[start]] == 0) typeCnt--;
            start = (start == n - 1) ? 0 : start + 1;

            // end++
            end = (end == n - 1) ? 0 : end + 1;
            eat[sushi[end]]++;
            if (eat[sushi[end]] == 1) typeCnt++;

            if (start == 0 && end == k - 1) break;
        }

        System.out.println(maxTypeCnt);
    }
}
