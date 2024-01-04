
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 시간복잡도: O(N) = 50
public class Main {

    static int n;
    static List<Integer> plus = new ArrayList<>();
    static List<Integer> minus = new ArrayList<>();
    static int oneCount = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= 0) {
                minus.add(num);
            } else if (num == 1) {
                oneCount++;
            } else {
                plus.add(num);
            }
        }

        // 음수 오름차순
        Collections.sort(minus);
        // 1. 절댓값 큰 음수끼리 묶기
        // 2. 2개로 묶이지 않은 하나의 수는 음수 또는 0, 이 수는 묶지 말고 더하기
        for (int i = 0; i < minus.size(); i += 2) {
            if (i == minus.size() - 1) {
                answer += minus.get(i);
                break;
            }
            answer += minus.get(i) * minus.get(i + 1);
        }

        // 양수 내림차순: 절댓값 큰 양수끼리 묶기
        Collections.sort(plus, Collections.reverseOrder());
        for (int i = 0; i < plus.size(); i += 2) {
            if (i == plus.size() - 1) {
                answer += plus.get(i);
                break;
            }
            answer += plus.get(i) * plus.get(i + 1);
        }

        // 1인 경우에는 묶지 말고 더하기
        answer += oneCount;

        System.out.println(answer);
    }
}
