import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // map 만들기(차번호, 입출차 분)
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (String record : records) {
            String time = record.split(" ")[0];
            int minute = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);

            int carNumber = Integer.parseInt(record.split(" ")[1]);

            List<Integer> list = map.getOrDefault(carNumber, new ArrayList<>());
            list.add(minute);
            map.put(carNumber, list);
        }

        // 각 차번호별로 낼 돈 계산하고 List<Result>에 담기
        List<Result> resultList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            List<Integer> list = map.get(key);
            int time  = 0;
            int fee = 0;

            // 시간 계산
            for(int i = 0; i < list.size(); i += 2) {
                Integer inMinute = list.get(i);
                Integer outMinute;
                if(i + 1 == list.size()) {
                    // 출차하지 않은 경우
                    outMinute = (23 * 60 + 59);
                } else {
                    outMinute = list.get(i + 1);
                }

                time += outMinute - inMinute;
            }

            // 요금 계산
            fee = fees[1];
            if (time > fees[0]) {
                fee += Math.ceil((time - fees[0]) / (double) fees[2]) * fees[3];
            }
            resultList.add(new Result(key, fee));
        }

        //결과 리스트를 차량 번호 작은 순으로 나열
        Collections.sort(resultList);

        int[] answer = new int[resultList.size()];
        for(int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i).fee;
        }

        return answer;
    }

    class Result implements Comparable<Result> {
        int carNumber;
        int fee;

        public Result(int carNumber, int fee) {
            this.carNumber = carNumber;
            this.fee = fee;
        }

        @Override
        public int compareTo(Result o) {
            return this.carNumber - o.carNumber;
        }
    }
}
