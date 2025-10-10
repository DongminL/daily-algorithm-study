import java.util.*;

class Solution {
    
    // n개의 원판을 3번 기둥으로 최소로 옮기는 방법
    private List<int[]> answer = new ArrayList<>(); 
    
    public int[][] solution(int n) {
        // 원판의 개수와 각 기둥의 번호 지정
        hanoi(n, 1, 2, 3);
        
        return answer.stream().toArray(int[][]::new);
    }
    
    private void hanoi(int n, int start, int mid, int end) {
        // 마지막 원판을 옮길 때, 기록
        if (n == 1) {
            answer.add(new int[] {start, end});
            return;
        }
        
        // (전체 원판의 개수 - 1)개를 start -> mid 기둥으로 옮김
        hanoi(n-1, start, end, mid);
        
        // 기둥에 남은 1개의 원판 기록
        answer.add(new int[] {start, end});
        
        // mid에 남아있는 원판(n-1개)을 end 기둥으로 옮김
        hanoi(n-1, mid, start, end);
    }
}