import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(V + E) = 100,000 + 200,000
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] result = new int[100000];
    static int n;
    static int t;
    static int g;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        // bfs
        if (!bfs()) {
            System.out.println("ANG");
        } else {
            System.out.println(result[g] - 1);
        }
    }

    private static boolean bfs() {
        Queue<Integer> que = new LinkedList<>();
        result[n] = 1;
        que.add(n);
        while (!que.isEmpty()) {
            Integer num = que.poll();

            // 성공
            if (num == g) {
                if (result[g] - 1 <= t) {
                    return true;
                } else {
                    return false;
                }
            }

            if (result[num] - 1 >= t) {
                continue;
            }

            // 버튼 A
            if (num + 1 <= 99999 && result[num + 1] == 0) {
                result[num + 1] = result[num] + 1;
                que.add(num + 1);
            }

            // 버튼 B
            int pushNum = pushButtonB(num);
            if (pushNum != -1 && pushNum >= 0 && pushNum <= 99999 && result[pushNum] == 0) {
                result[pushNum] = result[num] + 1;
                que.add(pushNum);
            }
        }

        return false;
    }

    private static int pushButtonB(Integer num) {
        num *= 2;
        if (num > 99999) {
            return -1;
        }

        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != 0) {
                str = str.substring(0, i) + (Character.getNumericValue(str.charAt(i)) - 1) + str.substring(i + 1);
                return Integer.parseInt(str);
            }
        }
        return -1;
    }
}