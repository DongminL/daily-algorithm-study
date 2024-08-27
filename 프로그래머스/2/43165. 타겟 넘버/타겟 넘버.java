class Solution {
    
    int answer = 0; // 타겟 넘버를 만드는 방법의 수
    
    public int solution(int[] numbers, int target) {
        
        dfs(0, 0, numbers, target);
        
        return answer;
    }
    
    /* 타겟 넘버 구하기 (DFS) */
    private void dfs(int depth, int sum, int[] numbers, int target) {
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            
            return;
        }
        
        dfs(depth + 1, sum + numbers[depth], numbers, target);  // 해당 depth의 값과 더할 경우
        dfs(depth + 1, sum - numbers[depth], numbers, target);  // 해당 depth의 값과 뺄 경우
    }
}