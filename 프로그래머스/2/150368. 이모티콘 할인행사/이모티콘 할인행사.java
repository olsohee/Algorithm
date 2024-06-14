import java.util.*;

class Solution {
    
    int[][] users;
    int[] emoticons;
    List<int[]> saleList = new ArrayList<>();
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        
        // 할인 조합 만들기
        dfs(0, new int[emoticons.length]);
        
//         for (int[] arr : saleList) {
//             for (int i : arr) {
//                 System.out.print(i + " ");
                
//             }
//             System.out.println();
//         }
        
        // 각 할인 조합마다 판단
        int[] answer = new int[2];
        for (int[] sale : saleList) {
            int totalJoinCnt = 0;
            int totalBuyCost = 0;
            for (int[] user : users) {
                // user[0] : 비율, user[1]: 가격
                int buyCost = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    // user[0] 이상의 할인을 하면 구매
                    if (sale[i] >= user[0]) {
                        buyCost += emoticons[i] * (100 - sale[i]) / 100;
                    }
                }
                
                // 총 구매 금액에 user[1] 이상이면 플러스 가입
                if (buyCost >= user[1]) {
                    totalJoinCnt++;
                } else {
                    totalBuyCost += buyCost;
                }
            }
            
            // 플러스 가입자 수가 가장 많고, 구매 금액이 가장 많은 것이 답
            if (totalJoinCnt > answer[0]) {
                answer[0] = totalJoinCnt;
                answer[1] = totalBuyCost;
            } else if (totalJoinCnt == answer[0])  {
                if (totalBuyCost > answer[1]) {
                    answer[1] = totalBuyCost;
                }
            }
        }
        return answer;
    }
    
    public void dfs(int depth, int[] arr) {
        if (depth == emoticons.length) {
            saleList.add(arr.clone());
            return;
        }
        
        for (int i = 1; i <= 4; i++) {
            arr[depth] = i * 10;
            dfs(depth + 1, arr);
        }
    }
}