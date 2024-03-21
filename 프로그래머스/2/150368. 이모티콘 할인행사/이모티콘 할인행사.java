import java.util.*;

class Solution {
    
    int[][] users;
    int[] emoticons;
    int n;
    int m;
    List<int[]> sales = new ArrayList<>();
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        
        n = users.length;
        m = emoticons.length;
        
        // 1. 각 이모티콘의 할인율의 경우의 수 (O(4^m)) (할인율: 10, 20, 30, 40%)
        dfs(0, new int[m]);
        
        // 2. 해당 경우의 수에서 사용자 별로 플러스 가입 OR 이모티콘 구매 판단 (O(100))
        List<Result> result = new ArrayList<>();
        
        // for (int[] arr : sales) {
        //     for (int n : arr) {
        //         System.out.print(n + " ");
        //     }
        //     System.out.println();
        // }
        
        // 세일 경우마다 Result(총 플러스 구매 수, 판매액)를 리스트에 저장
        for (int[] sale : sales) {
            int plus = 0;
            int purchase = 0;
            
            // 사용자마다 판단
            for (int[] user : users) {
                int sum = 0;
                for (int i = 0; i < m; i++) {
                    int percent = sale[i];
                    if (percent >= user[0]) {
                        sum += emoticons[i] * (100 - percent) / 100;
                    }
                }
                // 플러스 가입
                if (sum >= user[1]) {
                    plus++;
                } 
                // 이모티콘 구매
                else {
                    purchase += sum;
                }
            }
            
            result.add(new Result(plus, purchase));
        }
        
        // 플러스 가입 수, 매출액 반환 (1순위: 플러스 가입 수, 2순위: 매출액)
        Collections.sort(result);
        int[] answer = new int[2];
        answer[0] = result.get(0).plus;
        answer[1] = result.get(0).purchase;
        return answer;
    }
    
    public void dfs(int cnt, int[] arr) {
        
        // 이모티콘의 할인률 모두 결정되면(m개) 끝내기
        if (cnt == m) {
            sales.add(arr);
            return;
        }
        
        // cnt번째 이모티콘의 할인률 결정
        for (int i = 1; i <= 4; i++) {
            int[] cloneArr = arr.clone();
            cloneArr[cnt] = i * 10;
            dfs(cnt + 1, cloneArr);
        }
    }
    
    public class Result implements Comparable<Result> {
        int plus;
        int purchase;
        public Result (int plus, int purchase) {
            this.plus = plus;
            this.purchase = purchase;
        }
        
        @Override
        public int compareTo(Result o) {
            if (o.plus == this.plus) {
                return o.purchase - this.purchase;
            }
            return o.plus - this.plus;
        }
    }
}