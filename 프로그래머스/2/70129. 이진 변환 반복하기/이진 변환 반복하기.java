class Solution {
    public int[] solution(String s) {
        // answer[0]: "1"이 될 때까지 이진 변환의 횟수
        // answer[1]: 변환 과정에서 제거된 모든 0의 개수
        int[] answer = new int[2];
        
        while (!s.equals("1")) {
            int previousLength = s.length();    // 0을 제거하기 전의 길이
            
            // 모든 0을 제거
            s = s.replace("0", "");
            answer[1] += previousLength - s.length();
            
            // s의 길이를 이진수로 변환
            s = Integer.toBinaryString(s.length());
            
            // 이진 변환 횟수 추가
            answer[0]++;
        }
        
        return answer;
    }
}