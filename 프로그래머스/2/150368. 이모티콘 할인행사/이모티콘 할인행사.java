import java.util.*;

class Solution {

    List<int[]> combination = new ArrayList<>();
    int[][] users;
    int[] emoticons;

    public int[] solution(int[][] users, int[] emoticons) {

        this.users = users;
        this.emoticons = emoticons;

        // 재귀로 조합 만들기
        dfs(1, new int[emoticons.length + 1]);

        // 조합별로 구매 + 가입 계산
        List<Result> results = new ArrayList<>();
        for (int[] combi : combination) {
            results.add(purchase(combi));
        }

        // 가입자수 내림차순 정렬, 총금액 내림차순 정렬
        Collections.sort(results, new Result(1, 1));

        Result result = results.get(0);
        return new int[]{result.join, result.totalPrice};
    }

    private Result purchase(int[] combi) {

        int join = 0; // 가입자 수
        int totalPrice = 0; //구매금액

        for (int[] user : users) {
            int price = 0;

            for(int i = 1; i <= emoticons.length; i++) {
                int percent = combi[i];

                if(user[0] <= percent) {
                    int purchasePrice = emoticons[i - 1] * (100 - percent) / 100;
                    price += purchasePrice;
                }
            }

            if(price >= user[1]) {
                join++;
            } else {
                totalPrice += price;
            }
        }

        return new Result(join, totalPrice);
    }

    public void dfs(int cnt, int[] arr) {
        if(cnt == emoticons.length + 1) {
            combination.add(arr);
            return;
        }

        for(int i = 1; i <= 4; i++) {
            int[] cloneArr = arr.clone();
            cloneArr[cnt] = i * 10;
            dfs(cnt + 1, cloneArr);
        }
    }

    class Result implements Comparator<Result> {
        int join;
        int totalPrice;

        public Result(int join, int totalPrice) {
            this.join = join;
            this.totalPrice = totalPrice;
        }

        @Override
        public int compare(Result o1, Result o2) {
            if(o1.join == o2.join) {
                return o2.totalPrice - o1.totalPrice;
            }

            return o2.join - o1.join;
        }
    }
}
