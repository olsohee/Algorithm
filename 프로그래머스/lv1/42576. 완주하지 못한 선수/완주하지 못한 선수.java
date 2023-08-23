import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();

        for(String p : participant) {
            if(map.get(p) != null) {
                Integer cnt = map.get(p);
                map.put(p, cnt+1);
            } else {
                map.put(p, 1);
            }
        }

        for(String c : completion) {
            
            if(map.get(c) == 1) {
                map.remove(c);
            } else {
                map.replace(c, map.get(c) - 1);
            }
        }

        for(String p : participant) {
            if(map.get(p) != null) {
                return p;
            } 
        }
        return null;
    }
}