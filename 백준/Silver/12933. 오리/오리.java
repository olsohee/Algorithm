import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        char[] input = br.readLine().toCharArray();
        boolean[] visited = new boolean[input.length];
        char[] word = {'q', 'u', 'a', 'c', 'k'};
        int answer = 0;

        for (int i = 0; i < input.length; i++) {
            if (visited[i]) continue;
            int wordIdx = 0;
            List<Integer> list = new ArrayList<>();

            for (int j = i; j < input.length; j++) {
                if (wordIdx == 5) {
                    wordIdx = 0;
                    list = new ArrayList<>();
                }
                if (visited[j]) continue;
                if (input[j] == word[wordIdx]) {
                    list.add(j);
                    visited[j] = true;
                    wordIdx++;
                }
            }

            // quack을 만들다가 중간에 끝난 경우
            if (wordIdx != 5) {
                for (Integer idx : list) {
                    visited[idx] = false;
                }
            }

            answer++;
        }

        for (boolean b : visited) {
            if (!b) {
                answer = -1;
            }
        }

        System.out.println(answer);
    }
}
