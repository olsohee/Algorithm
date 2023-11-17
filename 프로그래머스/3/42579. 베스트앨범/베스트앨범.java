import java.util.*;


class Solution {
    public int[] solution(String[] genres, int[] plays) {

        // MusicInfo 만들어서 map에 넣기
        Map<String, List<MusicInfo>> map = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            MusicInfo musicInfo = new MusicInfo(genres[i], i, plays[i]);
            if(map.get(musicInfo.genre) == null) {
                ArrayList<MusicInfo> list = new ArrayList<>();
                list.add(musicInfo);
                map.put(musicInfo.genre, list);
            } else {
                List<MusicInfo> list = map.get(musicInfo.genre);
                list.add(musicInfo);
                map.put(musicInfo.genre, list);
            }
        }
//        System.out.println(map);

        // 각 map에 접근해서, GenreInfo 만들기
        ArrayList<GenreInfo> genreInfos = new ArrayList<>();
        for (String key : map.keySet()) {
            List<MusicInfo> list = map.get(key);
            // 최댓값 2개
            Collections.sort(list);
            int[] arr;
            if(list.size() == 1) {
                arr = new int[]{list.get(0).idx};
            } else{
                arr = new int[]{list.get(0).idx, list.get(1).idx};
            }

            // 합계
            int totalPlayCount = 0;
            for (MusicInfo musicInfo : list) {
                totalPlayCount += musicInfo.playCount;
            }

            genreInfos.add(new GenreInfo(totalPlayCount, arr));
        }

        // GenreInfo 리스트 (playCount) 순으로 내림차순 정렬 후, idx 꺼내기
        Collections.sort(genreInfos);
        ArrayList<Integer> answerList = new ArrayList<>();
        for(int i = 0; i < genreInfos.size(); i++) {
            int[] arr = genreInfos.get(i).idx;
            for (int ar : arr) {
                answerList.add(ar);
            }
        }

        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    class GenreInfo implements Comparable<GenreInfo> {
        int playCount;
        int[] idx;

        public GenreInfo(int playCount, int[] idx) {
            this.playCount = playCount;
            this.idx = idx;
        }

        @Override
        public int compareTo(GenreInfo o) {
            return o.playCount - this.playCount;
        }
    }

    class MusicInfo implements Comparable<MusicInfo> {
        String genre;
        int idx;
        int playCount;

        public MusicInfo(String genre, int idx, int playCount) {
            this.genre = genre;
            this.idx = idx;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(MusicInfo o) {
            return o.playCount - this.playCount;
        }
    }
}
