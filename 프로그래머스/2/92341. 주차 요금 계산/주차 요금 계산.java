import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        // 입차한 차들 저장하는 map
        Map<Integer, Integer> recordMap = new HashMap<>();
        
        // 머무른 시간을 저장하는 map
        Map<Integer, Integer> timeMap = new HashMap<>();
        
        for (String str : records) {
            String timeStr = str.split(" ")[0];
            int time = Integer.parseInt(timeStr.split(":")[0]) * 60 + Integer.parseInt(timeStr.split(":")[1]);
            int carNum = Integer.parseInt(str.split(" ")[1]);
            String command = str.split(" ")[2];
            
            // 입차 -> map에 시간 저장
            if (command.equals("IN")) {
                recordMap.put(carNum, time);
            } 
            // 출차 -> map에서 입차 시간 빼서 머무른 시간 저장
            else {
                int total = time - recordMap.get(carNum);
                timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + total);
                recordMap.remove(carNum); // 출차한 차는 map에서 제거
            }
        }
        
        // map에 남은 차(출차 안 한 차) 시간 계산
        for (int carNum : recordMap.keySet()) {
            int total = 23 * 60 + 59 - recordMap.get(carNum);
            timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + total);
        }
        
        // 요금 계산하기
        List<Result> list = new ArrayList<>();
        
        for (int carNum : timeMap.keySet()) {
            int time = timeMap.get(carNum);
            
            // 기본 시간 이하 -> 기본 요금
            if (time <= fees[0]) {
                list.add(new Result(carNum, fees[1]));
            } 
            // 기본 시간 초과 -> 기본 요금 + 초과 시간에 대한 청구 (올림)
            else {
                int feeSum = fees[1];
                time -= fees[0];
                int num = time / fees[2];
                if (time % fees[2] != 0) num++;
                feeSum += num * fees[3];
                list.add(new Result(carNum, feeSum));
            }
        }
        
        // 청구할 주차 요금 (차량 번호가 작은 순서)
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).fee;
        }
        return answer;
    }
    
    public class Result implements Comparable<Result> {
        int carNum;
        int fee;
        
        public Result (int carNum, int fee) {
            this.carNum = carNum;
            this.fee = fee;
        }
        
        @Override
        public int compareTo(Result o) {
            return this.carNum - o.carNum;
        }
    }
}