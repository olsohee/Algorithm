
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int K;
    static int[] visited = new int[100001];
    static int[] beforeIdx = new int[100001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N);

        System.out.println(visited[K] - 1);

        // 전에 위치로 순차적으로 돌아가며 위치 찾기
        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int idx = K;
        while(idx != N) {
            stack.push(beforeIdx[idx]);
            idx = beforeIdx[idx];
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void bfs(int start) {

        Queue<Integer> que = new LinkedList<>();

        visited[start] = 1;
        que.add(start);


        while(!que.isEmpty()) {
            Integer idx = que.poll();

            if(idx == K) {
                return;
            }


            // 순간 이동
            if((idx * 2 >= 0) && (idx  * 2 <= 100000) && (visited[idx * 2] == 0)) {
                visited[idx * 2] = visited[idx] + 1;
                beforeIdx[idx * 2] = idx;
                que.offer(idx * 2);
            }

            // 뒤로 가기
            if((idx - 1 >= 0) && (idx - 1 <= 100000) && (visited[idx - 1] == 0)) {
                visited[idx - 1] = visited[idx] + 1;
                beforeIdx[idx - 1] = idx;
                que.offer(idx - 1);
            }

            // 앞으로 가기
            if((idx + 1 >= 0) && (idx + 1 <= 100000) && (visited[idx + 1] == 0)) {
                visited[idx + 1] = visited[idx] + 1;
                beforeIdx[idx + 1] = idx;
                que.offer(idx + 1);
            }
        }
    }
}
