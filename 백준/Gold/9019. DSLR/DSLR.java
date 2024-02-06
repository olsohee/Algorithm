import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(T * (V + E)) = T * (10,000 + 40,000)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int a;
    static int b;

    public static void main(String[] args) throws IOException {

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            findResult(a, b);
        }
    }

    private static void findResult(int a, int b) {
        boolean[] visited = new boolean[10000];
        String[] command = new String[10000];
        Arrays.fill(command, "");

        Queue<Integer> que = new LinkedList<>();
        que.add(a);
        visited[a] = true;

        while (!que.isEmpty() && !visited[b]) {
            Integer n = que.poll();

            int d = (n * 2) % 10000;
            int s = n == 0 ? 9999 : n - 1;
            int l = n % 1000 * 10 + n / 1000;
            int r = n / 10 + n % 10 * 1000;

            if (!visited[d]) {
                visited[d] = true;
                command[d] = command[n] + "D";
                que.add(d);
            }

            if (!visited[s]) {
                visited[s] = true;
                command[s] = command[n] + "S";
                que.add(s);
            }

            if (!visited[l]) {
                visited[l] = true;
                command[l] = command[n] + "L";
                que.add(l);
            }

            if (!visited[r]) {
                visited[r] = true;
                command[r] = command[n] + "R";
                que.add(r);
            }
        }

        System.out.println(command[b]);
    }
}