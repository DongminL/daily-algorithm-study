import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // 어떤 번호가 다른 번호의 접두어인 여부
        boolean answer = true;
        
        // 오름차순 정렬
        Arrays.sort(phone_book);
        
        // 다음 문자열의 접두어가 현재 문자열로 시작하는지 검사
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}