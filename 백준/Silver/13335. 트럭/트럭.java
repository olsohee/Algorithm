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
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Queue<Integer> trucks = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }
        int sum = 0;
        int time = 0;

        while (!trucks.isEmpty()) {
            sum -= bridge.poll();
            Integer nextTruck = trucks.peek();
            if (sum + nextTruck <= l) {
                bridge.add(nextTruck);
                sum += trucks.poll();
            } else {
                bridge.add(0);
            }
            time++;
        }

        // 마지막 트럭이 다리 위에 올라간 순간부터 내려가는데까지 걸리는 시간 계산
        time += bridge.size();

        System.out.println(time);
    }
}
