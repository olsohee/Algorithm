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

        st = new StringTokenizer(br.readLine());
        int[] trucks = new int[n];
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            que.add(0);
        }
        int sum = 0;
        int truckIdx = 0;
        int time = 0;

        while (truckIdx < n) {
            Integer pollTruck = que.poll();
            sum -= pollTruck;
            if (sum + trucks[truckIdx] <= l) {
                que.add(trucks[truckIdx]);
                sum += trucks[truckIdx];
                truckIdx++;
            } else {
                que.add(0);
            }

            time++;
        }

        // 마지막 트럭이 다리 위에 올라간 순간부터 내려가는데까지 걸리는 시간 계산
        time += que.size();

        System.out.println(time);
    }
}
