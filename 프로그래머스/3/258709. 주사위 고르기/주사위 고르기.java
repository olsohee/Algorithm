import java.util.*;

class Solution {
    
        int n; // 주사위 개수
        int maxWin; // 승리 횟수
        int[] answer; // 가장 높은 승리 횟수를 갖는 주사위 조합
        List<List<Integer>> combinations = new ArrayList<>();
        int[][] dice;

        public int[] solution(int[][] dice) {
            this.n = dice.length;
            this.dice = dice;

            // 1. 0번 ~ n-1번 주사위 중 A가 가져갈 주사위의 조합 생성: n개의 주사위 중 n/2개 고르기
            dfs(new ArrayList<>(), 0);

            // 2. 해당 조합에 대해 승리 횟수 계산하기 (maxWin, answer 갱신)
            for (List<Integer> combination : combinations) {
                List<Integer> resultA = getResultForA(combination);
                List<Integer> resultB = getResultForB(combination);
                
                // A가 가질 수 있는 결과들 vs B가 가질 수 있는 결과들: 이분탐색으로 A가 이기는 횟수 구하기
                int aWinCnt = 0;
                Collections.sort(resultA);
                Collections.sort(resultB);
                for (Integer aSum : resultA) {
                    int start = 0;
                    int end = resultB.size() - 1;
                    while (start <= end) {
                        int mid = (start + end) / 2;
                        if (resultB.get(mid) >= aSum) {
                            end = mid - 1;
                        } else {
                            start = mid + 1;
                        }
                    }
                    aWinCnt += start;
                }

                // 갱신
                if (maxWin < aWinCnt) {
                    maxWin = aWinCnt;
                    answer = combination.stream()
                            .mapToInt(i -> i + 1)
                            .toArray();
                }
            }

            // 승리 확률이 가장 높은 A가 가져갈 주사위 번호 (오름차순)
            Arrays.sort(answer);
            return answer;
        }

        private void dfs(List<Integer> diceNums, int start) {
            if (diceNums.size() == n / 2) {
                ArrayList<Integer> combination = new ArrayList<>();
                for (Integer diceNum : diceNums) {
                    combination.add(diceNum);
                }
                combinations.add(combination);
                return;
            }

            for (int i = start; i < n; i++) {
                diceNums.add(i);
                dfs(diceNums, i + 1);
                diceNums.remove((Object)i);
            }
        }

        private List<Integer> getResultForA(List<Integer> aDiceNums) {
            List<int[]> aDiceList = new ArrayList<>();
            for (int i = 0; i < dice.length; i++) {
                if (!aDiceNums.contains(i)) {
                    continue;
                }
                aDiceList.add(dice[i]);
            }

            List<Integer> sumResult = new ArrayList<>();
            getResultByDfs(0, aDiceList, 0, sumResult);
            return sumResult;
        }

        private List<Integer> getResultForB(List<Integer> aDiceNums) {
            List<int[]> bDiceList = new ArrayList<>();
            for (int i = 0; i < dice.length; i++) {
                if (aDiceNums.contains(i)) {
                    continue;
                }
                bDiceList.add(dice[i]);
            }

            List<Integer> sumResult = new ArrayList<>();
            getResultByDfs(0, bDiceList, 0, sumResult);
            return sumResult;
        }

        private void getResultByDfs(int idx, List<int[]> diceList, int sum, List<Integer> sumResult) {
            if (idx == diceList.size()) {
                sumResult.add(sum);
                return;
            }
            int[] dice = diceList.get(idx);
            for (int num : dice) {
                getResultByDfs(idx + 1, diceList, sum + num, sumResult);
            }
        }
}
