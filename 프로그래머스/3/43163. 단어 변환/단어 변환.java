import java.util.*;

class Solution {
    
    int answer; // begin에서 target까지 거치는 최소 횟수
    boolean[] visited;  // words 배열에서 해당 인덱스의 방문 여부
    
    public int solution(String begin, String target, String[] words) { 
        // words에 target이 없는 경우
        if (!contains(target, words)) {
            return 0;   // begin에서 target으로 변환 불가
        }
        
        answer = 51; ;
        visited = new boolean[words.length];
        
        // begin -> target으로 만들며 변경된 횟수 세기
        dfs(begin, target, words, 0);
        
        return answer;
    }
    
    /* begin과 target이 같아질 때까지 탐색 (DFS) */
    private void dfs(String begin, String target, String[] words, int count) {
        // 두 단어가 같을 때, 최소 횟수 갱신
        if (begin.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }
        
        // words의 단어들을 한번씩 탐색하며 변화하기
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && changeable(begin, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, count + 1);
                visited[i] = false;
            }
        }
    }
    
    /* String[]에 target 문자열이 있는지 확인 */
    private boolean contains(String target, String[] words) {
        return Arrays.stream(words)
            .anyMatch(word -> word.equals(target));
    }
    
    /* 두 단어가 한 글자를 제외한 나머지 글자가 같은지 확인 */
    private boolean changeable(String begin, String word) {
        int count = 0;  // 다른 글자의 개수
        
        // 두 단어의 같은 자리의 글자 비교
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != word.charAt(i)) {
                count++;
            }
        }
        
        return count == 1;
    }
}