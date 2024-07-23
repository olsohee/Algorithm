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

        int min = Integer.MAX_VALUE;
        List<Integer> answer = new ArrayList<>();

        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                answer = List.of(arr[start], arr[end]);
            }
            if (sum == 0) break;
            if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }
}
