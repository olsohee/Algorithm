import java.util.*;
class Solution {
    public int solution(String[][] clothes) {

            // 의상 정보 Map에 저장
            Map<String, List<String>> map = new HashMap<>();
            for (String[] clothe : clothes) {
                List<String> list = map.getOrDefault(clothe[1], new ArrayList<>());
                list.add(clothe[0]);
                map.put(clothe[1], list);
            }

            // 각 분류의 의상을 1개 골라서 입는 경우 OR 안 입는 경우 모두 더하기 
            int answer = 1;
            for (String key : map.keySet()) {
                int size = map.get(key).size();
                answer *= size + 1;
            }
            
            // 모든 의상을 안 입는 경우 빼기
            return answer - 1;
        }
//     public int solution(String[][] clothes) {

//         Map<String, Integer> map = new HashMap<>();

//         for(int i = 0; i < clothes.length; i++) {
//             String[] clothe = clothes[i];
//             map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
//         }

//         int result = 1;
//         for(String key : map.keySet()) {
//             result *= map.get(key) + 1;
//         }

//         return result - 1;
//     }
}