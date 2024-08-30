import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        
        // int[] -> String[]
        String[] stringNumbers = Arrays.stream(numbers)
                                    .mapToObj(String::valueOf)
                                    .toArray(String[]::new);
        
        // 내림차순 정렬 ([30, 3]을  [3, 30]으로 정렬 되게 만들기)
        Arrays.sort(stringNumbers, (n1, n2) -> (n2 + n1).compareTo(n1 + n2));
        
        // 0밖에 없을 때
        if (stringNumbers[0].equals("0")) {
            return "0";
        }
        
        // 정렬된 10의 배수가 아닌 숫자 문자열을 이어 붙이기
        Arrays.stream(stringNumbers).forEach(answer::append);
        
        return answer.toString();
    }
}