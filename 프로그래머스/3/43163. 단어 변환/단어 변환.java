import java.util.*;

class Solution {
    
    String begin;
    String target;
    String[] words;
    boolean[] visited;
    int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;
        visited = new boolean[words.length]; // 각 단어에 대한 방문 처리
        
        dfs(begin, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public void dfs(String now, int cnt) {
        
        if (now.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canConvert(now, words[i])) {
                visited[i] = true;
                dfs(words[i], cnt + 1);
                visited[i] = false;
            }
        }
    }
    
    public boolean canConvert(String str1, String str2) {
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) diff++;
        }
        
        if (diff == 1) return true;
        return false;
    }
}