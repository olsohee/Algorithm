import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] contain = new boolean[26];
    static List<String> wordList = new ArrayList<>();
    static int k;
    static int answer;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            wordList.add(br.readLine());
        }

        if (k < 5) { // 모든 단어를 읽을 수 없음
            System.out.println(0);
        } else {
            // dfs로 k개의 알파벳 선택
            contain['a' - 97] = true;
            contain['n' - 97] = true;
            contain['t' - 97] = true;
            contain['i' - 97] = true;
            contain['c' - 97] = true;
            dfs(5, 0);
            System.out.println(answer);
        }
    }

    private static void dfs(int cnt, int idx) {
        if (cnt == k) {
            answer = Math.max(answer, getResult());
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (contain[i]) continue;
            contain[i] = true;
            dfs(cnt + 1, i + 1);
            contain[i] = false;
        }
    }

    private static int getResult() {
        int cnt = 0;
        for (String word : wordList) {
            boolean canRead = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!contain[c - 97]) {
                    canRead = false;
                    break;
                }
            }
            if (canRead) {
                cnt++;
            }
        }
        return cnt;
    }
}
