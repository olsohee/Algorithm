import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int L, C;
    static char[] arr;
    static boolean[] isContain;
    static List<String> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        isContain = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        // 정렬
        Arrays.sort(arr);

        // 백트래킹으로 L개의 조합 생성
        getCode(0, 0);

        for (String answer : answerList) {
            System.out.println(answer);
        }
    }

    private static void getCode(int start, int len) {
        if (len == L) {
            String code = "";
            for (int i = 0; i < C; i++) {
                if (isContain[i]) {
                    code += arr[i];
                }
            }
            if (isAnswer(code)) {
                answerList.add(code);
            }
            return;
        }

        for (int i = start; i < C; i++) {
            isContain[i] = true;
            getCode(i + 1, len + 1);
            isContain[i] = false;
        }
    }

    private static boolean isAnswer(String code) {
        int vowelCnt = 0;
        int consonantCnt = 0;
        for (int i = 0; i < L; i++) {
            char c = code.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelCnt++;
            } else {
                consonantCnt++;
            }
        }
        return vowelCnt >= 1 && consonantCnt >= 2;
    }
}
