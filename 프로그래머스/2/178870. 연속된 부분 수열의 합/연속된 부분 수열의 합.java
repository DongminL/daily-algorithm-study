class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, sequence.length - 1};  // 수열의 합이 k인, 가장 짧은 부분 수열의 시작과 끝 인덱스
        
        int left = 0;   // 시작 포인터 (인덱스)
        int sum = 0;    // 수열의 합
        
        // 조건을 만족하는 가장 짧은 부분 수열 찾기
        for (int right = 0; right < sequence.length; right++) {
            sum += sequence[right];
            
            while (k < sum) {
                sum -= sequence[left];
                left++;
            }
            
            if (k == sum) {
                // 짧은 부분 수열로 갱신
                if (answer[1] - answer[0] > right - left) {
                    answer[0] = left;
                    answer[1] = right;
                }
            }
        }
        
        return answer;
    }
}