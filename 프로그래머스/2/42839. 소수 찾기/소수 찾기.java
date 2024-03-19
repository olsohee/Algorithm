import java.util.*;

class Solution {
    
    Set<Integer> set = new HashSet<>();
    String[] arr;
    boolean[] visited;
    
    public int solution(String numbers) {
        
        arr = numbers.split("");
        visited = new boolean[arr.length];
        
        // 완전탐색해서 구한 수 set에 넣기
        dfs("");
        
        // set에 있는 값 탐색하며 소수 개수 찾기
        int answer = 0;
        for (int num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }
    
    public void dfs(String str) {
        if (str != "") {
            set.add(Integer.parseInt(str));
        }
        
        if (str.length() == arr.length) {
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(str + arr[i]);
                visited[i] = false;
            }
        }
    }
    
    public boolean isPrime(int num) {
        if (num <= 1) return false;
        
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}