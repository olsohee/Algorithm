
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            Stack<Character> stack2 = new Stack<>();
            String input = br.readLine();
            for(int j = 0; j < input.length(); j++) {
                char cmd = input.charAt(j);

                if(cmd == '<') {
                    if(!stack.isEmpty()) {
                        stack2.push(stack.pop());
                    }
                } else if(cmd == '>') {
                    if(!stack2.isEmpty()) {
                        stack.push(stack2.pop());
                    }
                } else if(cmd == '-') {
                    if(!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(cmd);
                }
            }
            while(!stack.isEmpty()) {
                stack2.push(stack.pop());
            }
            StringBuffer sb = new StringBuffer();
            while(!stack2.isEmpty()) {
                sb.append(stack2.pop());
            }
            System.out.println(sb);
        }
    }
}