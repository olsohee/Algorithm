
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());

        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0; i < cnt; i++) {
            int num = Integer.parseInt(br.readLine());
            if(!stack.isEmpty() && num == 0) {
                stack.pop();
            } else {
                stack.push(num);
            }
        }

        int size = stack.size();
        int result = 0;
        for(int i = 0; i < size; i++) {
            result += stack.pop();
        }
        System.out.println(result);
    }
}