import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        
        // 1. 광물들을 5개씩 나눠, 돌로 캤을 때의 피로도 계산
        List<Group> list = new ArrayList<>();
        int cnt = 0; // 곡괭이 개수
        for (int p : picks) {
            cnt += p;
        }
        
        for (int i = 0; i < minerals.length; i += 5) {
            
            if (i != 0 && i / 5 == cnt) {
                break;
            }
            int diaSum = 0;
            int ironSum = 0;
            int stoneSum = 0;
            
            for (int j = i; j < i + 5; j++) {
                if (j == minerals.length) break;
                if (minerals[j].equals("diamond")) {
                    diaSum += 1;
                    ironSum += 5;
                    stoneSum += 25;
                }
                if (minerals[j].equals("iron")) {
                    diaSum += 1;
                    ironSum += 1;
                    stoneSum += 5;
                }
                if (minerals[j].equals("stone")) {
                    diaSum += 1;
                    ironSum += 1;
                    stoneSum += 1;
                }
            }
            System.out.println(diaSum + ", " + ironSum + ", " + stoneSum);
            list.add(new Group(diaSum, ironSum, stoneSum));
        }
       
        // 2. 피로도가 가장 높은 집합부터 캐기. 이때 "다이아->철->돌" 순위로 곡괭이 선택
        Collections.sort(list);
        int answer = 0;
        
        for (Group g : list) {
            System.out.println(g.diaSum + ", " + g.ironSum + ", " + g.stoneSum);
            if (picks[0] > 0) {
                answer += g.diaSum;
                System.out.println("다이아로!");
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += g.ironSum;
                System.out.println("철로!");
                
                picks[1]--;
            } else if (picks[2] > 0) {
                answer += g.stoneSum;
                System.out.println("돌로!");
                
                picks[2]--;
            }  else {
                System.out.println("못캠!");
}
        }
        return answer;
    }
    
    private class Group implements Comparable<Group> {
        int diaSum;
        int ironSum;
        int stoneSum;
        
        public Group(int diaSUm, int ironSum, int stoneSum) {
            this.diaSum = diaSUm;
            this.ironSum = ironSum;
            this.stoneSum = stoneSum;
        }
        
        @Override
        public int compareTo(Group o) {
            return o.stoneSum - this.stoneSum; // 내림차순
        }
    }
}