class Solution {
    
    // N 사용 횟수의 최솟값
    int answer = 9; 
    
    public int solution(int N, int number) {
        express(N, 0, number, 0);
        
        return answer > 8 ? -1 : answer;
    }
    
    // N과 사칙연산으로 target을 표현 (DFS)
    private void express(int N, int cur, int target, int count) {
        // 최솟값 갱신이 안될 때
        if (count > 8) {
            return;
        }
        
        // number 값을 만들었을 때
        if (cur == target) {
            answer = Math.min(answer, count);
            return;
        }
        
        int nNum = 0;    // N으로만 구성된 n자리수
        for (int i = 1; i < 6; i++) {
            // 일의 자리에 N을 추가
            nNum = nNum * 10 + N;  
            
            // 덧셈
            express(N, cur + nNum, target, count + i);
            // 뺄셈
            express(N, cur - nNum, target, count + i);
            
            // cur 값이 0이면 곱셈과 나눗셈을 해도 의미 없음
            if (cur != 0) {
                // 곱셈
                express(N, cur * nNum, target, count + i);
                
                // 나눗셈
                express(N, cur / nNum, target, count + i);
                express(N, nNum / cur, target, count + i);
            } 
        }
    }
}