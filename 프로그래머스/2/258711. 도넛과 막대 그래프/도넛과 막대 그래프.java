import java.util.*; 

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, List<Integer>> out = new HashMap<>();
        Map<Integer, List<Integer>> in = new HashMap<>();
        int max = 0;
        for (int[] edge : edges) {
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        
        for (int i = 1; i <= max; i++) {
            out.put(i, new ArrayList<>());
            in.put(i, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            List<Integer> list1 = out.get(edge[0]);
            list1.add(edge[1]);
            out.put(edge[0], list1);
            
            List<Integer> list2 = in.get(edge[1]);
            list2.add(edge[0]);
            in.put(edge[1], list2);
        }
        
        // 정점: 나가는 선이 2개 이상, 들어오는 선 없음
        int node = 0;
        for (int i = 1; i <= max; i++) {
            if (out.get(i).size() >= 2 && in.get(i).size() == 0) {
                node = i;
                break;
            }
        }
        int totalCnt = out.get(node).size();
        
        // 8자: 나가는 선 2개
        int eight = 0;
        for (int i = 1; i <= max; i++) {
            if (i == node) continue;
            if (out.get(i).size() == 2) {
                eight++;
            }
        }
        
        // 막대: 나가는 선 없음
        int bar = 0;
        for (int i = 1; i <= max; i++) {
            if (i == node) continue;
            if (out.get(i).size() == 0 && in.get(i).size() != 0) {
                bar++;
            }
        }
        
        // 생성한 정점 번호, 도넛 수, 막대 수, 8자 수 반환
        return new int[]{node, totalCnt - (bar + eight), bar, eight};
    }
}