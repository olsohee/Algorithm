import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N)
public class Main {

    static int n;
    static int[] arr;
    static long answer = Long.MAX_VALUE;
    static int[] answerNumberArr = new int[2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (answer > Math.abs(sum)) {
                answer = Math.abs(sum);
                answerNumberArr[0] = arr[left];
                answerNumberArr[1] = arr[right];
            }

            if (sum >= 0) {
                right--;
            } else {
                left++;
            }
        }

        for (int i : answerNumberArr) {
            System.out.print(i + " ");
        }
    }
}