
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        // 뒤에서부터 접근
        for (int i = n - 2; i >= 0; i--) {
            // i번째 점수가 i + 1번째 점수보다 같거나 크면 줄여야 함
            if (numbers[i] >= numbers[i + 1]) {
                answer += numbers[i] - (numbers[i + 1] - 1);
                numbers[i] = numbers[i + 1] - 1;
            }
        }

        System.out.println(answer);
    }
}
