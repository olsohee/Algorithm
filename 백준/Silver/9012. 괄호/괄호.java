import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            String str = br.readLine();
            func(str);
        }
    }

    static void func(String str) {

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(c == '(') {
                stack.push(c);
            } else if(!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                System.out.println("NO");
                return;
            }
        }

        if(stack.isEmpty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}