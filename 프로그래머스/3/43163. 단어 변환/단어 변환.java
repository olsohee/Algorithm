import java.util.*;

class Solution {
    
    int answer = Integer.MAX_VALUE;
    int wordLen = 0;
    String target;
    String[] words;
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
       
        wordLen = begin.length();
        this.target = target;
        this.words = words;
        this.visited = new boolean[words.length];
        
        dfs(begin, 0);
        
        // 최소 변환 횟수 (불가하면 0)
        if (answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    
    public void dfs (String str, int cnt) {
        System.out.println("cnt = " + cnt + ", str = " + str);
        if (str.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        // 1자리만 다른 변환 가능한 단어가 있으면 변환하여 dfs
        for (int i = 0; i < words.length; i++) {
            int diff = 0;
            for (int j = 0; j < wordLen; j++) {
                if (words[i].charAt(j) != str.charAt(j)) {
                    diff++;
                }
            }
            if (diff == 1 && !visited[i]) {
                visited[i] = true;
                dfs(words[i], cnt + 1);
                visited[i] = false;
            }
        }
    }
}