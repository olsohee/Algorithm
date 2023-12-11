
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(upperBound(num) - lowerBound(num) + " ");
        }
        System.out.println(sb);
    }

    private static int upperBound(int num) {

        int min = 0;
        int max = n;
        while (min < max) {
            int mid = (min + max) / 2;
            if (arr[mid] > num) {
                max = mid;
            }

            else {
                min = mid + 1;
            }
        }
        return max;
    }

    private static int lowerBound(int num) {
        int min = 0;
        int max = n;
        while (min < max) {
            int mid = (min + max) / 2;
            if (arr[mid] >= num) {
                max = mid;
            }

            else {
                min = mid + 1;
            }
        }
        return min;
    }
}
