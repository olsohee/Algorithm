
import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] savedNum;
    static int[] outputNum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        savedNum = new int[n];
        outputNum = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            savedNum[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(savedNum);
        func(0);
        System.out.println(sb);
    }

    public static void func(int k) {

        if(k == m) {
            for(int i = 0; i < m; i++) {
                sb.append(outputNum[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i = 0; i < n; i++) {
            outputNum[k] = savedNum[i];
            func(k + 1);
        }
    }
}