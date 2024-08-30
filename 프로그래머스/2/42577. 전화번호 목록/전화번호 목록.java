import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);    // 오름차순 정렬
        
        // 다음 문자열의 접두어가 현재 문자열로 시작하는지 검사
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}