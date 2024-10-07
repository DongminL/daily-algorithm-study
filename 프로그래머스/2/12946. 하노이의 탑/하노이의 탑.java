import java.util.*;

class Solution {
    
    List<int[]> answer;
    
    public int[][] solution(int n) {
        answer = new ArrayList<>();
        
        hanoi(n, 1, 2, 3);
        
        return answer.stream().toArray(int[][]::new);
    }
    
    /* 
        하노이의 탑
        start: 출발지 / mid: 원판을 옮기기 위한 중간 다리 역할 / end: 도착지
        초기값 -> start: 1, mid: 2, end: 3
    */
    private void hanoi(int n, int start, int mid, int end) {
        // 1개의 원판만 옮길 때, 1 -> 3으로 옮김
        if (n == 1) {
            answer.add(new int[] {start, end});
            return;
        }
        
        // 맨 밑에 원판을 옮기기 위해 n-1개의 원판을 1 -> 2로 옮길 때
        hanoi(n-1, start, end, mid);
        
        // 맨 밑에 원판을 1 -> 3로 옮길 때
        answer.add(new int[] {start, end});
        
        // n-1개의 원판을 2 -> 3으로 옮길 때
        hanoi(n-1, mid, start, end);
    }
}