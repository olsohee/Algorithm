import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] parents;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        // 합치기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    union(i + 1, j + 1); // 입력값이 1이면 i노드와 j노드를 합치기
                }
            }
        }

        // 여행 경로가 모두 같은 집합인지 확인
        int[] route = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }
        int p = find(route[0]);
        for (int i = 1; i < m; i++) {
            // 하나라도 다른 집합이면 NO 출력
            if (find(route[i]) != p) {
                System.out.println("NO");
                return;
            }
        }
        // 모두 같은 집합이면 YES 출력
        System.out.println("YES");
    }

    public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
        }
    }

    public static int find(int x) {
        if (parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }
}
