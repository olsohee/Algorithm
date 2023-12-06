import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> minusNumbers = new ArrayList<>();
        List<Integer> plusNumbers = new ArrayList<>();

        // 음수, 0이 담긴 배열과 양수가 담긴 배열로 나누기
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= 0) {
                minusNumbers.add(num);
            } else {
                plusNumbers.add(num);
            }
        }

        // 정렬 (음수: 오름차순, 양수: 내림차순)
        Collections.sort(minusNumbers);
        Collections.sort(plusNumbers, Collections.reverseOrder());

        int answer = 0;
        // 음수 배열 계산
        for (int i = 0; i < minusNumbers.size(); i += 2) {
            if (i == minusNumbers.size() - 1) {
                answer += minusNumbers.get(i);
                break;
            }
            answer += minusNumbers.get(i) * minusNumbers.get(i + 1);
        }

        // 양수 배열 계산
        for (int i = 0; i < plusNumbers.size(); i += 2) {
            if (i == plusNumbers.size() - 1) {
                answer += plusNumbers.get(i);
                break;
            }
            // 두 값 중 하나라도 1이면 두 값을 곱하지 않고 더한다.
            if (plusNumbers.get(i) == 1 || plusNumbers.get(i + 1) == 1) {
                answer += plusNumbers.get(i) + plusNumbers.get(i + 1);
                continue;
            }
            answer += plusNumbers.get(i) * plusNumbers.get(i + 1);
        }

        System.out.println(answer);
    }
}