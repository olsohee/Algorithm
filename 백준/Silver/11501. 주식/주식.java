
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            long answer = 0;
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int max = arr[n - 1];
            for(int j = n - 2; j >= 0; j--) {
                if (arr[j] <= max) {
                    answer += max - arr[j];
                } else {
                    max = arr[j];
                }
            }
            System.out.println(answer);
        }
    }
}
