import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Info>> map = new HashMap<>();
        Map<String, Integer> playSum = new HashMap<>();
        List<Rank> rankList = new ArrayList<>();
        
        int n = genres.length;
        for (int i = 0; i < n; i++) {
            playSum.put(genres[i], playSum.getOrDefault(genres[i], 0) + plays[i]);
            
            List<Info> list = map.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Info(plays[i], i));
            map.put(genres[i], list);
        }
        
        for (String genre : playSum.keySet()) {
            rankList.add(new Rank(genre, playSum.get(genre)));
        }
        Collections.sort(rankList);
        
        List<Integer> answerList = new ArrayList<>();
        
        for (Rank rank : rankList) {
            int addCnt = 0;
            Collections.sort(map.get(rank.genre));
            for (Info info : map.get(rank.genre)) {
                if (addCnt >= 2) break;
                answerList.add(info.idx);
                addCnt++;
            }
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    class Rank implements Comparable<Rank> {
        
        String genre;
        int playSum;
        
        public Rank(String genre, int playSum) {
            this.genre = genre;
            this.playSum = playSum;
        }
        
        @Override
        public int compareTo (Rank o) {
            return o.playSum - this.playSum;
        }
    }
    
    class Info implements Comparable<Info> {
        
        int cnt;
        int idx;
        
        public Info (int cnt, int idx) {
            this.cnt = cnt;
            this.idx = idx;
        }
        
        @Override
        public int compareTo (Info o) {
            if (o.cnt == this.cnt) {
                return this.idx - o.idx;
            }
            return o.cnt - this.cnt;
        }
    }
}