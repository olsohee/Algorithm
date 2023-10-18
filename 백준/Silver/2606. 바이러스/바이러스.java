import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[] visited;
    static int N;
    static int M;
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            map[n1][n2] = map[n2][n1] = 1;
        }

        // 1번 노드에서 dfs 시작
        dfs(1);

        System.out.println(answer - 1);
    }

    private static void dfs(int idx) {

        answer++;
        visited[idx] = true;

        for(int i = 1; i < N + 1; i++) {
            if(map[idx][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}