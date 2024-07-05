import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        Queue<Integer> minQue = new PriorityQueue<>();
        Queue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            // 최대힙에 넣기
            if (minQue.size() == maxQue.size()) {
                maxQue.add(num);

                if (!minQue.isEmpty() && maxQue.peek() > minQue.peek()) {
                    minQue.add(maxQue.poll());
                    maxQue.add(minQue.poll());
                }
            }
            // 최소힙에 넣기
            else {
                minQue.add(num);

                if (maxQue.peek() > minQue.peek()) {
                    minQue.add(maxQue.poll());
                    maxQue.add(minQue.poll());
                }

//                System.out.println(Math.min(maxQue.peek(), minQue.peek()));
            }
            sb.append(maxQue.peek()).append('\n');
        }

        System.out.println(sb.toString());
    }
}