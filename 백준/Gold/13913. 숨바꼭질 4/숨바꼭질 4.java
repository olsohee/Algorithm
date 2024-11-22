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
        int k = Integer.parseInt(st.nextToken());

        int[] route = new int[100001]; // route[i] = i 노드로 오기 전에 거친 노드
        Arrays.fill(route, -1);

        // bfs
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(n, 0));
        route[n] = n;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.node == k) {
                System.out.println(now.cnt);

                Stack<Integer> stack = new Stack<>();
                int node = now.node;
                while (true) {
                    if (node == n) {
                        stack.add(node);
                        break;
                    }
                    stack.add(node);
                    node = route[node];
                    
                }
                while (!stack.isEmpty()) {
                    System.out.print(stack.pop() + " ");
                }
                break;
            }


            if (now.node - 1 >= 0 && route[now.node - 1] == -1) {
                que.add(new Node(now.node - 1, now.cnt + 1));
                route[now.node - 1] = now.node;
            }
            if (now.node + 1 <= 100000 && route[now.node + 1] == -1) {
                que.add(new Node(now.node + 1, now.cnt + 1));
                route[now.node + 1] = now.node;
            }
            if (now.node * 2 <= 100000 && route[now.node * 2] == -1) {
                que.add(new Node(now.node * 2, now.cnt + 1));
                route[now.node * 2] = now.node;
            }
        }
    }

    private static class Node {

        int node;
        int cnt;

        public Node(int node, int cnt) {
            this.node = node;
            this.cnt = cnt;
        }
    }
}