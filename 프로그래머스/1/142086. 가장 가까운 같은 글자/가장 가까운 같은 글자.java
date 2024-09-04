class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()]; // 자신과 가장 가까운 같은 글자의 위치
        
        // 우선 -1값으로 초기화
        for (int i = 0; i < answer.length; i++) {
            answer[i] = -1;
        }
        
        // 앞에서 자신과 같은 글자 찾기
        for (int cur = s.length() - 1; cur > - 1; cur--) {
            for (int front = cur - 1; front >  -1; front--) {
                // 앞에서 자신과 같은 글자를 발견할 때
                if (s.charAt(cur) == s.charAt(front)) {
                    answer[cur] = cur - front;  // 몇 번째로 앞에 있는지 저장
                    break;
                }
            }
        }
        
        return answer;
    }
}