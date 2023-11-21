
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V;
    static int E;
    static int K;
    static ArrayList<List<Node>> list = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        for(int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, dist));
        }

        dijkstra(K);

        for(int i = 1; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        dist[start] = 0;
        que.add(new Node(start, 0));

        while(!que.isEmpty()) {
            Node node = que.poll();

            int len = list.get(node.v).size();
            for(int i = 0; i < len; i++) {
                Node newNode = list.get(node.v).get(i);
                if(dist[newNode.v] > node.w + newNode.w) {
                    dist[newNode.v] = node.w + newNode.w;
                    que.add(new Node(newNode.v, dist[newNode.v]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int v; // 도착지
        int w; // 가중치

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
