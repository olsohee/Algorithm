import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Set<String> set = new HashSet<>();
    static List<Input> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        for (String str : set) {
            answer.add(new Input(str));
        }

        Collections.sort(answer);
        for (Input input : answer) {
            System.out.println(input.str);
        }
    }

    static class Input implements Comparable<Input> {
        String str;

        public Input(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Input o) {
            if (str.length() == o.str.length()) {
                return str.compareTo(o.str);
            }

            return str.length() - o.str.length();
        }
    }
}