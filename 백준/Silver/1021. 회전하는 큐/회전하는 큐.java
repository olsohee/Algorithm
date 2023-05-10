
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        LinkedList<Integer> deque = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            deque.offerLast(i);
        }

        int[] arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0; //2번, 3번 연산의 횟수
        for(int i = 0; i < m; i++) {
            int cnt2 = deque.indexOf(arr[i]); //2번 연산의 경우 연산 횟수
            int cnt3 = deque.size() - deque.indexOf(arr[i]); //3번 연산의 경우 연산 횟수

            if(cnt2 < cnt3) {
                while(deque.peek() != arr[i]) {
                    deque.addLast(deque.removeFirst());
                    count++;
                }
            } else {
                while(deque.peek() != arr[i]) {
                    deque.addFirst(deque.removeLast());
                    count++;
                }
            }
            deque.removeFirst();
        }

        System.out.println(count);
    }
}