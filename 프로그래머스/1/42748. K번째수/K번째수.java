import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        
        IntStream.range(0, commands.length).forEach(i -> {
            // startIdx ~ endIdx - 1 인덱스를 복사
            int startIdx = commands[i][0] - 1;
            int endIdx = commands[i][1];
            int[] temp = Arrays.copyOfRange(array, startIdx, endIdx);
            
            // 정렬
            Arrays.sort(temp);
            
            // k번째에 있는 수 추가
            answer.add(temp[commands[i][2] - 1]);
        });
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}