import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        
        // 키: 필터 조건, 값: 점수 리스트
        Map<String, List<Integer>> map = new HashMap<>();
        for (String s : info) {
            String[] data = s.split(" ");
            String[] languages = new String[]{data[0], "-"}; 
            String[] jobs = new String[]{data[1], "-"}; 
            String[] exps = new String[]{data[2], "-"}; 
            String[] foods = new String[]{data[3], "-"}; 
            int score = Integer.parseInt(data[4]);
            for (String language : languages) {
                for (String job : jobs) {
                    for (String exp : exps) {
                        for (String food : foods) {
                            String key = language + job + exp + food;
                            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
                            list.add(score);
                            map.put(key, list);
                        }
                    }
                }
            }
        }
        
        // 결과 계산
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] data = query[i].split(" ");
            String key = data[0] + data[2] + data[4] + data[6];
            int score = Integer.parseInt(data[7]);
            
            if (map.containsKey(key)) {
                List<Integer> scores = map.get(key); // 필터링된 사람들의 점수들
                answer[i] = findCount(scores, score);
            } else {
                answer[i] = 0;
            }
        
        }
        
        return answer; // 문의조건에 해당하는 사람들의 숫자 
    }
    
    private int findCount(List<Integer> scores, int standard) {
        Collections.sort(scores);
        int start = 0;
        int end = scores.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (scores.get(mid) >= standard) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return scores.size() - start;
    }
}