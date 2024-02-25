import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static boolean[] isKnow;
    static boolean[][] map;
    static int[][] party;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isKnow = new boolean[N + 1];
        map = new boolean[N + 1][N + 1];
        party = new int[M][];

        st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        for (int i = 0; i < len; i++) {
            isKnow[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            len = Integer.parseInt(st.nextToken());
            party[i] = new int[len];
            party[i][0] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < len; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
                map[party[i][j - 1]][party[i][j]] = map[party[i][j]][party[i][j - 1]] = true;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (isKnow[i]) {
                dfs(i);
            }
        }

        for (int i = 0; i < M; i++) {
            if (!isKnow[party[i][0]]) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int know) {
        for (int i = 1; i <= N; i++) {
            if (map[know][i] && !isKnow[i]) {
                isKnow[i] = true;
                dfs(i);
            }
        }
    }
}

