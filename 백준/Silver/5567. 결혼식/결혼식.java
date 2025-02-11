import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            map[num1][num2] = map[num2][num1] = true;
        }

        // 1번 노드(상근이)에서 BFS 시작
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(1, 0));
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();

            // 현재 노드까지 오는데 2만큼 걸렸다면, 더이상 이동할 수 없음
            if (now.cost == 2) {
                continue;
            }

            for (int i = 1; i <= n; i++) {
                if (map[now.end][i] && !visited[i]) {
                    que.add(new Node(i, now.cost + 1));
                    visited[i] = true;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (visited[i]) cnt++;
        }
        System.out.println(cnt);
    }

    private static class Node {

        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
