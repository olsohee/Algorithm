import java.util.*;

class Solution {
    
    String[][] relation;
    int r;
    int c;
    List<String> keyList = new ArrayList<>();
    List<String> answer = new ArrayList<>(); // 최종적으로 선택된 키들이 저장됨
    
    public int solution(String[][] relation) {
        this.relation = relation;
        r = relation.length;
        c = relation[0].length;
        
        // 1. 후보키 조합 만들기
        comb(0, "");
        
        // 2. 유일성 만족하는 키 추리기
        List<String> uniqueKeyList = new ArrayList<>();
        for (int i = 0; i < keyList.size(); i++) {
            if (isUniqueKey(keyList.get(i))) {
                uniqueKeyList.add(keyList.get(i));
            }
        }
        
        // 3. 최소성 만족하는 키 추리기
        Collections.sort(uniqueKeyList, (o1, o2) -> o1.length() - o2.length());
        
        for (String key : uniqueKeyList) {
            if (isMinimal(key)) {
                answer.add(key);
            }
        }
        
        return answer.size();
    }
    
    private void comb(int start, String key) {
        if (!key.equals("")) {
            keyList.add(key);
        }
        
        for (int i = start; i < c; i++) {
            comb(i + 1, key + i);
        }
    }
    
    private boolean isUniqueKey(String key) {
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i < r; i++) {
            String str = "";
            for (char c : key.toCharArray()) {
                int idx = c - '0';
                str += relation[i][idx];
            }
            set.add(str);
        }
        
        return set.size() == r;
    }
    
    private boolean isMinimal(String key) {
        for (String savedKey : answer) {
            int cnt = 0;
            for (char c : key.toCharArray()) {
                if (savedKey.contains(String.valueOf(c))) {
                    cnt++;
                }
            }
            // 유일성 X
            if (cnt == savedKey.length()) {
                return false; 
            }
        }
        return true;
    }
}