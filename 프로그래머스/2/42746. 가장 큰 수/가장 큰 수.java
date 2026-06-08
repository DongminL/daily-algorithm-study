import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // 순서를 재배치해서 만들 수 있는 가장 큰 수
        StringBuilder answer = new StringBuilder();
        
        // int[] -> String[]
        String[] strNumbers = Arrays.stream(numbers)
                                    .mapToObj(String::valueOf)
                                    .toArray(String[]::new);
        
        // 내림차순 정렬 ([30, 3] -> [3, 30]으로 정렬)
        Arrays.sort(strNumbers, (n1, n2) -> (n2 + n1).compareTo(n1 + n2));
        
        // 0밖에 없을 때
        if (strNumbers[0].equals("0")) {
            return "0";
        }
        
        // 숫자 이어 붙이기
        Arrays.stream(strNumbers).forEach(answer::append);
        
        return answer.toString();
    }
}