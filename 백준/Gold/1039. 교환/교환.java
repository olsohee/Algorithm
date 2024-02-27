import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int answer = -1;
    static boolean[][] visited = new boolean[1000001][11];

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dfs();
        System.out.println(answer);
    }

    private static void dfs() {
        Queue<Info> que = new LinkedList<>();

        que.add(new Info(n, 0));
        visited[n][0] = true;

        while (!que.isEmpty()) {
            Info now = que.poll();

            if (now.cnt == k) {
                answer = Math.max(answer, now.num);
                continue;
            }

            char[] arr = String.valueOf(now.num).toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    int next = swap(i, j, arr);
                    if (next != -1 && !visited[next][now.cnt + 1]) {
                        que.add(new Info(next, now.cnt + 1));
                        visited[next][now.cnt + 1] = true;
                    }
                }
            }
        }
    }

    private static int swap(int i, int j, char[] arr) {
        char[] cloneArr = arr.clone();
        cloneArr[i] = arr[j];
        cloneArr[j] = arr[i];

        if (cloneArr[0] == '0') {
            return -1;
        }
        return Integer.parseInt(new String(cloneArr));
    }

    static class Info {
        int num;
        int cnt;

        public Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
