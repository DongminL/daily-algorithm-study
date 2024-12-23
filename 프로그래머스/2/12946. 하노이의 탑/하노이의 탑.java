import java.util.*;

class Solution {
    
    private List<int[]> answer; // n개의 원판을 3번 기둥으로 최소로 옮기는 방법
    
    public int[][] solution(int n) {
        answer = new ArrayList<>();
        
        // 원판의 개수와 각 기둥의 번호 지정
        hanoi(n, 1, 2, 3);
        
        return answer.stream().toArray(int[][]::new);
    }
    
    private void hanoi(int movingDiskCount, int start, int mid, int end) {
        // 원판을 옮길 때 마지막 번째 때 기록
        if (movingDiskCount == 1) {
            answer.add(new int[] {start, end});
            return;
        }
        
        // (전체 원판의 개수 - 1)개를 start -> mid 기둥으로 옮김
        hanoi(movingDiskCount-1, start, end, mid);
        
        // 기둥에 하나의 원판만 남았을 경우
        answer.add(new int[] {start, end});
        
        // 남아있는 원판을 end 기둥으로 옮김
        hanoi(movingDiskCount-1, mid, start, end);
    }
}