import java.util.*;

class Solution {
    
    int n;
    int[] weak;
    int[] dist;
    int answer = Integer.MAX_VALUE;
    List<int[]> distList = new ArrayList<>();
    List<int[]> weakList = new ArrayList<>();
    boolean[] visited;
    
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        visited = new boolean[dist.length];
        
        // dist의 각 사람들의 순서 조합 구하기
        dfs(0, new int[dist.length]);
        
        // 취약 지점 처리 순서 조합 구하기
        int[] route = new int[weak.length * 2 - 1];
        for (int i = 0; i < route.length; i++) {
            if (i < weak.length) {
                route[i] = weak[i];
            } else if (i == weak.length) {
                route[i] = route[i - 1] + (n - route[i - 1]) + weak[0];
            } else {
                route[i] = route[i - 1] + weak[i - weak.length] - weak[i - weak.length - 1];
            }
        }
        
        for (int start = 0; start < weak.length; start++) {
            int[] order = new int[weak.length];
            int idx = 0;
            int i = start;
            while (idx < weak.length) {
                order[idx] = route[i];
                i++;                    
                idx++;
            }
            weakList.add(order);
        }
        
        for (int[] distOrder : distList) {
            for (int[] weakOrder : weakList) {
                check(distOrder, weakOrder);
            }
        }
        
        if (answer == Integer.MAX_VALUE) return -1;
        return answer;
    }
    
    private void check(int[] dist, int[] weak) {
        // System.out.println();
        // System.out.println();
        // for (int i : dist) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
        // for (int i : weak) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
        
        int personIdx = 0;
        int idx = 0;
        
        while (true) {
            if (personIdx == dist.length) {
                return;
                // break;
            }
            
            int interval = this.dist[dist[personIdx]];
            int sum = 0;
            // System.out.println("***interval = " + interval);
            // System.out.println("***idx = " + idx);
            boolean finish = false;
            while (true) {
                // System.out.println("sum = " + sum);
                if (idx == weak.length - 1) {
                    finish = true;
                    break;
                }
                if (interval < sum + weak[idx + 1] - weak[idx]) {
                    break;
                }
                sum += weak[idx + 1] - weak[idx];
                idx++;
            }
            
            if (finish) {
                personIdx++;
                break;
            }
            
            personIdx++;
            idx++;
        }
        // System.out.println("사용된 사람 수");
        // System.out.println(personIdx);
        answer = Math.min(answer, personIdx);
    }
    
    private void dfs(int idx, int[] order) {
        if (idx == dist.length) {
            distList.add(Arrays.copyOf(order, order.length));
            return;
        }
        
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                
                order[idx] = i;
                dfs(idx + 1, order);
                
                visited[i] = false;
            }
        }
    }
    
}