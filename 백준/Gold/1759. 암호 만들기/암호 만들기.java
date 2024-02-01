
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N!)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int l;
    static int c;
    static char[] input;
    static char[] result;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        input = new char[c];
        result = new char[l];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            input[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(input);

        dfs(0, 0);
    }

    private static void dfs(int depth, int start) {
        // l개를 다 고른 경우
        if (depth == l) {
            if (checkCondition(result)) {
                for (char c : result) {
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }

        for (int i = start; i < c; i++) {
            // 포함 O
            result[depth] = input[i];
            dfs(depth + 1, i + 1);
        }
    }

    private static boolean checkCondition(char[] result) {
        // 최소 2개의 자음, 1개의 모음
        int consonant = 0; // 자음
        int vowel = 0; // 모음
        for (int i = 0; i < result.length; i++) {
            char c = result[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowel++;
            } else {
                consonant++;
            }
        }
        return consonant >= 2 && vowel >= 1;
    }
}
