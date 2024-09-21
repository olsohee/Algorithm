
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        // 입력값 거꾸로 넣어주기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(end).add(new Node(start, cost));
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        Queue<Node> que = new PriorityQueue<>();
        // 면접장에서 시작
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int destination = Integer.parseInt(st.nextToken());
            que.add(new Node(destination, 0));
            dist[destination] = 0;
        }

        // 다익스트라
        boolean[] visited = new boolean[n + 1];
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (visited[now.end]) continue;
            visited[now.end] = true;

            for (Node node : list.get(now.end)) {
                if (dist[node.end] > now.cost + node.cost) {
                    dist[node.end] = now.cost + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }

        int node = 0;
        long maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (maxDist < dist[i]) {
                maxDist = dist[i];
                node = i;
            }
        }
        System.out.println(node);
        System.out.println(maxDist);
    }

    private static class Node implements Comparable<Node>{
        int end;
        long cost;

        public Node(int end, long cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}