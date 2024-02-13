import java.util.*;
class Solution {

    int n;
    int[][] paths;
    int[] gates;
    int[] summits;
    List<List<Node>> list = new ArrayList<>();
    int[] dist;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        this.paths = paths;
        this.gates = gates;
        this.summits = summits;
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        // 출입구 -> 정상으로의 최소 비용만 구하면 된다.
        for (int[] path : paths) {
            int v1 = path[0];
            int v2 = path[1];
            int intensity = path[2];
            // 단방향
            if (isGate(v1) || isSummit(v2)) {
                list.get(v1).add(new Node(v2, intensity));
            } else if (isSummit(v1) || isGate(v2)) {
                list.get(v2).add(new Node(v1, intensity));
            }
            // 양방향
            else {
                list.get(v1).add(new Node(v2, intensity));
                list.get(v2).add(new Node(v1, intensity));
            }
        }
        
        dijkstra(); // 다익스트라

        Arrays.sort(summits); // 산봉우리 번호가 낮은 순서대로 정렬
        int minIntensity = Integer.MAX_VALUE;
        int summitNum = 0;
        for (int summit : summits) {
            if (dist[summit] < minIntensity) {
                minIntensity = dist[summit];
                summitNum = summit;
            }
        }

        return new int[]{summitNum, minIntensity};
    }

    private void dijkstra() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n + 1];

        for (int gate : gates) {
            dist[gate] = 0;
            que.add(new Node(gate, 0));
        }

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.intensity > dist[now.end]) continue;
            if (visited[now.end]) continue;
            visited[now.end] = true;
            for (Node node : list.get(now.end)) {
                if (dist[node.end] > Math.max(dist[now.end], node.intensity)) {
                    dist[node.end] = Math.max(dist[now.end], node.intensity);
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }

    private boolean isSummit(int v) {
        for (int summit : summits) {
            if (summit == v) {
                return true;
            }
        }
        return false;
    }

    private boolean isGate(int v) {
        for (int gate : gates) {
            if (gate == v) {
                return true;
            }
        }
        return false;
    }

    class Node implements Comparable<Node> {
        int end, intensity;

        public Node(int end, int intensity) {
            this.end = end;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Node o) {
            return intensity - o.intensity;
        }
    }
}
