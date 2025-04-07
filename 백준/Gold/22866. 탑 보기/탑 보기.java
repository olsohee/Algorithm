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
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Building> st = new Stack<>();
        int[] cnt = new int[n];
        int[] nearIdx = new int[n];
        Arrays.fill(nearIdx, Integer.MAX_VALUE);

        // 왼쪽
        for (int i = 0; i < n; i++) {
            // 제거할 거 제거
            while (!st.isEmpty() && st.peek().height <= heights[i]) {
                st.pop();
            }

            cnt[i] += st.size();
            if (!st.isEmpty()) {
                nearIdx[i] = st.peek().idx;
            }

            st.push(new Building(heights[i], i));
        }

        // 오른쪽
        st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek().height <= heights[i]) {
                st.pop();
            }

            cnt[i] += st.size();
            if (!st.isEmpty()) {
                if (nearIdx[i] == Integer.MAX_VALUE || st.peek().idx - i < i - nearIdx[i]) {
                    nearIdx[i] = st.peek().idx;
                }
            }

            st.push(new Building(heights[i], i));
        }

        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0) {
                System.out.println(cnt[i]);
            } else {
                System.out.println(cnt[i] + " " + (nearIdx[i] + 1));
            }
        }
    }

    static class Building {
        int height;
        int idx;

        public Building(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
}
