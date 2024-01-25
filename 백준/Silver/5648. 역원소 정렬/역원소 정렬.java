
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static List<String> inputs = new ArrayList<>();
    static List<Long> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        int inputCount = 0;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            inputs.add(st.nextToken());
            inputCount++;
        }

        while (inputCount < n) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                inputs.add(st.nextToken());
                inputCount++;
            }
        }

        for (String input : inputs) {
            String reverse = "";
            for (int i = input.length() - 1; i >= 0; i--) {
                reverse += input.charAt(i);
            }
            answers.add(Long.parseLong(reverse));
        }

        Collections.sort(answers);
        for (Long answer : answers) {
            System.out.println(answer);
        }
    }
}