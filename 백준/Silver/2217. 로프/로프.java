
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] lopes = new int[n];
        int[] answers = new int[n];

        for(int i = 0; i < n; i++) {
            lopes[i] = Integer.parseInt(br.readLine());
        }

        // 로프를 무게를 기준으로 오름차순 정렬
        Arrays.sort(lopes);
        for(int i = 0; i < n; i++) {
            answers[i] = lopes[i] * (n - i);
            answer = Math.max(answer, answers[i]);
        }

        System.out.println(answer);
    }
}
