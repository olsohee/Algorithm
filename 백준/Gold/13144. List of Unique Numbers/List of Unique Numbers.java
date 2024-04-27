import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] numbers = new int[100001];
        long answer = 0;
        int start = 0;
        int end = 0;
        while (start < n) {
            while (end < n && numbers[arr[end]] == 0) {
                numbers[arr[end]]++;
                end++;
            }

            answer += end - start;
            numbers[arr[start]]--;
            start++;
        }

        System.out.println(answer);
    }
}
