import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] arr = new int[100001];
    static boolean[] visited = new boolean[100001];
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N));
    }

    static int bfs(int n) {
        Queue<Integer> que = new LinkedList<>();

        que.add(n);
        visited[n] = true;
        arr[n] = 1;

        while(!que.isEmpty()) {
            Integer p = que.poll();

            if(p == K) {
                return arr[p] - 1;
            }

            if(p-1 >= 0  && !visited[p-1]) {
                arr[p-1] = arr[p] + 1;
                visited[p-1] = true;
                que.add(p-1);
            }

            if(p+1 <= 100000 && !visited[p+1]) {
                arr[p+1] = arr[p] + 1;
                visited[p+1] = true;
                que.add(p+1);
            }

            if(2 * p <= 100000 && !visited[2 * p]) {
                arr[2 * p] = arr[p] + 1;
                visited[2*p] = true;
                que.add(2 * p);
            }
        }
        return 0;
    }
}