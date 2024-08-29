import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<List<Integer>> list = new ArrayList<>();
    static int n;
//    static boolean[][] map;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

//        map = new boolean[n][n];
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list.get(n1).add(n2);
            list.get(n2).add(n1);
//            map[n1][n2] = map[n2][n1] = true;
        }

        // dfs
        for (int i = 0; i < n; i++) {
            if (answer == 1) {
                break;
            }
            visited[i] = true;
            dfs(1, i);
            visited[i] = false;
        }

        System.out.println(answer);
    }

    private static void dfs(int cnt, int start) {
//        System.out.println("start = " + start);
//        System.out.println("cnt = " + cnt);
//        System.out.println();

        if (cnt == 5) {
            answer = 1;
            return;
        }

        for (int num : list.get(start)) {
            if (!visited[num]) {
                visited[num] = true;
                dfs(cnt + 1, num);
                visited[num] = false;
            }
        }
//        for (int i = 0; i < n; i++) {
//            if (map[start][i] && !visited[i]) {
//                visited[i] = true;
//                dfs(cnt + 1, i);
//                visited[i] = false;
//            }
//        }
    }
}
