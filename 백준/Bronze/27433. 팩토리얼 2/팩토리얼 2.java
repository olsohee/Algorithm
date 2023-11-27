
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static long answer = 1;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dfs(n);
        System.out.println(answer);
    }

    private static void dfs(int idx) {
        if(idx == 0) {
            return;
        }

        answer *= idx;
        dfs(idx - 1);
    }
}
