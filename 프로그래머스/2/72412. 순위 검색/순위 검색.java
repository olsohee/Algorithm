import java.util.*;
class Solution {

    Map<String, ArrayList<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {

        List<Integer> answerList = new ArrayList<>();

        // info를 보고 map에 저장 (키, 점수)
        for (String i : info) {
            String[] data = i.split(" ");
            String[] languages = {data[0], "-"};
            String[] jobs = {data[1], "-"};
            String[] exps = {data[2], "-"};
            String[] foods = {data[3], "-"};
            int score = Integer.parseInt(data[4]);
            for (String language : languages) {
                for (String job : jobs) {
                    for (String exp : exps) {
                        for (String food : foods) {
                            String[] keyData = {language, job, exp, food};
                            String key = String.join(" ", keyData);
                            ArrayList<Integer> list = map.getOrDefault(key, new ArrayList<>());
                            list.add(score);
                            map.put(key, list);
                        }
                    }
                }
            }
        }

        // 이분탐색을 위해 각 value 값을 정렬
        for(ArrayList<Integer> arr : map.values()) {
            Collections.sort(arr);
        }


        // 해당 key의 값 가져오기
        for (String q : query) {
            // key 만들기
            String[] data = q.split(" and ");
            String[] split = data[3].split(" ");
            data[3] = split[0];
            int score = Integer.parseInt(split[1]);

            String key = String.join(" ", data);

            if(map.containsKey(key)) {
                ArrayList<Integer> list = map.get(key);
                // 이진탐색으로 요구하는 점수보다 높은 애들만
                int leftIdx = 0;
                int rightIdx = list.size();
                while (leftIdx < rightIdx) {
                    int mid = (leftIdx + rightIdx) / 2;
                    if(list.get(mid) >= score) {
                        rightIdx = mid;
                    } else {
                        leftIdx = mid + 1;
                    }
                }
                answerList.add(list.size() - leftIdx);
            } else {
                answerList.add(0);
            }

        }

        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
