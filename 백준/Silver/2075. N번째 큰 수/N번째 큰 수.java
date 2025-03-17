import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                que.add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < n - 1; i++) {
            que.poll();
        }
        System.out.println(que.poll());
    }
}
