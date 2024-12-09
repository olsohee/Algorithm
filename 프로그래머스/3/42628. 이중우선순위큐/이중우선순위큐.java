import java.util.*;

class Solution {
    
    Queue<Integer> maxQue = new PriorityQueue<>((o1, o2) -> o2 - o1);
    Queue<Integer> minQue = new PriorityQueue<>();
    Map<Integer, Integer> map = new HashMap<>();
    
    public int[] solution(String[] operations) {
        
        for (String operation : operations) {
            String[] arr = operation.split(" ");
            String command = arr[0];
            if (command.equals("I")) {
                // maxQue, minQue, map에 넣기
                int num = Integer.parseInt(arr[1]);
                maxQue.add(num);
                minQue.add(num);
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            if (command.equals("D") && arr[1].equals("1")) {
                // 최댓값 빼기
                while (!maxQue.isEmpty()) {
                    int num = maxQue.poll();
                    if (map.get(num) > 0) {
                        map.put(num, map.get(num) - 1);
                        break;
                    }
                }
            }
            if (command.equals("D") && arr[1].equals("-1")) {
                // 최솟값 빼기
                while (!minQue.isEmpty()) {
                    int num = minQue.poll();
                    if (map.get(num) > 0) {
                        map.put(num, map.get(num) - 1);
                        break;
                    }
                }
            }
        }
        List<Integer>  list = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) > 0) {
                list.add(key);
            }
        }
        
        if (list.size() == 0) {
            return new int[]{0, 0};
        }
        if (list.size() == 1) {
            return new int[]{list.get(0), list.get(0)};
        }
        int max = 0;
        while (!maxQue.isEmpty()) {
            int num = maxQue.poll();
            if (map.get(num) > 0) {
                max = num;
                break;
            }
        }
        int min = 0;
        while (!minQue.isEmpty()) {
            int num = minQue.poll();
            if (map.get(num) > 0) {
                min = num;
                break;
            }
        }
        return new int[]{max, min};
    }
}