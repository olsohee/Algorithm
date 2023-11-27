
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr = new int[21];
    static int n;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr[0] = 0;
        arr[1] = 1;
        dfs(2);
        System.out.println(arr[n]);
    }

    private static void dfs(int idx) {
        if(idx > n) {
            return;
        }

        arr[idx] = arr[idx - 2] + arr[idx - 1];
        dfs(idx + 1);
    }
}
