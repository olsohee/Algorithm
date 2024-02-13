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

        // 출입구 -> 정상으로의 최소 비용을 구하면 된다.
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

        // 다익스트라
        dijkstra();
        Arrays.sort(summits);

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
//        boolean[] visited = new boolean[n + 1];

        for (int gate : gates) {
            dist[gate] = 0;
            que.add(new Node(gate, 0));
        }

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.intensity > dist[now.end]) continue;
//            if (visited[now.end]) continue;
//            visited[now.end] = true;
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



// class Solution {

//     int n;
//     int[][] paths;
//     int[] gates;
//     int[] summits;
//     ArrayList<List<Node>> list = new ArrayList<>();

//     public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

//         this.n = n;
//         this.paths = paths;
//         this.gates = gates;
//         this.summits = summits;

//         // 1. 초기 값 세팅
//         for (int i = 0; i < n + 1; i++) {
//             list.add(new ArrayList<>());
//         }

//         for (int i = 0; i < paths.length; i++) {
//             int node1 = paths[i][0];
//             int node2 = paths[i][1];
//             int intensity = paths[i][2];

//             // 출입구나 정상이면 단방향 이동
//             if(isGate(node1) || isSummit(node2)) {
//                 list.get(node1).add(new Node(node2, intensity));
//             } else if(isSummit(node1) || isGate(node2)) {
//                 list.get(node2).add(new Node(node1, intensity));
//             } else {
//                 // 그 외는 양방향 이동
//                 list.get(node1).add(new Node(node2, intensity));
//                 list.get(node2).add(new Node(node1, intensity));
//             }
//         }

//         // 2. 다익스트라를 위한 최소 비용 배열 초기화
//         int[] intensityArr = new int[n + 1];
//         Arrays.fill(intensityArr, Integer.MAX_VALUE);

//         // 3. 다익스트라
//         dijkstra(intensityArr);

//         // 4. 정상을 돌면서, 최소 비용으로 도착한 정상 찾기
//         int summitNumber = 0;
//         int minIntensity = Integer.MAX_VALUE;

//         Arrays.sort(summits);
//         for(int i = 0; i < summits.length; i++) {
//             int summit = summits[i];
//             if(minIntensity > intensityArr[summit]) {
//                 summitNumber = summit;
//                 minIntensity = intensityArr[summit];
//             }
//         }

//         return new int[]{summitNumber, minIntensity};
//     }

//     private boolean isGate(int node) {
//         for (int i = 0; i < gates.length; i++) {
//             if(gates[i] == node) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     private boolean isSummit(int node) {
//         for(int i = 0; i < summits.length; i++) {
//             if(summits[i] == node) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     private void dijkstra(int[] intensityArr) {

//         PriorityQueue<Node> que = new PriorityQueue<>();

//         // 시작점 큐에 담기 + 시작점까지의 최소 비용 초기화
//         for(int i = 0; i < gates.length; i++) {
//             int gate = gates[i];
//             que.add(new Node(gate, 0)); // 이때의 가중치는 0 (ex, 시작점인 1까지의 최소 비용은 0)
//             intensityArr[gate] = 0;
//         }

//         // 큐에서 노드를 꺼내서 해당 노드에서 특정 노드로 이동하며 최소 비용 갱신
//         while(!que.isEmpty()) {
//             Node nowNode = que.poll();

//             // 갱신 안되는 경우 패스
//             if(nowNode.intensity > intensityArr[nowNode.end]) {
//                 continue;
//             }

//             List<Node> nodes = list.get(nowNode.end);
//             for (Node node : nodes) {
//                 if(intensityArr[node.end] > Math.max(intensityArr[nowNode.end], node.intensity)) {
//                     intensityArr[node.end] = Math.max(intensityArr[nowNode.end], node.intensity);
//                     que.add(new Node(node.end, intensityArr[node.end]));
//                 }
//             }
//         }
//     }

//     class Node implements Comparable<Node> {
//         int end;
//         int intensity;

//         public Node(int end, int intensity) {
//             this.end = end;
//             this.intensity = intensity;
//         }

//         @Override
//         public int compareTo(Node o) {
//             return this.intensity - o.intensity;
//         }
//     }
// }
