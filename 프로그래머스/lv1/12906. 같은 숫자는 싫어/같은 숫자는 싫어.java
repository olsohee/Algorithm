import java.util.*;
public class Solution {
        public int[] solution(int []arr) {
ArrayList<Integer> list = new ArrayList<>();

            int preNum = 10;

            for(int n : arr) {
                if(preNum != n) {
                    list.add(n);
                    preNum = n;
                }
            }

            int[] answer = new int[list.size()];
            for(int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }

            return answer;
        }
    }