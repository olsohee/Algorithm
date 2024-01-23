import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: ElogV = 100,000 * log1,000
public class Main {

    static int n;
    static int m;
    static List<List<Node>> list = new ArrayList<>();
    static long dist[];
    static int beforeNode[];
    static boolean visited[];
    static final long INF = 10000000000l;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        dist = new long[n + 1];
        beforeNode = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dist, INF);

        m = Integer.parseInt(br.readLine());
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

        // 다익스트라
        dijkstra(startingPoint, destination);

        List<Integer> answerList = new ArrayList<>();
        int i = destination;
        while (i != 0) {
            answerList.add(i);
            i = beforeNode[i];
        }

        sb.append(dist[destination]).append('\n');
        sb.append(answerList.size()).append('\n');
        for (int j = answerList.size() - 1; j >= 0; j--) {
            sb.append(answerList.get(j) + " ");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int startingPoint, int destination) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        dist[startingPoint] = 0;
        beforeNode[startingPoint] = 0;
        que.add(new Node(startingPoint, 0));

        while (!que.isEmpty()) {
            int now = que.poll().end;
            if (visited[now]) continue;
            visited[now] = true;

            for (Node node : list.get(now)) {
                if (dist[node.end] > dist[now] + node.cost) {
                    dist[node.end] = dist[now] + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                    beforeNode[node.end] = now;
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        int end;
        long cost;

        public Node(int end, long cost) {
            this.end = end;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return (int)(this.cost - o.cost);
        }
    }
}
