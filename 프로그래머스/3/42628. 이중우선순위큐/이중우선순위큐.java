import java.util.*;

class Solution {
    
    // 큰 거 우선인 que1
    Queue<Integer> que1 = new PriorityQueue<>((o1, o2) -> {
        return o2 - o1;
    });
    
    // 작은 거 우선인 que2
    Queue<Integer> que2 = new PriorityQueue<>();
        
    // 큐에 공통으로 저장된 수를 기록하는 set
    Set<Integer> set = new HashSet<>();
    
    public int[] solution(String[] operations) {
        
        for (String op : operations) {
            String o = op.split(" ")[0];
            // 삽입
            if (o.equals("I")) {
                addNum(Integer.parseInt(op.split(" ")[1]));
            }
            
            if (o.equals("D")) {
                // 최댓값 제거
                if (op.split(" ")[1].equals("1")) {
                    removeMax();
                } 
                // 최솟값 제거
                else {
                    removeMin();
                }
            }
        }
        
        if (set.size() == 0) {
            return new int[] {0, 0};
        }
        
        if (set.size() == 1) {
            int result = 0;
            for (int num : set) {
                result = num;
            }
            return new int[] {result, result};
        }
        
        int max = 0;
        int min = 0;
        while (!que1.isEmpty()) {
            int num = que1.poll();
            if (set.contains(num)) {
                max = num;
                break;
            }
        }
        while (!que2.isEmpty()) {
            int num = que2.poll();
            if (set.contains(num)) {
                min = num;
                break;
            }
        }
        return new int[] {max, min};
    }
    
    // 삽입: que1, que2, set에 삽입
    public void addNum(int num) {
        que1.add(num);
        que2.add(num);
        set.add(num);
    }
    
    // 최댓값 삭제: que1, set에서 삭제
    public void removeMax() {
        if (set.size() > 0) {
            while (!que1.isEmpty()) {
                int num = que1.poll();
                if (set.contains(num)) {
                    set.remove(num);
                    break;
                }
            }
        }
    }
    
    // 최솟값 삭제: que2, set에서 삭제
    public void removeMin() {
        if (set.size() > 0) {
            while (!que2.isEmpty()) {
                int num = que2.poll();
                if (set.contains(num)) {
                    set.remove(num);
                    break;
                }
            }
        }
    }
}