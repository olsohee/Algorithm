import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> minQue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        for (String s : operations) {
            String op = s.split(" ")[0];
            int num = Integer.parseInt(s.split(" ")[1]);
            
            if (op.equals("I")) {
                set.add(num);
                minQue.add(num);
                maxQue.add(num);
            } 
            if (op.equals("D") && num == 1) {
                while (!maxQue.isEmpty()) {
                    int poll = maxQue.poll();
                    if (set.contains(poll)) {
                        set.remove(poll);
                        break;
                    }
                }
            } 
            if (op.equals("D") && num == -1) {
                while (!minQue.isEmpty()) {
                    int poll = minQue.poll();
                    if (set.contains(poll)) {
                        set.remove(poll);
                        break;
                    }
                }
            } 
        }
        
        int[] answer = new int[2];
        if (set.size() == 0) {
            return answer;
        }
        while (!maxQue.isEmpty()) {
            int poll = maxQue.poll();
            if (set.contains(poll)) {
                answer[0] = poll;
                break;
            }
        }
        while (!minQue.isEmpty()) {
            int poll = minQue.poll();
            if (set.contains(poll)) {
                answer[1] = poll;
                break;
            }
        }
        
        // 0, 0 또는 최댓값, 최솟값 반환
        return answer;
    }
}