import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String input;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input = br.readLine();
        visited = new boolean[input.length()];

        func(0, input.length() - 1);
        System.out.println(sb);
    }

    private static void func(int start, int end) {
        if (start > end) {
            return;
        }

        // 1. start~end 범위에서 가장 작은 문자 선택
        int idx = start;
        for (int i = start; i <= end; i++) {
            if (input.charAt(idx) > input.charAt(i)) {
                idx = i;
            }
        }

        // 2. 선택 결과 출력
        visited[idx] = true;

        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) {
                sb.append(input.charAt(i));
            }
        }
        sb.append('\n');

        // 3. 선택한 문자의 뒤범위에서 다음 문자 선택
        func(idx + 1, end);
        
        // 4. 선택한 문자의 앞범위에서 다음 문자 선택
        func(start, idx - 1);
    }
}
