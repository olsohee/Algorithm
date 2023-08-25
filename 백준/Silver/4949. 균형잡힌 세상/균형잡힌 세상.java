
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String input = br.readLine();
            if(input.equals(".")) {
                break;
            } else {
                String result = solve(input);
                sb.append(result).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static String solve(String input) {

        LinkedList<Character> stack = new LinkedList<>();

        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if(c == '(' || c == '[') {
                stack.push(c);
            }
            else if(c == ')') {
                if(!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return "no";
                }
            }

            else if(c == ']') {
                if(!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return "no";
                }
            }
        }

        if(stack.isEmpty()) {
            return "yes";
        } else {
            return "no";
        }
    }
}