import java.util.*;

class Solution {
    
        int n;
        int[] info;
        int maxDiff = 0;
        List<int[]> list = new ArrayList<>();

        public int[] solution(int n, int[] info) {
            this.n = n;
            this.info = info;

            // n발을 10~0점에 나누기
            dfs(0, n, new int[11]);

            int[] answer = new int[11];
            for (int[] result : list) {
                int diff = getResult(result);

                if (maxDiff < diff) {
                    maxDiff = diff;
                    answer = result;
                }

                if (maxDiff == diff) {
                    for (int i = 10; i >= 0; i--) {
                        if (answer[i] > result[i]) {
                            break;
                        } else if (answer[i] < result[i]) {
                            answer = result;
                            break;
                        }
                    }
                }
            }

            System.out.println("maxDiff = " + maxDiff);

            if (maxDiff <= 0) {
                return new int[]{-1}; // 라이언이 무조건 지거나 비기면 -1 반환
            }
            
            return answer;
        }

        private void dfs(int idx, int remain, int[] result) {
            if (idx == 11) {
                if (remain == 0) {
                    list.add(result);
                }
                return;
            }

            for (int i = 0; i <= remain; i++) {
                int[] cloneArr = result.clone();
                cloneArr[idx] = i;
                dfs(idx + 1, remain - i, cloneArr);
            }
        }

        private int getResult(int[] result) {

            int apeach = 0;
            int lion = 0;
            // 똑같은 횟수로 맞혔으면 어피치가 점수 획득
            // 둘 다 0점이면 둘 다 획득 불가
            for (int i = 0; i <= 10; i++) {
                int score = 10 - i;

                if (info[i] >= result[i]) {
                    if (info[i] != 0) {
                        apeach += score;
                    }
                } else {
                    lion += score;
                }
            }

            // System.out.println(apeach);
            // System.out.println(lion);

            return lion - apeach;
        }
    }

