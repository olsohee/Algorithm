import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int start;
    static int end;
    static List<List<Node>> list = new ArrayList<>();
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            if (i != start) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // 다익스트라
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, 0));
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (visited[now.end]) {
                continue;
            }

            visited[now.end] = true;
            for (Node node : list.get(now.end)) {
                if (dist[node.end] > now.cost + node.cost) {
                    dist[node.end] = now.cost + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }

        // 출력
        System.out.println(dist[end]);
    }

    static class Node implements Comparable<Node> {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}
