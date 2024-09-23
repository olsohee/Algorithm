
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] lengthArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            lengthArr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[n + 1];
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            if (lengthArr[i] < lengthArr[stack.peek()]) {
                answer[i] = stack.peek();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && lengthArr[i] > lengthArr[stack.peek()]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    answer[i] = stack.peek();
                }
                stack.push(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}

