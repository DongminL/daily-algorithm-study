import java.util.*;

class Solution {
    
    List<String> visited;  // 탐색한 단어 목록
    char[] alphabet = {'A', 'E', 'I', 'O', 'U'};  // 단어의 알파벳 구성
    
    public int solution(String word) {
        visited = new ArrayList<>();
        
        // 사전에서의 순번 찾기
        int answer = dfs(word, "");
        
        return answer;
    }
    
    /* 해당 단어까지 사전에 있는 단어들 탐색 (DFS) (찾는 단어, 현재 단어) */
    private int dfs(String targetWord, String curWord) {
        // 5글자까지만 탐색가능하도록 설정
        if (curWord.length() > 4) {
            return -1;
        }
            
        // 다음 단어 탐색
        for (char a : alphabet) {
            // 다음 단어 추가
            String nextWord = curWord + a;
            visited.add(nextWord);
            
            // 찾는 단어와 같아질 때 종료
            if (nextWord.equals(targetWord)) {
                return visited.size();  // 이 단어가 마지막으로 추가되기 때문
            }
                
            // 재귀 호출의 반환값을 체크
            int result = dfs(targetWord, nextWord);
            
            // 단어를 찾은 경우 즉시 반환
            if (result != -1) { 
                return result;
            }
        }

        return -1; // 단어를 찾지 못한 경우
    }
}