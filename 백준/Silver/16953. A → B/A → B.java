import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int a, b;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        dfs(a, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer + 1);
        }
    }

    private static void dfs(long num, int cnt) {
        if (num == b) {
            answer = Math.min(answer, cnt);
            return;
        }
        if (num > b) return;

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                dfs(num * 2, cnt + 1);
            } else {
                dfs(num * 10 + 1, cnt + 1);
            }
        }
    }
}