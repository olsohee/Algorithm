import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int K;
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int second = bfs(N);

        System.out.println(second);
    }

    public static int bfs(int start) {

        Queue<Pair> que = new LinkedList<>();

        que.add(new Pair(start, 0));

        while(!que.isEmpty()) {
            Pair p = que.poll();
            int idx = p.idx;
            int totalSec = p.totalSec;

            if(p.idx == K) {
                return p.totalSec;
            }
            
            // 순간 이동
            if(idx * 2 >= 0 && idx  * 2 <= 100000 && !visited[idx * 2]) {
                visited[idx * 2] = true;
                que.offer(new Pair(idx * 2, totalSec));
            }

            // 뒤로 가기
            if(idx - 1 >= 0 && idx - 1 <= 100000 && !visited[idx - 1]) {
                visited[idx - 1] = true;
                que.offer(new Pair(idx - 1, totalSec + 1));
            }

            // 앞으로 가기
            if(idx + 1 >= 0 && idx + 1 <= 100000 && !visited[idx + 1]) {
                visited[idx + 1] = true;
                que.offer(new Pair(idx + 1, totalSec + 1));
            }
        }

        return -1;
    }

    static class Pair {

        int idx;
        int totalSec;

        public Pair(int idx, int totalSec) {
            this.idx = idx;
            this.totalSec = totalSec;
        }
    }
}
