import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        int v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());
        parent = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            parent[i] = i;
        }

        // 노드들을 합치기
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            union(n1, n2);
        }

        // 1번 컴퓨터와 연결된 컴퓨터 수 찾기
        int answer = 0;
        int p = find(1);

        for (int i = 2; i <= v; i++) {
            if (find(i) == p) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void union(int n1, int n2) {
        // 최상단의 부모 노드 찾기
        int parent1 = find(n1);
        int parent2 = find(n2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
}