import java.util.*;

class Solution {
    
    String[] user_id;
    String[] banned_id;
    Set<Set<String>> set = new HashSet<>();
    boolean[] visited;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        visited = new boolean[user_id.length];
        
        dfs(0, new HashSet<>());
        
        return set.size();
    }
    
    private void dfs(int idx, Set<String> names) {
        if (idx == banned_id.length) {
            Set<String> copySet = new HashSet<>();
            for (String name : names) {
                copySet.add(name);
            }
            set.add(copySet);
            return;
        }
        
        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i] && bannedName(user_id[i], banned_id[idx])) {
                visited[i] = true;
                names.add(user_id[i]);
                dfs(idx + 1, names);
                visited[i] = false;
                names.remove(user_id[i]);
            }
        }
    }
    
    private boolean bannedName(String name, String banned) {
        if (name.length() != banned.length()) {
            return false;
        }
        
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == banned.charAt(i) || banned.charAt(i) == '*') {
                continue;
            } else {
                return false;
            }
        }
        
        return true;
    }
}