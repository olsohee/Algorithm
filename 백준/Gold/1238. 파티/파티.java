import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: ElogV = 10,000 * log1,000
public class Main {

    static int n, m, x;
    static List<List<Node>> list = new ArrayList();
    static List<List<Node>> reverseList = new ArrayList();
    static int[] dist;
    static int[] reverseDist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        reverseDist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(reverseDist, Integer.MAX_VALUE);

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        // 그래프 초기화
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, cost));
            reverseList.get(end).add(new Node(start, cost));
        }

        dijkstra(x, list, dist);
        dijkstra(x, reverseList, reverseDist);

        int maxSum = -1;
        for (int i = 1; i <= n; i++) {
            maxSum = Math.max(maxSum, dist[i] + reverseDist[i]);
        }
        System.out.println(maxSum);
    }

    private static void dijkstra(int start, List<List<Node>> list, int[] dist) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        dist[start] = 0;
        que.add(new Node(start, 0));

        while (!que.isEmpty()) {
            int now = que.poll().end;
            if (visited[now]) {
                continue;
            }
            visited[now] = true;
            for (Node node : list.get(now)) {
                if (dist[node.end] > dist[now] + node.cost) {
                    dist[node.end] = dist[now] + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }


    static class Node implements Comparable<Node> {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
