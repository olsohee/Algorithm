import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        stack = new Stack<>();

        sb = new StringBuilder();
        func(str);

        System.out.println(sb);
    }

    static void func(String str) {
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && orderCalculate(stack.peek()) >= orderCalculate(c)) { 
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                    break;

                case '(':
                    stack.push(c);
                    break;

                case ')': // 여는 괄호까지 스택의 값을 출력
                    while(!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;

                default: // 문자인 경우
                    sb.append(c);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }

    static int orderCalculate(char c) {
        if(c == '*' || c == '/') return 2;
        else if(c == '+' || c == '-') return 1;
        else return 0;
    }
}