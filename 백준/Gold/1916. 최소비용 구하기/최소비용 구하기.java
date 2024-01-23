import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: ElogV = 100,000 * log1,000
public class Main {

    static int n;
    static int m;
    static List<List<Node>> list = new ArrayList<>();
    static int dist[];
    static boolean visited[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[n + 1];
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int startingPoint = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        dijkstra(startingPoint);
        System.out.println(dist[destination]);
    }

    private static void dijkstra(int startingPoint) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        dist[startingPoint] = 0;
        que.add(new Node(startingPoint, 0));

        while (!que.isEmpty()) {
            int now = que.poll().end;
            if (visited[now]) continue;
            visited[now] = true;

            for (Node node : list.get(now)) {
                if (dist[node.end] > dist[now] + node.cost) {
                    dist[node.end] = dist[now] + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        int end;
        int cost;

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