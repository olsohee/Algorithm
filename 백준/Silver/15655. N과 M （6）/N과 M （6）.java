
import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] savedNum;
    static int[] outputNum;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        savedNum = new int[n];
        visited = new boolean[n];
        outputNum = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            savedNum[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(savedNum);
        func(0, 0);
    }

    public static void func(int k, int startIdx) {

        if(k == m) {
            for(int i = 0; i < m; i++) {
                System.out.print(outputNum[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = startIdx; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                outputNum[k] = savedNum[i];
                func(k + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
