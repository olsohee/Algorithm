
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: 
public class Main {

    static int v;
    static int e;
    static int start;
    static List<Node>[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        list = new ArrayList[v + 1];
        dist = new int[v + 1];

        // 초기 셋팅
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        // 값 초기화
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
        }

        // 다익스트라
        dijkstra(start);

        // 출력
        for (int i = 1; i <= v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean[] visited = new boolean[v + 1];
        que.add(new Node(start, 0)); // start에서 start까지 가는데 거리는 0
        dist[start] = 0;

        while (!que.isEmpty()) {
            Node node = que.poll();

            // 이미 해당 위치를 시작점으로 방문했으면 패스
            if (visited[node.e]) {
                continue;
            }
            visited[node.e] = true;

            for (Node n : list[node.e]) {
                if (dist[n.e] > dist[node.e] + n.w) {
                     dist[n.e] = dist[node.e] + n.w;
                     que.add(new Node(n.e, dist[n.e])); // 갱신할 경우 큐에 넣기
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}