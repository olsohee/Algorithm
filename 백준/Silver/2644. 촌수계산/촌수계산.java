import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int from;
    static int to;
    static int[][] map;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        visited = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine()); // 부모-자식 관계 수

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[n1][n2] = 1;
            map[n2][n1] = 1;
        }

        bfs(from, to);

        if(visited[to] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(visited[to]);
        }
    }

    static void bfs(int start, int end) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        while(!que.isEmpty()) {
            int q = que.poll();
            if(q == end) {
                break;
            }
            for(int i = 1; i <= n; i++) {
                if(visited[i] == 0 && map[q][i] == 1) {
                    visited[i] = visited[q] + 1;
                    que.add(i);
                }
            }
        }
    }
}