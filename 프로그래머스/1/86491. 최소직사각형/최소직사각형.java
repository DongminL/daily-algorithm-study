import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        List<Integer> biggerList = new ArrayList<>();   // 가로와 높이 중 더 긴 쪽의 길이들
        List<Integer> shorterList = new ArrayList<>();   // 가로와 높이 중 더 짧은 쪽의 길이들
        
        for (int i = 0; i < sizes.length; i++) {
            int max = IntStream.of(sizes[i]).max().orElse(0); // 둘 중 큰 길이
            int min = IntStream.of(sizes[i]).min().orElse(0); // 둘 중 짧은 길이
            
            biggerList.add(max);
            shorterList.add(min);
        }
        
        // 긴쪽 중에서 최대 값과 짧은 쪽 중에서 최대값을 곱함
        int biggerMax = biggerList.stream().max(Comparator.naturalOrder()).orElse(0);
        int shorterMax = shorterList.stream().max(Comparator.naturalOrder()).orElse(0);
        answer = biggerMax * shorterMax ;
        
        return answer;
    }
}