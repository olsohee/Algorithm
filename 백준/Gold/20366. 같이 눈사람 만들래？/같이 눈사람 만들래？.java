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

        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 2; j < n; j++) {
                int sum = arr[i] + arr[j];
                // i와 j 사이를 투포인터
                int start = i + 1;
                int end = j - 1;
                while (start < end) {
                    int newSum = arr[start] + arr[end];
                    if (newSum == sum) {
                        System.out.println(0);
                        return;
                    }
                    answer = Math.min(answer, Math.abs(newSum - sum));
                    if (newSum < sum) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
