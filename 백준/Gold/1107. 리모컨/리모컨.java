import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Set<Integer> broken = new HashSet<>();

        if (m != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                broken.add(Integer.parseInt(st.nextToken()));
            }
        }
        
        int answer = Math.abs(100 - n);

        // i로 이동한 후, i에서 n으로 +/- 버튼 누르기
        for (int i = 0; i <= 999999; i++) {
            // 하나라도 버튼 고장났으면 i 못 만듦
            String num = String.valueOf(i);
            boolean canMove = true;
            if (broken.size() != 0) {
                for (int j = 0; j < num.length(); j++) {
                    if (broken.contains(num.charAt(j) - '0')) {
                        canMove = false;
                        break;
                    }
                }
            }

            if (canMove) {
                int cnt = num.length() + Math.abs(n - i);
                answer = Math.min(answer, cnt);
            }
        }

        System.out.println(answer);
    }
}