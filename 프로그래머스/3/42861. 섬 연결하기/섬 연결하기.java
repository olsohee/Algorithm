import java.util.*;

class Solution {
    
    int[] parents;
    int answer = 0;
    
    public int solution(int n, int[][] costs) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        // 비용이 적은 순으로 정렬
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        
        // 비용이 적은 순으로, 모든 노드가 하나의 집합에 속할 때까지 다리 건설
        for (int[] cost : costs) {
            // 다른 집합인 경우에만 합치기
            if (getParents(cost[0]) != getParents(cost[1])) {
                union(cost[0], cost[1]);
                answer += cost[2];
            }
        }
        
        return answer;
    }
    
    public int getParents(int n) {
        if (parents[n] == n) return n;
        return parents[n] = getParents(parents[n]);
    }
    
    public void union(int n1, int n2) {
        int p1 = getParents(n1);
        int p2 = getParents(n2);
        if (p1 < p2) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
        }
    }
}