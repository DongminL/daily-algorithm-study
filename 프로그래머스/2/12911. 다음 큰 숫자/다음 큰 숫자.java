class Solution {
    public int solution(int n) {
        int answer = n + 1; // 3가지 n의 다음 큰 숫자
        
        int bitCountN = Integer.bitCount(n);    // n의 1비트 개수
        int bitCountAnswer = Integer.bitCount(answer);    // n보다 큰 수의 1비트 개수
        
        // n + 1값부터 차례대로 1 비트 개수 비교
        while (bitCountN != bitCountAnswer) {
            answer += 1;
            bitCountAnswer = Integer.bitCount(answer);
        }
        
        return answer;
    }
}