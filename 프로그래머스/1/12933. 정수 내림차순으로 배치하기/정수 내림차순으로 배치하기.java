import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        
        char[] charNums = String.valueOf(n).toCharArray();
        Arrays.sort(charNums);
        
        answer = Long.parseLong(
            new StringBuilder(String.valueOf(charNums))
                .reverse()
                .toString()
        );
        
        return answer;
    }
}