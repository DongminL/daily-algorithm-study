import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        // 정렬 후, k 번째 수 모음
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            // startIdx ~ endIdx - 1 인덱스를 복사
            int startIdx = commands[i][0] - 1;
            int endIdx = commands[i][1];
            int[] temp = Arrays.copyOfRange(array, startIdx, endIdx);
            
            // 정렬
            Arrays.sort(temp);
            
            // k번째에 있는 수 추가
            answer[i] = temp[commands[i][2] - 1];
        }
        
        return answer;
    }
}