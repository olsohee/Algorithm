import java.util.*;

class Solution {

    int n;
    int[][] paths;
    int[] gates;
    int[] summits;
    ArrayList<List<Node>> list = new ArrayList<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        this.n = n;
        this.paths = paths;
        this.gates = gates;
        this.summits = summits;

        // 초기화
        for(int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }

        // list에 node 담기 (간선 표시)
        for(int i = 0; i < paths.length; i++) {
            int start = paths[i][0];
            int end = paths[i][1];
            int dist = paths[i][2];

            // 출입구 또는 산봉우리인 경우 (단방향)
            if(isGate(start) || isSummit(end)) {
                list.get(start).add(new Node(end, dist));
            } else if(isGate(end) || isSummit(start)) {
                list.get(end).add(new Node(start, dist));
            } else {
                // 그 외 (양방향)
                list.get(end).add(new Node(start, dist));
                list.get(start).add(new Node(end, dist));
            }
        }

        // 다익스트라
        return dijkstra(n, gates, summits);
    }

    private int[] dijkstra(int n, int[] gates, int[] summits) {
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        PriorityQueue<Node> que = new PriorityQueue<>();
        // 시작점을 큐에 담기(이때 가중치는 0)
        for (int gate : gates) {
            que.add(new Node(gate, 0)); // 시작점까지의 최소 intensity는 0
            intensity[gate] = 0;
        }

        while (!que.isEmpty()) {
            Node node = que.poll();
            int len = list.get(node.end).size();

            // 갱신 안되는 경우 패스
            if(node.dist > intensity[node.end]) {
                continue;
            }

            for(int i = 0; i < len; i++) {
                Node newNode = list.get(node.end).get(i);

                int newDist = Math.max(intensity[node.end], newNode.dist);
                if(intensity[newNode.end] > newDist) {
                    intensity[newNode.end] = newDist;
                    que.add(new Node(newNode.end, newDist));
                }
            }
        }

        // 산봉우리의 최소값
        int minSummit = Integer.MAX_VALUE;
        // 거리
        int minIntensity = Integer.MAX_VALUE;

        Arrays.sort(summits);
        for (int summit : summits) {
            if(intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }
        }

        return new int[]{minSummit, minIntensity};
    }

    public boolean isGate(int number) {
        for (int gate : gates) {
            if(number == gate) {
                return true;
            }
        }
        return false;
    }

    public boolean isSummit(int number) {
        for (int summit : summits) {
            if(number == summit) {
                return true;
            }
        }
        return false;
    }

    class Node implements Comparable<Node> {
        int end;
        int dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
