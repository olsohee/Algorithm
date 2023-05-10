import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            String s = br.readLine();

            for(int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);

                if(stack.isEmpty() || stack.peek() != c) {
                    stack.push(c);
                } else {
                    stack.pop();
                }
            }

            if(stack.isEmpty()) {
                result++;
            }
        }
        System.out.println(result);
    }
}