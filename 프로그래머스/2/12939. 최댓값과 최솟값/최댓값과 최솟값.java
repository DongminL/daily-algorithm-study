import java.util.*;

class Solution {
    public String solution(String s) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (String num : s.split(" ")) {
            min = Math.min(min, Integer.parseInt(num));
            max = Math.max(max, Integer.parseInt(num));
        }
        
        return min + " " + max;
    }
}