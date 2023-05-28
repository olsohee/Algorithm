import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        func(0, 1);
    }

    public static void func(int k, int start) {

        if(k == m) {
            for(int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = start; i <= n; i++) {
            arr[k] = i;
            func(k + 1, i);
        }
    }
}
