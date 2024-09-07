
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long sum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (a > 0) {
                if (sum + arr[i] / 2 <= b) {
                    sum += arr[i] / 2;
                    a--;
                    end = i + 1;
                } else {
                    break;
                }
            } else {
                sum += arr[start] / 2;
                sum += arr[end] / 2;
                if (sum > b) {
                    break;
                } else {
                    start++;
                    end++;
                }
            }
        }

        System.out.println(end);
    }
}