import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] parents;
    static int[] money;
    static int n;
    static int m;
    static int k;

    public static void main(String[] args) throws IOException {
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        money = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }
        
        // 1. 합치기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            union(n1, n2);
        }
        
        // 2. 각각의 집합과 친구가 되기 위해 필요한 돈 계산
        int needMoney = 0;
        Set<Integer> set = new HashSet<>(); // 서로 다른 부모 노드 인덱스를 저장
        for (int i = 1; i<= n; i++) {
            set.add(find(i));
        }
        
        for (int idx : set) {
            needMoney += money[idx];
        }
        
        if (needMoney <= k) {
            System.out.println(needMoney);
        } else {
            System.out.println("Oh no");
        }
    }
    
    public static void union (int n1, int n2) {
        // 필요한 돈이 적은 노드를 기준으로 합치기
        int p1 = find(n1);
        int p2 = find(n2);
        
        if (money[p1] < money[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
        }
    }
    
    public static int find (int x) {
        if (parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }
}