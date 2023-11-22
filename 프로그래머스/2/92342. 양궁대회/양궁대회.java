import java.util.*;

// n개를 0~10점 어떻게 나눌지 조합 만들기
// 조합별로, 어피치 점수 - 라이언 점수 계산하기
// 어피치 점수 - 라이언 점수의 값이 가장 큰 조합이 정답 조합
// 어피치 점수 - 라이언 점수의 값이 0이거나 음수이면 -1리턴 (라이언 우승 불가)

class Solution {
    int n;
    int[] info;
    List<int[]> list = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;

        // n개를 0~10점에 어떻게 나눌지 조합 만들기
        dfs(0, new int[11], n);

        // 조합별로, 어피치 점수 - 라이언 점수 계산하기
        int maxDiff = 0;
        int[] answerArr = new int[11];
        for(int i = 0; i < list.size(); i++) {
            int[] arr = list.get(i);

            int diff = getScoreDiff(arr);
            if(maxDiff == diff) {
                // 점수차가 같은 경우가 여러개 있으면, 더 낮은 점수를 많이 맞춘 경우를 답으로
                for(int j = 10; j >= 0; j--) {
                    if(arr[j] == answerArr[j]) {
                        continue;
                    }
                    if(arr[j] > answerArr[j]) {
                        answerArr = arr;
                        break;
                    } else {
                        break;
                    }
                }
            } else if(maxDiff < diff) {
                maxDiff = diff;
                answerArr = arr;
            }
        }

        if(maxDiff <= 0) {
            return new int[] {-1};
        } else {
            return answerArr;
        }
    }

    private int getScoreDiff(int[] arr) {
        int lionScore = 0;
        int apeachScore = 0;

        for(int i = 0; i <= 10; i++) {
            if(arr[i] == 0 && info[i] == 0) {
                continue;
            }
            if(arr[i] > info[i]) {
                lionScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }

        return lionScore - apeachScore;
    }

    private void dfs(int index, int[] arr, int remain) {
        if(index == 11) {
            if(remain == 0) {
                list.add(arr);
            }
            return;
        }

        // cnt 인덱스에 숫자 몇을 넣을지
        for(int i = 0; i <= remain; i++) {
            int[] cloneArr = arr.clone();
            cloneArr[index] = i;
            dfs(index + 1, cloneArr, remain - i);
            if(cloneArr[index] > info[index]) {
                break;
            }
        }

    }
}

