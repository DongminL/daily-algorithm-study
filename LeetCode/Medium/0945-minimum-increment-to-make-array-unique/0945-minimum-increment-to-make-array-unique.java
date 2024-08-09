import java.util.*;


class Solution {
    public int minIncrementForUnique(int[] nums) {
        // 가장 큰 값 찾기
        int max = Arrays.stream(nums).max().getAsInt();

        // 배열의 값의 개수 세기
        int[] count = new int[max + nums.length];
        for (int num : nums) {
            count[num]++;
        }

        // 고유한 숫자로 만들기 위한 최소 이동 횟수
        int moves = 0;  // 최소 이동 횟수
        for (int i = 0; i < count.length; i++) {
            // 중복된 값일 때
            if (count[i] > 1) {
                // 중복된 수를 1 증가 후 개수 1 감소
                int excess = count[i] - 1;  
                count[i+1] += excess;
                moves += excess;    // 이동 시킨 숫자의 개수 누적
            }
        }
        
        return moves;
    }
}