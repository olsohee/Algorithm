
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

        // 음수 + 0 / 양수 각 배열로 나누기
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num <= 0) {
                minusNumbers.add(num);
            } else {
                plusNumbers.add(num);
            }
        }

        // 정렬 (음수: 오름차순, 양수: 내림차순)
        Collections.sort(minusNumbers);
        Collections.sort(plusNumbers, Collections.reverseOrder());

        int answer = 0;
        for (int i = 0; i < minusNumbers.size(); i += 2) {
            if(i == minusNumbers.size() - 1) {
                answer += minusNumbers.get(i);
                break;
            }
            answer += minusNumbers.get(i) * minusNumbers.get(i + 1);
        }

        for (int i = 0; i < plusNumbers.size(); i += 2) {
//            System.out.println("i = " + i);
            if(i == plusNumbers.size() - 1) {
//                System.out.println("==");
                answer += plusNumbers.get(i);
//                System.out.println(plusNumbers.get(i));
                break;
            }

            if(plusNumbers.get(i) == 1 || plusNumbers.get(i + 1) == 1) {
                answer += plusNumbers.get(i) + plusNumbers.get(i + 1);
//                System.out.println("!!");
//                System.out.println(plusNumbers.get(i) + plusNumbers.get(i + 1));
                continue;
            }

            answer += plusNumbers.get(i) * plusNumbers.get(i + 1);
        }

        System.out.println(answer);
    }
}
