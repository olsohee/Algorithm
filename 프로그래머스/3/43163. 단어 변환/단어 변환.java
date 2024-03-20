import java.util.*;

class Solution {
    
    String begin;
    String target;
    String[] words;
    int answer = Integer.MAX_VALUE;
    int len = 0;
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;
        this.len = begin.length();
        this.visited = new boolean[words.length];
        
        dfs(0, begin);
        
        // 최소 변환 단계(불가능하면 0)
        if (answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    
    
    public void dfs(int cnt, String str) {
        
        if (str.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        // str과 words 배열 비교해서, 한 자리만 다르면 변환하기
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue; // 이미 해당 단어 방문했으면 패스
            
            String word = words[i];
        
            int diffCnt = 0;
            for (int j = 0; j < len; j++) {
                if (word.charAt(j) != str.charAt(j)) {
                    diffCnt++;
                }
            }
            // 한 자리만 다르면 dfs
            if (diffCnt == 1) {
                visited[i] = true;
                dfs(cnt + 1, word);
                visited[i] = false;
            }
        }
    }
}