import java.util.*;

class Solution {

    private List<List<Integer>> graph = new ArrayList<>();
    private int answer = 0;
    private int[] info;
    
    public int solution(int[] info, int[][] edges) {
        
        this.info = info;
        
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        // 루트노드부터 시작
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list);
        
        return answer;
    }
    
    private void dfs(int node, int sheep, int wolf, List<Integer> nextRoute) {
        
        if (info[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (wolf >= sheep) {
            return;
        }
        
        answer = Math.max(answer, sheep);
        
        // 다음으로 이동
        // List<Integer> copyNextRoute = new ArrayList<>();
        // for (int next : nextRoute) {
        //     copyNextRoute.add(next);
        // }
        
        // copyNextRoute.remove(Integer.valueOf(node));
        nextRoute.remove(Integer.valueOf(node));
        
        for (int next : graph.get(node)) {
            // copyNextRoute.add(next);
            nextRoute.add(next);
        }
        
        for (int next : nextRoute) {
            List<Integer> list = new ArrayList<>();
            for (int i : nextRoute) {
                list.add(i);
            }
            dfs(next, sheep, wolf, list);
        }
        
        // for (int next : copyNextRoute) {
        //     dfs(next, sheep, wolf, copyNextRoute);
        // }
    }
}