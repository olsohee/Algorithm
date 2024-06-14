import java.util.*;


class Solution {

    int n;
    int[][] dice;
    List<List<Integer>> combination = new ArrayList<>();
    int winCnt = 0;
    List<Integer> answer;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;

        // 1. 주사위 나누기
        combination(0, 0, new ArrayList<>());

        for (List<Integer> aIdxList : combination) {
            // 2. A 결과 추출
            List<Integer> resultA = new ArrayList<>();
            getResult(aIdxList, 0, 0, resultA);

            // 3. B 결과 추출
            List<Integer> bIdxList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (!aIdxList.contains(i)) {
                    bIdxList.add(i);
                }
            }
            List<Integer> resultB = new ArrayList<>();
            getResult(bIdxList, 0, 0, resultB);
            
            // 4. A, B 결과 비교 (이분탐색)
            int winCnt = compare(resultA, resultB);
            if (this.winCnt < winCnt) {
                this.winCnt = winCnt;
                this.answer = aIdxList;
            }
        }

        // 오름차순 정렬
        Collections.sort(answer);
        return answer.stream()
                .mapToInt(integer -> integer + 1)
                .toArray();
    }

    public int compare(List<Integer> resultA, List<Integer> resultB) {
        Collections.sort(resultA);
        Collections.sort(resultB);
        int winCnt = 0;

        for (int score : resultA) {
            int start = 0;
            int end = resultB.size() - 1;
            // score보다 큰 하한선 찾기
            while (start <= end) {
                int mid = (start + end) / 2;
                if (resultB.get(mid) < score) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            winCnt += start;
        }
        
        //winCnt == 현재 조합에서의 A가 이기는 횟수
        return winCnt;
    }

    public void getResult(List<Integer> idxList, int idx, int sum, List<Integer> resultA) {
        if (idx == idxList.size()) {
            resultA.add(sum);
            return;
        }

        int[] dice = this.dice[idxList.get(idx)];
        for (int i = 0; i < 6; i++) {
            getResult(idxList, idx + 1, sum + dice[i], resultA);
        }
    }

    public void combination(int depth, int start, List<Integer> idxList) {
        if (depth == n / 2) {
            combination.add(new ArrayList<>(idxList));
            return;
        }

        for (int i = start; i < n; i++) {
            idxList.add(i);
            combination(depth + 1, i + 1, idxList);
            idxList.remove(depth);
        }
    }
}
