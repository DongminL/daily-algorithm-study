import java.util.*;

class Solution {
    
    Set<Integer> makedNumbers;  // 만들 수 있는 숫자 목록
    boolean[] isMaked;  // i자리 숫자를 만든 여부
    
    public int solution(String numbers) {
        int answer = 0; // 소수의 개수
        makedNumbers = new HashSet<>();
        isMaked = new boolean[numbers.length()];
        
        // 숫자 조합 구하기
        dfs(numbers, "", 0);
        
        // 만들 수 있는 숫자 중 소수의 개수 구하기
        for (int num : makedNumbers) {
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    /* 만들 수 있는 숫자 탐색 */
    private void dfs(String numbers, String num, int depth) {
        // 최대 길이일 때 빠져나오기
        if (depth > numbers.length()) {
            return;
        }
        
        // 자릿수를 증가시키면서 숫자 추가
        for (int i = 0; i < numbers.length(); i++) {
            if (!isMaked[i]) {
                isMaked[i] = true;
                
                makedNumbers.add(Integer.parseInt(num + numbers.charAt(i)));
                dfs(numbers, num + numbers.charAt(i), i+1);
                
                isMaked[i] = false; // 다음 숫자일 때 탐색 가능하도록
            }
        }
    }
    
    /* 소수 판별 */
    private boolean isPrime(int num) {
        // 0, 1 소수가 아님
        if (num <= 1) {
            return false;
        }
        
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            // 소수가 아닐 때
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}