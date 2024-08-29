import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] answers) {
        Set<Integer> result = new HashSet<>();
        
        // 찍는 패턴
        int[] pattern1 = {1, 2, 3, 4, 5};
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        // 각 수포자가 맞춘 문제의 개수
        int[] answerCount = new int[4];
        
        // 정답 체크
        IntStream.range(0, answers.length).forEach(i -> {
            int answer = answers[i];    // i번째 문제의 정답
            
            if (pattern1[i % 5] == answer) {
                answerCount[1]++;
            }
            
            if (pattern2[i % 8] == answer) {
                answerCount[2]++;
            }
            
            if (pattern3[i % 10] == answer) {
                answerCount[3]++;
            }
        });
        
        int max = Arrays.stream(answerCount).max().orElse(0);   // 가장 많이 맞춘 문제의 개수
        
        // 가장 많이 맞춘 사람 추가
        IntStream.range(1, answerCount.length)
            .filter(i -> answerCount[i] == max)
            .forEach(i -> result.add(i));
        
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}