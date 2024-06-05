import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        // 끝나는 시간이 이른 순으로 정렬 
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
            
      
        int last = routes[0][1];
        System.out.println(last);
        int answer = 1;
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] <= last && routes[i][1] >= last) {
                continue;
            } 
            last = routes[i][1];
            answer++;
            System.out.println(last);
            
        }
    
        
        // 설치해야 하는 최소 카메라 수 반환
        return answer;
    }
}