import java.util.*;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];

        int allMul = 1; // 모든 수의 곱
        int removeZero = 1; // 0을 제외한 수의 곱
        int zeroCount = 0;  // 0의 개수

        // 0의 개수와 총 곱을 구함
        for (int num : nums) {
            allMul *= num;

            if (num == 0) {
                zeroCount++;
                continue;
            }

            removeZero *= num;
        }

        // 0이 2개 이상이면 결과값은 모두 0
        if (zeroCount > 1) {
            return answer;
        }

        for (int i = 0; i < nums.length; i++) {
            // i번째가 0일 때 
            if (allMul == 0 && nums[i] == 0) {
                answer[i] = removeZero;
                continue;
            }
            // i번째의 수를 구함
            answer[i] = allMul / nums[i];
        }

        return answer;
    }
}