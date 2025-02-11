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
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            map[num1][num2] = map[num2][num1] = true;
        }

        // 1번 노드부터 bfs 시작
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(1, 0));
        visited[1] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.cnt == 2) {
                continue;
            }
            for (int i = 1; i <= n; i++) {
                if (map[now.num][i] && !visited[i]) {
                    visited[i] = true;
                    que.add(new Node(i, now.cnt + 1));
                }
            }
        }

        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (visited[i]) answer++;
        }
        System.out.println(answer);
    }

    private static class Node {
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
