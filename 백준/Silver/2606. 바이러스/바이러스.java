
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static boolean[][] graph;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new boolean[n+1][n+1];
        visited = new boolean[n+1];

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph[n1][n2] = graph[n2][n1] = true;
        }

        dfs(1);

        System.out.println(answer - 1);
    }

    static void dfs(int idx) {

        visited[idx] = true;
        answer++;

        for(int i = 1; i < n + 1; i++) {
            if(graph[idx][i] && !visited[i]) {
                dfs(i);
            }
        }
    }
}