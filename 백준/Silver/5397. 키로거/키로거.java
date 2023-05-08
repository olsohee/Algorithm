import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for(int i = 0; i < num; i++) {
            String str = br.readLine();
            for(int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == '<') {
                    if(!stack1.isEmpty()) {
                        stack2.push(stack1.pop());
                    }
                } else if(str.charAt(j) == '>') {
                    if(!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                } else if(str.charAt(j) == '-') {
                    if (!stack1.isEmpty()) {
                        stack1.pop();
                    }
                } else {
                    stack1.push(str.charAt(j));
                }
            }
            while(!stack2.isEmpty())
                stack1.push(stack2.pop());

            StringBuffer sb = new StringBuffer();
            while (!stack1.isEmpty())
                sb.append(stack1.pop());

           
            System.out.println(sb.reverse());
        }
    }
}