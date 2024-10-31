import java.util.*;

class Solution {
    
    int n;
    int[][] dice;
    int maxWinCnt;
    int[] answer;
    List<Integer> aResult;
    List<Integer> bResult;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        n = dice.length;
        
        // n개의 주사위를 나눠갖기 
        dfs(0, new int[n / 2], 0);
        
        Arrays.sort(answer);
        return Arrays.stream(answer)
            .map(i -> i + 1)
            .toArray();
    }
    
    private void dfs(int idx, int[] combination, int start) {
        if (idx == n / 2) {
            getResult(combination);
            return;
        }
        
        for (int i = start; i < n; i++) {
            combination[idx] = i;
            dfs(idx + 1, combination, i + 1);
        }
    }
    
    private void getResult(int[] aDices) {
        int[] bDices = new int[n / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            boolean isADice = false;;
            for (int j = 0; j < n / 2; j++) {
                if (aDices[j] == i) {
                    isADice = true;
                    break;
                }
            }
            if (!isADice) {
                bDices[idx++] = i;
            }
        }
        
        // A, B가 주사위들을 던져서 나올 수 있는 결과들 계산
        aResult = new ArrayList<>();
        bResult = new ArrayList<>();
        throwDices(aDices, 0, 0, true);
        throwDices(bDices, 0, 0, false);
        
        // 두 결과를 비교하여 A가 승리할 확률 계산 (이분탐색))
        
        Collections.sort(bResult);
        int winCnt = 0;
        for (int score : aResult) {
            // A의 점수가 score점일 때, B를 이기는 횟수
            int start = 0;
            int end = bResult.size() - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (bResult.get(mid) >= score) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            
            winCnt += start;
        }
        
        if (maxWinCnt < winCnt) {
            maxWinCnt = winCnt;
            answer = aDices.clone(); //!!!!
        }
    }
    
    private void throwDices(int[] dices, int idx, int result, boolean isForA) {
        if (idx == dices.length) {
            if (isForA) {
                aResult.add(result);
            } else {
                bResult.add(result);
            }
            return;
        }
        
        //dices[idx] 번 주사위 던지기
        for (int num : dice[dices[idx]]) {
            throwDices(dices, idx + 1, result + num, isForA);
        }
    }
}