import java.io.*;
import java.util.*;

public class Main {

    static int k;
    static int[] s;
    static boolean[] visited;
    static int[] result = new int[6];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            if(k == 0) {
                break;
            }

            s = new int[k];
            visited = new boolean[k];

            for(int i = 0; i < k; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            func(0, 0);
            System.out.println();
        }
    }

    public static void func(int depth, int start) {

        if(depth == 6) {
            for(int i = 0; i < 6; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = start; i < k; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[depth] = s[i];
                func(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
