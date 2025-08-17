import java.util.*;

class Solution {
    
    List<String> visited;  // 탐색한 단어 목록
    char[] alphabet = {'A', 'E', 'I', 'O', 'U'};  // 단어의 알파벳 구성
    
    public int solution(String word) {
        visited = new ArrayList<>();
        
        int answer = searchOrder(word, "");
        
        return answer;
    }
    
    // 사전에서의 순번 찾기 (DFS)
    private int searchOrder(String targetWord, String curWord) {
        // 5글자까지만 탐색
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
                
            // 재귀 호출로 깊이 탐색
            int result = searchOrder(targetWord, nextWord);
            
            // 단어를 찾은 경우 즉시 반환
            if (result != -1) { 
                return result;
            }
        }

        return -1; // 끝내 단어를 찾지 못한 경우
    }
}