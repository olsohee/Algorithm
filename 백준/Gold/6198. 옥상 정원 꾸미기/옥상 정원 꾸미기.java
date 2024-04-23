import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            if (arr[stack.peek()] > arr[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                    answer += i - stack.pop() - 1;
                }
                stack.push(i);
            }
        }

        // 스택에 남은 값들도 처리
        int i = stack.pop();
        while (!stack.isEmpty()) {
            answer += i - stack.pop();
        }

        System.out.println(answer);
    }
}
