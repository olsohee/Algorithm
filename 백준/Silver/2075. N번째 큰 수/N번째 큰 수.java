import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        List<Queue<Integer>> queList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            queList.add(new PriorityQueue<>((o1, o2) -> o2 - o1));
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                queList.get(j).add(Integer.parseInt(st.nextToken()));
            }
        }

        // 큰 수를 우선으로 n번 추출
        int answer = 0;
        int count = n;
        while (count-- > 0) {
            int maxNum = Integer.MIN_VALUE;
            int queIdx = -1;
            for (int j = 0; j < n; j++) {
                int num = queList.get(j).peek();
                if (maxNum < num) {
                    maxNum = num;
                    queIdx = j;
                }
            }
            int poll = queList.get(queIdx).poll();
            if (count == 0) {
                answer = poll;
            }
        }
        System.out.println(answer);
    }
}
