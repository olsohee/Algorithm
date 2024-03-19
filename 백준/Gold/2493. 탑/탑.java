import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;
    static int[] answer;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        answer = new int[n + 1];
        arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(st.nextToken());
        }
        Stack<Tower> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            if (stack.isEmpty()) {
                stack.push(new Tower(arr[i], i));
                continue;
            }

            // peek한 값이 더 크면 peek한 값에 신호가 닿고 스택에 넣기
            if (stack.peek().num > arr[i]) {
                answer[i] = stack.peek().idx;
                stack.push(new Tower(arr[i], i));
            }

            // peek한 값이 더 작거나 같으면 신호가 닿을 때까지 pop
            else {
                while (!stack.isEmpty()) {
                    if (stack.peek().num <= arr[i]) {
                        stack.pop();
                    } else {
                        answer[i] = stack.peek().idx;
                        break;
                    }
                }
                stack.push(new Tower(arr[i], i));
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static class Tower {
        int num;
        int idx;
        public Tower (int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}
