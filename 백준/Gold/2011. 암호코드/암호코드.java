import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        final int mod = 1000000;
        String code = br.readLine();
        int[] dp = new int[code.length() + 1];

        dp[0] = 1;
        if (code.charAt(0) - '0' == 0) {
            System.out.println(0);
            return;
        }
        dp[1] = 1;

        // dp[i]의 값 채우기
//        for (int i = 1; i <= code.length(); i++) {
//            code.charAt(i )
//        }

        for (int i = 1; i < code.length(); i++) {
            int num = code.charAt(i) - '0';
            if (num == 0) {
                int frontNum = code.charAt(i - 1) - '0';
                if (frontNum <= 0 || frontNum >= 3) {
                    System.out.println(0);
                    return;
                } else {
                    dp[i + 1] = dp[i - 1] % mod;
                }
            } else {
                // 이어지는 숫자의 앞이 0인 경우
                int prev = code.charAt(i - 1) - '0';
                if (prev == 0) {
                    dp[i + 1] = dp[i] % mod;
//                    System.out.println(i + 1 + "인덱스에 들어가는 값 = " + dp[i]);
                    continue;
                }
                int connectedNum = prev * 10 + code.charAt(i) - '0';
                if (connectedNum >= 10 && connectedNum <= 26) {
                    dp[i + 1] = (dp[i] + dp[i - 1]) % mod;;
                } else {
                    dp[i + 1] = dp[i] % mod;;
                }
            }
        }

        // 이어지는 숫자가 1~26 O -> dp[i] = dp[i - 1] + dp[i - 2]
        // 이어지는 숫자가 1~26 X -> dp[i] = dp[i - 1]


        System.out.println(dp[code.length()]);
    }
}
