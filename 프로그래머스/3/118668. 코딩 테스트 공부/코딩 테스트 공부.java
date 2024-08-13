import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {

        int goalAlp = alp;
        int goalCop = cop;
        for (int[] problem : problems) {
            goalAlp = Math.max(goalAlp, problem[0]);
            goalCop = Math.max(goalCop, problem[1]);
        }

        int[][] time = new int[goalAlp + 1][goalCop + 1];
        for (int[] arr : time) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        time[alp][cop] = 0;

        for (int i = alp; i <= goalAlp; i++) {
            for (int j = cop; j <= goalCop; j++) {
                // 알고, 코딩력이 i, j일 때 할 수 있는 것들

                // 알고리즘 공부
                if (i < goalAlp) {
                    time[i + 1][j] = Math.min(time[i + 1][j], time[i][j] + 1);
                } else {
                    time[goalAlp][j] = Math.min(time[goalAlp][j], time[i][j] + 1);
                }

                // 코딩 공부
                if (j < goalCop) {
                    time[i][j + 1] = Math.min(time[i][j + 1], time[i][j] + 1);
                } else {
                    time[i][goalCop] = Math.min(time[i][goalCop], time[i][j] + 1);
                }

                // 문제 풀기
                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        int newAlp = i + problem[2];
                        int newCop = j + problem[3];
                        if (newAlp >= goalAlp) newAlp = goalAlp;
                        if (newCop >= goalCop) newCop = goalCop;
                        time[newAlp][newCop] = Math.min(time[newAlp][newCop], time[i][j] + problem[4]);
                    }
                }
            }
        }
        return time[goalAlp][goalCop];
//        int min = Integer.MAX_VALUE;
//        for (int i = goalAlp; i < goalAlp + 30; i++) {
//            for (int j = goalCop; j < goalCop + 30; j++) {
//                min = Math.min(min, time[i][j]);
//            }
//        }
//        return min;
    }
}
