
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] solution(int[] answers) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] score = new int[3];

        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == a[i % 5]) score[0]++;
            if(answers[i] == b[i % 8]) score[1]++;
            if(answers[i] == c[i % 10]) score[2]++;
        }

        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        List<Integer> list = new ArrayList<>();
        if(maxScore == score[0]) list.add(1);
        if(maxScore == score[1]) list.add(2);
        if(maxScore == score[2]) list.add(3);

        return list.stream().mapToInt(i -> i.intValue()).toArray();
    }
}