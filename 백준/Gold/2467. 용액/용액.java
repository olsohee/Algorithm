
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N * logN)
public class Main {

    static int n;
    static int[] arr;
    static long answer = Long.MAX_VALUE;
    static int[] answerArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        answerArr = new int[2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            calculate(arr[i], i);
        }

//        System.out.println("answer = " + answer);
        Arrays.sort(answerArr);
        for (int i : answerArr) {
            System.out.print(i + " ");
        }
    }

    private static void calculate(int targetNum, int targetIdx) {
        int minIdx = targetIdx + 1;
        int maxIdx = n - 1;
        int midIdx = 0;

        long result = Long.MAX_VALUE;
        int[] resultArr = new int[2];
        resultArr[0] = targetNum;

        while (minIdx <= maxIdx) {
            midIdx = (minIdx + maxIdx) / 2;
            long sum = targetNum + arr[midIdx];
            if (result > Math.abs(sum - 0)) {
                result = Math.abs(sum - 0);
                resultArr[1] = arr[midIdx];
            }
            if (sum <= 0) {
                minIdx = midIdx + 1;
            } else {
                maxIdx = midIdx - 1;
            }
        }

        if (answer > result) {
            answer = result;
            answerArr[0] = resultArr[0];
            answerArr[1] = resultArr[1];
        }
    }
}