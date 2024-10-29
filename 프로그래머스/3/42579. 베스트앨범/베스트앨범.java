import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 베스트 앨범에 들어갈 노래의 고유 번호들
        List<Integer> answer = new ArrayList<>();
        
        // 장르별 재생 횟수
        Map<String, Integer> genreCount = new HashMap<>();
        // 장르별 노래들
        Map<String, List<Song>> genreSongs = new HashMap<>();
        
        for (int i = 0; i < plays.length; i++) {
            // 장르별로 노래 추가
            List<Song> songs = genreSongs.getOrDefault(genres[i], new ArrayList<>());
            songs.add(new Song(i, plays[i]));
            genreSongs.put(genres[i], songs);
            
            // 장르별 재생 횟수 추가
            genreCount.put(genres[i], genreCount.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        while (genreCount.size() > 0) {
            String bestGenre = searchBestGenre(genreCount);
            List<Song> songs = genreSongs.get(bestGenre);
            
            //
            if (songs.size() < 2) {
                answer.add(songs.get(0).idx);
                
            } else {
                // 해당 장르의 노래들을 재생 횟수를 기준으로 내림차순으로 정렬 (같으면 idx를 기준으로 오름차순)
                Collections.sort(songs, (s1, s2) -> {
                    if (s2.playCount == s1.playCount) {
                        return s1.idx - s2.idx;
                    }
                    
                    return s2.playCount - s1.playCount;
                });
            
                // 장르 내에서 많이 재생된 수록곡 2개 추가
                for (int i = 0; i < 2; i++) {
                    answer.add(songs.get(i).idx);
                }
            }
            
            // 베스트 앨범에 추가한 장르는 제외
            genreCount.remove(bestGenre);
            genreSongs.remove(bestGenre);
        }
        
        // List -> int[]
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    /* 가장 많이 재생된 장르 찾기 */
    private String searchBestGenre(Map<String, Integer> genreCount) {
        return genreCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .get();
    }
}

/* 노래에 대한 정보 */
class Song {
    
    int idx;  // 고유 번호
    int playCount;  // 재생 횟수
    
    public Song(int idx, int playCount) {
        this.idx = idx;
        this.playCount = playCount;
    }
}