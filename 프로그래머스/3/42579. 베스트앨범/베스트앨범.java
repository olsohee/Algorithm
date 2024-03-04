import java.util.*;


class Solution {

        public int[] solution(String[] genres, int[] plays) {

            Map<String, List<Music>> map = new HashMap();
            Map<String, Integer> cntMap = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                // map
                List<Music> list = map.getOrDefault(genres[i], new ArrayList<>());
                list.add(new Music(plays[i], i));
                map.put(genres[i], list);

                // cntMap
                cntMap.put(genres[i], cntMap.getOrDefault(genres[i], 0) + plays[i]);
            }

            // 총 재생횟수가 높은 순서대로 장르 정렬
            List<Genre> genresList = new ArrayList<>();
            for (String genre : cntMap.keySet()) {
                genresList.add(new Genre(genre, cntMap.get(genre)));
            }
            Collections.sort(genresList);

            // 각 장르별로 2개씩 고르기
            List<Integer> answerList = new ArrayList<>();
            for (Genre genre : genresList) {
                List<Music> list = map.get(genre.genre);
                Collections.sort(list); // 정렬
                answerList.add(list.get(0).num);
                if (list.size() > 1) {
                    answerList.add(list.get(1).num);
                }
            }

            int[] answer = new int[answerList.size()];
            for (int i = 0; i < answerList.size(); i++) {
                answer[i] = answerList.get(i);
            }
            return answer;
        }
    

    class Music implements Comparable<Music> {
        int play;
        int num;

        public Music(int play, int num) {
            this.play = play;
            this.num = num;
        }

        @Override
        public int compareTo(Music o) {
            // 같으면 고유번호 작은 순서대로
            if (this.play == o.play) {
                return this.num - o.num;
            }
            // 재생횟수 큰 순서대로
            return o.play - this.play;
        }
    }

    class Genre implements Comparable<Genre> {
        String genre;
        int playSum;

        public Genre(String genre, int playSum) {
            this.genre = genre;
            this.playSum = playSum;
        }

        @Override
        public int compareTo(Genre o) {
            return o.playSum - this.playSum;
        }
    }

//     public int[] solution(String[] genres, int[] plays) {

//         // MusicInfo 만들어서 map에 넣기
//         Map<String, List<MusicInfo>> map = new HashMap<>();

//         for (int i = 0; i < plays.length; i++) {
//             MusicInfo musicInfo = new MusicInfo(genres[i], i, plays[i]);
//             if(map.get(musicInfo.genre) == null) {
//                 ArrayList<MusicInfo> list = new ArrayList<>();
//                 list.add(musicInfo);
//                 map.put(musicInfo.genre, list);
//             } else {
//                 List<MusicInfo> list = map.get(musicInfo.genre);
//                 list.add(musicInfo);
//                 map.put(musicInfo.genre, list);
//             }
//         }
// //        System.out.println(map);

//         // 각 map에 접근해서, GenreInfo 만들기
//         ArrayList<GenreInfo> genreInfos = new ArrayList<>();
//         for (String key : map.keySet()) {
//             List<MusicInfo> list = map.get(key);
//             // 최댓값 2개
//             Collections.sort(list);
//             int[] arr;
//             if(list.size() == 1) {
//                 arr = new int[]{list.get(0).idx};
//             } else{
//                 arr = new int[]{list.get(0).idx, list.get(1).idx};
//             }

//             // 합계
//             int totalPlayCount = 0;
//             for (MusicInfo musicInfo : list) {
//                 totalPlayCount += musicInfo.playCount;
//             }

//             genreInfos.add(new GenreInfo(totalPlayCount, arr));
//         }

//         // GenreInfo 리스트 (playCount) 순으로 내림차순 정렬 후, idx 꺼내기
//         Collections.sort(genreInfos);
//         ArrayList<Integer> answerList = new ArrayList<>();
//         for(int i = 0; i < genreInfos.size(); i++) {
//             int[] arr = genreInfos.get(i).idx;
//             for (int ar : arr) {
//                 answerList.add(ar);
//             }
//         }

//         int[] answer = new int[answerList.size()];
//         for(int i = 0; i < answerList.size(); i++) {
//             answer[i] = answerList.get(i);
//         }

//         return answer;
//     }

//     class GenreInfo implements Comparable<GenreInfo> {
//         int playCount;
//         int[] idx;

//         public GenreInfo(int playCount, int[] idx) {
//             this.playCount = playCount;
//             this.idx = idx;
//         }

//         @Override
//         public int compareTo(GenreInfo o) {
//             return o.playCount - this.playCount;
//         }
//     }

//     class MusicInfo implements Comparable<MusicInfo> {
//         String genre;
//         int idx;
//         int playCount;

//         public MusicInfo(String genre, int idx, int playCount) {
//             this.genre = genre;
//             this.idx = idx;
//             this.playCount = playCount;
//         }

//         @Override
//         public int compareTo(MusicInfo o) {
//             return o.playCount - this.playCount;
//         }
//     }
}
