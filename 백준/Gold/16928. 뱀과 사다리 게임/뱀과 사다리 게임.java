
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
        Map<Integer, Integer> moveMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            moveMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            moveMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // bfs
        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        boolean[] visited = new boolean[101];

        visited[1] = true;
        que.add(new Node(1, 0));
        int answer = 0;
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.node == 100) {
                answer = now.cnt;
                break;
            }

            // 사다리이거나 뱀이면 이동(주사위 던지지 X)
//            if (moveMap.containsKey(now)) {
//                Integer next = moveMap.get(now);
//                if (!visited[next]) {
//                    que.add(new Node(next, now.cnt));
//                    visited[next] = true;
//
//                }
//                continue;
//
////                if (visited[next] == 0 || visited[next] > visited[now]) {
////                    que.add(next);
////                    visited[next] = visited[now];
////                    continue;
////                }
//            }

            // 사다리이거나 뱀 없으면 주사위 던지기
            for (int i = 1; i <= 6; i++) {
                int next = now.node + i;
                if (next > 100) continue;
                if (visited[next]) continue;
                if (moveMap.containsKey(next)) {
                    visited[next] = true;
                    que.add(new Node(moveMap.get(next), now.cnt + 1));
                } else {
                    visited[next] = true;
                    que.add(new Node(next, now.cnt + 1));
                }

            }
        }

        System.out.println(answer);
    }

    static class Node {
        int node, cnt;

        public Node(int node, int cnt) {
            this.node = node;
            this.cnt = cnt;
        }
    }
}