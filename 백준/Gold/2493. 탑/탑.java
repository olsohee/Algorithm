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
        answer = new int[n + 1];
        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 스택이 비어있으면, 답은 0 + 현재 값 push
        // 2. 스택이 비어있지 않으면, peek한 값과 현재 값 비교
        // 2-1. peek한 값이 더 크면, 답은 peek한 값의 인덱스 + 현재 값 push
        // 2-2. 그렇지 않으면, peek한 값을 pop하고 2번으로

        Stack<Node> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            int now = arr[i];
            if (stack.isEmpty()) {
                answer[i] = 0;
                stack.push(new Node(now, i));
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        answer[i] = 0;
                        stack.push(new Node(now, i));
                        break;
                    }
                    if (stack.peek().num > now) {
                        answer[i] = stack.peek().idx;
                        stack.push(new Node(now, i));
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        for (int i = 1; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    static class Node {
        int num, idx;

        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}