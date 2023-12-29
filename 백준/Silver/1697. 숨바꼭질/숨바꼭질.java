import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도: O(V+E) = 100,000 + 300,000 = 400,000
public class Main {

    static int n; // 수빈 위치
    static int k; // 동생 위치
    static int[] map = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(map[k] - 1);
    }

    private static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        map[n] = 1; // 수빈이 위치에 1 저장
        que.add(n);

        while (!que.isEmpty()) {
            Integer idx = que.poll();

            if (idx == k) {
                return;
            }

            // 뒤로
            if (idx - 1 >= 0 && map[idx - 1] == 0) {
                map[idx - 1] = map[idx] + 1;
                que.add(idx - 1);
            }

            // 앞으로
            if (idx + 1 <= 100000 && map[idx + 1] == 0) {
                map[idx + 1] = map[idx] + 1;
                que.add(idx + 1);
            }

            // 순간이동
            if (2 * idx <= 100000 && map[2 * idx] == 0) {
                map[2 * idx] = map[idx] + 1;
                que.add(2 * idx);
            }
        }
    }
}