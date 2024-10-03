import java.util.*;

class Solution {
    
    int[][] dice;
    int n;
    List<int[]> aDiceList = new ArrayList<>();
    List<int[]> bDiceList = new ArrayList<>();
    List<Integer> aResult;
    List<Integer> bResult;
            
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        int[] answer = new int[n / 2];
        int maxBIdx = 0;
        
        // 1. 주사위 나눠 갖기
        dfs(0, 0, new int[n / 2]); // A가 갖는 주사위 idx
        
        // 2. A와 B가 각각 자신의 주사위들을 던져서 나올 수 있는 숫자 리스트
        for (int[] aDiceNums : aDiceList) {
            
            // bDiceNums채우기
            int[] bDiceNums = new int[n / 2];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                boolean contain = false;
                for (int j = 0; j < aDiceNums.length; j++) {
                    if (aDiceNums[j] == i) {
                        contain = true;
                        break;
                    }
                }
                if (!contain) {
                    bDiceNums[idx++] = i;
                }
            }
            
            // A, B가 나올 수 있는 결과
            aResult = new ArrayList<>();
            bResult = new ArrayList<>();
            throwDice(aDiceNums, 0, 0, true);
            throwDice(bDiceNums, 0, 0, false);
            Collections.sort(bResult);
            int maxWinCnt = 0;
            int aWinCnt = 0;
            
            // 3. A, B가 나올 수 있는 결과(aResult, bResult)끼리 비교해서 승패가리기 (이분탐색)
            for (int result1 : aResult) {
                // System.out.println("A의 결과 값 = " + result1);
                int min = 0;
                int max = bResult.size() - 1;
                
                while (min <= max) {
                    int mid = (min + max) / 2;
                    // System.out.println("b의 결과 값 = " + bResult.get(mid));
                    int result2 = bResult.get(mid);
                    if (result2 >= result1) {
                        max = mid - 1;
                    } else {
                        min = mid + 1;
                    }
                }
                
                aWinCnt += min;
            }
            
            if (maxWinCnt < aWinCnt) {
                maxWinCnt = aWinCnt;
                answer = aDiceNums;
            }
        }
        
        
        for (int n : answer) {
            System.out.println(n);
        }
        
        // 승리 확률이 가장 높은, A의 주사위 번호 (오름차순) (승리 확률이 가장 높은 경우는 1개의 상황뿐!)
        return answer;
    }
    
    private void throwDice(int[] diceNums, int idx, int result, boolean isForA) {
        if (idx == diceNums.length) {
            if (isForA) {
                aResult.add(result); 
            } else {
                 bResult.add(result);
            }
            return;
        }
        int[] nowDice = dice[diceNums[idx]];
        for (int i = 0; i < 6; i++) {
            throwDice(diceNums, idx + 1, result + nowDice[i], isForA);
        }
    }
    
    private void dfs(int depth, int idx, int[] arr) {
        if (depth == n/2) {
            aDiceList.add(arr.clone());
            return;
        }
        
        for (int i = idx; i < n; i++) {
            arr[depth] = i;
            dfs(depth + 1, i + 1, arr);
        }
    }
}