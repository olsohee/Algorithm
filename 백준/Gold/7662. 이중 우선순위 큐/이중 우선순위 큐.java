import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
//            PriorityQueue<Integer> maxQue = new PriorityQueue<>((o1, o2) -> o2 - o1);
//            PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> maxQue = new PriorityQueue<>((o1, o2) ->
            {
                if (o1 == o2) return 0;
                else if (o1 > o2) return -1;
                else return 1;
            });
            PriorityQueue<Integer> minQue = new PriorityQueue<>();
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                // 1. 삽입
                if (command.equals("I")) {
                    maxQue.add(num);
                    minQue.add(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                }
                if (command.equals("D")) {
                    // 2. 최댓값 삭제
                    if (num == 1) {
                        while (!maxQue.isEmpty()) {
                            Integer pollNum = maxQue.poll();
                            if (map.get(pollNum) > 0) {
                                map.put(pollNum, map.get(pollNum) - 1);
                                break;
                            }
                        }
                    }
                    // 3. 최솟값 삭제
                    else {
                        while (!minQue.isEmpty()) {
                            Integer pollNum = minQue.poll();
                            if (map.get(pollNum) > 0) {
                                map.put(pollNum, map.get(pollNum) - 1);
                                break;
                            }
                        }
                    }
                }
            }

            List<Integer> list = new LinkedList<>();
            for (int num : map.keySet()) {
                if (map.get(num) > 0) {
                    list.add(num);
                }
            }

            if (list.size() == 0) {
                sb.append("EMPTY").append('\n');
            } else {
                Collections.sort(list);
                sb.append(list.get(list.size() - 1) + " " + list.get(0)).append('\n');
            }
        }
        System.out.println(sb);
    }
}