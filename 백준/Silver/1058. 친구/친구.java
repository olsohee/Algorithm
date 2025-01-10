import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        int answer = 0;

        // BFS
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            Queue<Node> que = new LinkedList<>();
            que.add(new Node(i, 0));
            visited[i] = true;
            int friendCnt = 0;

            while (!que.isEmpty()) {
                Node now = que.poll();
                friendCnt++;

                if (now.cnt < 2) {
                    for (int j = 0; j < n; j++) {
                        if (map[now.node][j] == 'Y' && !visited[j]) {
                            visited[j] = true;
                            que.add(new Node(j, now.cnt + 1));
                        }
                    }
                }
            }

            answer = Math.max(answer, friendCnt - 1); // -1을 하는 이유 = 자기 자신을 poll 한 경우는 빼기
        }

        System.out.println(answer);
    }

    static class Node {
        int node;
        int cnt;

        public Node(int node, int cnt) {
            this.node = node;
            this.cnt = cnt;
        }
    }
}