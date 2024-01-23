import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: ElogV = 200,000 * long800
public class Main {

    static int n;
    static int e;
    static int v1;
    static int v2;
    static List<List<Node>> list = new ArrayList<>();
    static int dist[];
    static boolean visited[];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, cost));
            list.get(end).add(new Node(start, cost));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 1-> v1 -> v2 -> n

        int result1 = dijkstra(1, v1);
        int result2 = dijkstra(v1, v2);
        int result3 = dijkstra(v2, n);
        if (result1 != -1 && result2 != -1 && result3 != -1) {
            answer = Math.min(answer, result1 + result2 + result3);
        }

        // 1 -> v2 -> v1 -> n
        result1 = dijkstra(1, v2);
        result2 = dijkstra(v2, v1);
        result3 = dijkstra(v1, n);
        if (result1 != -1 && result2 != -1 && result3 != -1) {
            answer = Math.min(answer, result1 + result2 + result3);
        }

        int printNum = (answer == Integer.MAX_VALUE) ? -1 : answer;
        System.out.println(printNum);
    }

    private static int dijkstra(int start, int destination) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        PriorityQueue<Node> que = new PriorityQueue<>();

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

        if (dist[destination] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dist[destination];
        }
    }

    public static class Node implements Comparable<Node> {
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