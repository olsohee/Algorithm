
import java.io.*;
import java.util.*;


public class Main {

    static Queue<Integer> truckQue = new LinkedList<>();
    static Queue<Integer> bridgeQue = new LinkedList<>();
    static int sum;
    static int cnt;
    static int n; // 트럭 수
    static int w; // 다리 길이
    static int L; // 다리 하중

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            truckQue.offer(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < w; i++) {
            bridgeQue.offer(0);
        }

        while (!bridgeQue.isEmpty()) {
            sum -= bridgeQue.poll();

            if(!truckQue.isEmpty()) {
                if(sum + truckQue.peek() <= L) {
                    Integer truck = truckQue.poll();
                    bridgeQue.offer(truck);
                    sum += truck;
                } else {
                    bridgeQue.offer(0);
                }
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}