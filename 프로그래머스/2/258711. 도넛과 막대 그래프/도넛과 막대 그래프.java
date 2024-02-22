import java.util.*;


class Solution {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, List<Integer>> reverseMap = new HashMap<>();

        public int[] solution(int[][] edges) {
            int max = 0;
            for (int i = 0; i < edges.length; i++) {
                max = Math.max(Math.max(max, edges[i][0]), edges[i][1]);

                List<Integer> list = map.getOrDefault(edges[i][0], new ArrayList<>());
                list.add(edges[i][1]);
                map.put(edges[i][0], list);

                List<Integer> reverseList = reverseMap.getOrDefault(edges[i][1], new ArrayList<>());
                reverseList.add(edges[i][0]);
                reverseMap.put(edges[i][1], reverseList);
            }

            for (int i = 1; i <= max; i++) {
                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }

                if (!reverseMap.containsKey(i)) {
                    reverseMap.put(i, new ArrayList<>());
                }
            }

            // 나가는게 2개 이상인 정점
            int addPoint = 0;
            int totalCnt = 0;
            int eightCnt = 0;
            for (Integer key : map.keySet()) {
                if (map.get(key).size() > 1) {
                    if (reverseMap.get(key).size() == 0) {
                        addPoint = key;
                        totalCnt = map.get(key).size();
                    } else {
                        eightCnt++;
                    }
                }
            }

            int barCnt = 0;
            for (Integer key : map.keySet()) {
                if (map.get(key).size() == 0) {
                    barCnt++;
                }
            }

            int donutCnt = totalCnt - eightCnt - barCnt;
            return new int[]{addPoint, donutCnt, barCnt, eightCnt};
        }
}