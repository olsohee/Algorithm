import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        String str = br.readLine();
        char[] arr = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int temp = 1;
        for (int i = 0; i < arr.length; i++) {
            // 1. '(', '['이면 스택에 넣기
            if (arr[i] == '(') {
                stack.push(arr[i]);
                temp *= 2;
            }
            if (arr[i] == '[') {
                stack.push(arr[i]);
                temp *= 3;
            }

            // 2. ')', ']'이면 스택에 있는 값 조회하기
            if (arr[i] == ')') {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                // peek한 값과 짝이 안맞으면 false
                if (stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }
                // peek한 값과 짝이 맞으면 스택의 값 pop, temp의 값 answer에 반영하고 temp 값 초기화
                stack.pop();
                if (arr[i - 1] == '(') {
                    answer += temp;
                    temp /= 2;
                } else {
                    temp /= 2;
                }
            }
            if (arr[i] == ']') {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                if (stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }
                stack.pop();
                if (arr[i - 1] == '[') {
                    answer += temp;
                    temp /= 3;
                } else {
                    temp /= 3;
                }
            }
        }

        // 마지막에 스택에 값이 남아있으면 false
        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
