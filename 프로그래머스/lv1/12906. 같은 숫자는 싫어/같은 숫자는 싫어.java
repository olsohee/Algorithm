import java.util.*;
public class Solution {
        public int[] solution(int []arr) {

            Stack<Integer> stack = new Stack<>();

            for(int i = 0; i < arr.length; i++) {
                if(stack.isEmpty() || stack.peek() != arr[i]) {
                    stack.push(arr[i]);
                } else {
                    continue;
                }
            }

            int count = stack.size();
            int[] answer = new int[count];
            for(int i = count - 1; i >= 0; i--) {
                answer[i] = stack.pop();
            }

            return answer;
        }
    }