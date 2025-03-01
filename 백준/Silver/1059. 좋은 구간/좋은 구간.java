import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int s;
    static int[] arr;
    static int n;
    static int start;
    static int end;

    public static void main(String[] args) throws IOException {
        s = Integer.parseInt(br.readLine());
        arr = new int[s];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        n = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        if (Arrays.stream(arr)
                .anyMatch(num -> num == n)) {
            System.out.println(0);
        } 
        else {
            System.out.println(result());
        }
    }

    private static int result() {
        for (int i = 0; i < s - 1; i++) {
            if (arr[i] < n && arr[i + 1] > n) {
                start = arr[i];
                end = arr[i + 1];
                break;
            }
        }
        if (start == 0 && end == 0) {
            start = 0;
            end = arr[0];
        }

        for (int i = 0; i < s - 1; i++) {
            if (arr[i] < n && arr[i + 1] > n) {
                if (end - start < arr[i + 1] - arr[i]) {
                    start = arr[i];
                    end = arr[i + 1];
                }
            }
        }

        int cnt = 0;

        for (int i = start + 1; i < end; i++) {
            if (i > n) {
                break;
            }
            for (int j = i + 1; j < end; j++) {
                if (j < n) {
                    continue;
                }
                cnt++;
            }
        }


        return cnt;
    }
}
