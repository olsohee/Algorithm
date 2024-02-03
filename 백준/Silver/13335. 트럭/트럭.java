
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int w;
    static int l;
    static Queue<Integer> truck = new LinkedList<>();
    static Queue<Integer> bridge = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        int sum = 0;
        int time = 0;

        while (!bridge.isEmpty()) {
            time++;
            sum -= bridge.poll(); // 다리에서 트럭 내리기
            if (!truck.isEmpty()) {
                if (truck.peek() + sum <= l) {
                    sum += truck.peek();
                    bridge.add(truck.poll()); // 다리로 트럭 올리
                } else {
                    bridge.add(0);
                }
            }
        }

        System.out.println(time);
    }
}
