import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        Map<Integer, Integer> timeMap = new HashMap<>();
        Map<Integer, Integer> inTimeMap = new HashMap<>();
        Map<Integer, Integer> feeMap = new HashMap<>();

        for (String record : records) {
            String[] arr = record.split(" ");
            int time = Integer.parseInt(arr[0].split(":")[0]) * 60 + Integer.parseInt(arr[0].split(":")[1]);
            int carNum = Integer.parseInt(arr[1]);

            if (arr[2].equals("IN")) {
                inTimeMap.put(carNum, time);
            }

            if (arr[2].equals("OUT")) {
                int inTime = inTimeMap.get(carNum);
                timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + time - inTime);
                                inTimeMap.remove(carNum);

            }
        }

        // 출차 안한 경우 (23:59 출차로 간주)
        for (int carNum : inTimeMap.keySet()) {
            int inTime = inTimeMap.get(carNum);
            timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + 23 * 60 + 59 - inTime);
        }

        int basicTime = fees[0];
        int basicFee = fees[1];
        int additionalTime = fees[2];
        int additionalFee = fees[3];

     
        for (int carNum : timeMap.keySet()) {
            int totalTime = timeMap.get(carNum);
            System.out.println(carNum);
            System.out.println(timeMap.get(carNum));
            System.out.println();
            if (totalTime > basicTime) {
                int fee = basicFee + (int)Math.ceil((double)(totalTime - basicTime) / additionalTime) * additionalFee;
                feeMap.put(carNum, fee);
            } else {
                feeMap.put(carNum, basicFee);
            }
        }

        // 차번호대로 올림
        List<Integer> carNumList = new ArrayList<>();
        for (int carNum : feeMap.keySet()) {
            carNumList.add(carNum);
        }
        Collections.sort(carNumList);

        int[] answer = new int[carNumList.size()];
        for (int i = 0; i < carNumList.size(); i++) {
            answer[i] = feeMap.get(carNumList.get(i));
        }
        return answer;
    }
}
