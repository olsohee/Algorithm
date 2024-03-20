import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] arr3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == arr1[i % 5]) result1++;
            if (answers[i] == arr2[i % 8]) result2++;
            if (answers[i] == arr3[i % 10]) result3++;
        }
        List<Integer> answerList = new ArrayList<>();
        int max = Math.max(result1, Math.max(result2, result3));
        if (result1 == max) answerList.add(1);
        if (result2 == max) answerList.add(2);
        if (result3 == max) answerList.add(3);
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}