import java.util.*;

class Solution {
    
    int n;
    Map<String, Integer> nameMap = new HashMap<>();
    int[] parent;
    int[] answer;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        n = enroll.length;
        answer = new int[n];
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            nameMap.put(enroll[i], i);
        }
        
        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1; // 자신이 최상단이면 -1
            } else {
                parent[i] = nameMap.get(referral[i]);   
            }
        }
        
        for (int i = 0; i < seller.length; i++) {
            dfs(nameMap.get(seller[i]), amount[i] * 100);
        }
        
        return answer;
    }
    
    private void dfs(int now, int amount) {
        int result = (int)(amount * 0.1);
        if (result == 0) {
            answer[now] += amount;
        } else {
            answer[now] += amount - result;
            // 0.1프로를 부모 노드에게 전달
            if (parent[now] != -1) {
                dfs(parent[now], result);
            }
        }
    }
}