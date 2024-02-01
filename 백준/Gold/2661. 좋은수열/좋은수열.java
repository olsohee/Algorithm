
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: 
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static String result = "1"; // 제일 앞자리는 1

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        dfs(result);
    }

    private static void dfs(String result) {
        // answer이 좋은 수열인지 확인
        if (!check(result)) {
            return;
        }

        if (result.length() == n) {
            System.out.println(result); // 1 ~ 3 순서로 넣었으므로 제일 처음 값이 최소값이다.
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            String copyResult = new String(result);
            copyResult += i;
            dfs(copyResult);
        }
    }

    private static boolean check(String result) {
        for (int i = 1; i <= result.length() / 2; i++) {
            String front = result.substring(result.length() - i * 2, result.length() - i);
            String back = result.substring(result.length() - i, result.length());

            if (front.equals(back)) {
                return false;
            }
        }
        return true;
    }
}
