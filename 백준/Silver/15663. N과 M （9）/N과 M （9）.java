import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] num;
    static int[] outputArr;
    static LinkedHashSet<String> outputSet;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        outputArr = new int[m];
        outputSet = new LinkedHashSet<>();
        num = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        func(0);
        for (String set:outputSet) {
            System.out.println(set.toString());
        }
    }

    public static void func(int k) {

        if(k == m) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < m; i++) {
                sb.append(outputArr[i]).append(" ");
            }
            outputSet.add(sb.toString());
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                outputArr[k] = num[i];
                func(k + 1);
                visited[i] = false;
            }
        }
    }
}
