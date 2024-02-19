import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int x;
    static List<List<Node>> list = new ArrayList<>();
    static List<List<Node>> reversList = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            reversList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, cost));
            reversList.get(end).add(new Node(start, cost));
        }

        // 다익스트라
        int[] dist = dijkstra(x, list); // x -> i
        int[] reverseDist = dijkstra(x, reversList); // i -> x

        // 결과 계산
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, dist[i] + reverseDist[i]);
        }

        System.out.println(answer);
    }

    private static int[] dijkstra(int start, List<List<Node>> list) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Node> que = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        que.add(new Node(start, 0)); // start까지의 비용 = 0
        dist[start] = 0;

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

        return dist;
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