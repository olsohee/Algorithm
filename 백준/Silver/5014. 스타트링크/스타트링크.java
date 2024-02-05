import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(V + E) = 1,000,000 + 2 * 1,000,000
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        // bfs
        int[] map = new int[F + 1];
        map[S] = 1;

        Queue<Integer> que = new LinkedList<>();
        que.add(S);
        while (!que.isEmpty()) {
            Integer num = que.poll();

            // 도착한 경우
            if (num == G) {
                System.out.println(map[G] - 1);
                return;
            }

            // Up
            if (num + U <= F && map[num + U] == 0) {
                int newNum = num + U;
                map[newNum] = map[num] + 1;
                que.add(newNum);
            }

            // Down
            if (num - D > 0 && map[num - D] == 0) {
                int newNum = num - D;
                map[newNum] = map[num] + 1;
                que.add(newNum);
            }
        }

        // 도착하지 못한 경우
        System.out.println("use the stairs");
    }
}