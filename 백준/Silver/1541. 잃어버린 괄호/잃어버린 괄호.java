
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] split = input.split("\\-");

        // split[0] 저장
        String[] firstSum = split[0].split("\\+");
        for (int i = 0; i < firstSum.length; i++) {
            answer += Integer.parseInt(firstSum[i]);
        }

        // split[1] 부터 빼기
        for (int i = 1; i < split.length; i++) {
            int totalSum = 0;
            String[] sum = split[i].split("\\+");
            for (int j = 0; j < sum.length; j++) {
                totalSum += Integer.parseInt(sum[j]);
            }
            answer -= totalSum;
        }

        System.out.println(answer);
    }
}
