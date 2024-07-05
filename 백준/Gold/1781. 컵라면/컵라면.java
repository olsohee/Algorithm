import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int answer = 0;
        Queue<Homework> que = new PriorityQueue<>((Homework o1, Homework o2) -> o2.deadline - o1.deadline);
        Queue<Homework> tempQue = new PriorityQueue<>((Homework o1, Homework o2) -> o2.getCnt - o1.getCnt);

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            que.add(new Homework(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // i 날짜에 할 숙제
        for (int i = n; i >= 1; i--) {
            while (!que.isEmpty() && que.peek().deadline >= i) {
                tempQue.add(que.poll());
            }

            if (tempQue.isEmpty()) {
                continue;
            }
            answer += tempQue.poll().getCnt;
        }

        System.out.println(answer);
    }

    private static class Homework {
        int deadline;
        int getCnt;

        public Homework(int deadline, int getCnt) {
            this.deadline = deadline;
            this.getCnt = getCnt;
        }
    }
}
