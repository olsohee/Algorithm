import java.util.*;

class Solution {

    int n;
    int s;
    int a;
    int b;
    int[][] fares;
    List<List<Node>> list = new ArrayList<>();
    int[] togetherFares;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;
        this.fares = fares;
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        togetherFares = new int[n + 1];
        Arrays.fill(togetherFares, Integer.MAX_VALUE);

        // Node 리스트에 담기 (그래프 구현)
        for(int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int fare = fares[i][2];

            list.get(start).add(new Node(end, fare));
            list.get(end).add(new Node(start, fare));
        }

        // 시작 노드부터 각 노드까지의 최소 금액 구하기 (togetherFares)
        dijkstra(s, togetherFares);

        // a, b 노드에서 각 노드까지의 최소 금액 구하기
        int[] aloneFares1 = new int[n + 1];
        Arrays.fill(aloneFares1, Integer.MAX_VALUE);
        dijkstra(a, aloneFares1);

        int[] aloneFares2 = new int[n + 1];
        Arrays.fill(aloneFares2, Integer.MAX_VALUE);
        dijkstra(b, aloneFares2);

        for(int i = 1; i <= n; i++) {
            int sum = togetherFares[i] + aloneFares1[i] + aloneFares2[i];
            answer = Math.min(answer, sum);
        }
        
        return answer;
    }

    private void dijkstra(int start, int[] fares) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        fares[start] = 0;
        Node node = new Node(start, 0);
        que.add(node);

        while(!que.isEmpty()) {
            Node nowNode = que.poll();
            int len = list.get(nowNode.end).size();

            for(int i = 0; i < len; i++) {
                Node newNode = list.get(nowNode.end).get(i);
                int fare = fares[nowNode.end] + newNode.fare;
                if(fares[newNode.end] > fare) {
                    fares[newNode.end] = fare;
                    que.offer(new Node(newNode.end, fare));
                }
            }
        }
    }

    class Node implements Comparable<Node> {
        int end;
        int fare;

        public Node(int end, int fare) {
            this.end = end;
            this.fare = fare;
        }

        @Override
        public int compareTo(Node o) {
            return this.fare - o.fare;
        }
    }
}