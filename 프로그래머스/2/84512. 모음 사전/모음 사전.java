import java.util.*;

class Solution {
    
    String[] words = new String[]{"A", "E", "I", "O", "U"};
    List<String> list = new ArrayList<>();
    
    public int solution(String word) {
        
        dfs("");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) {
                return i + 1;
            }
        }
        return -1;
    }
    
    public void dfs(String s) {
        if (s.length() == 5) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            list.add(s + words[i]);
            dfs(s + words[i]);
        }
    }
}