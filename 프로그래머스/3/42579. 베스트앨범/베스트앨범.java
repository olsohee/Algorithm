import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, List<Info>> genreAndPlay = new HashMap<>();
        Map<String, Integer> playSum = new HashMap<>();
        List<Rank> rankList = new ArrayList<>();
        
        int n = genres.length;
        for (int i = 0; i < n; i++) {
            List<Info> list = genreAndPlay.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Info(plays[i], i));
            genreAndPlay.put(genres[i], list);
            
            playSum.put(genres[i], playSum.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 재생 횟수가 높은 순으로 장르 정렬
        for (String genre : playSum.keySet()) {
            rankList.add(new Rank(genre, playSum.get(genre)));
        }
        Collections.sort(rankList);
        
        // 각 장르 내에서 재생 횟수가 높은 노래 2개 선정
        List<Integer> answer = new ArrayList<>();
        for (Rank rank : rankList) {
            int addCnt = 0;
            Collections.sort(genreAndPlay.get(rank.genre));
            for (Info info : genreAndPlay.get(rank.genre)) {
                if (addCnt >= 2) break;
                answer.add(info.idx);
                addCnt++;
            }
        }
        
        return answer.stream()
            .mapToInt(num -> num)
            .toArray();
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