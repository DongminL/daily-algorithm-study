import java.util.*;

class Solution {
    public int missingNumber(int[] nums) {
        // nums에 있는 숫자 확인
        boolean[] check = new boolean[nums.length + 1];
        Arrays.stream(nums).forEach(num -> check[num] = true);

        // 없는 숫자 리턴
        for (int i = 0; i <= nums.length; i++) {
            if (!check[i]) {
                return i;
            }
        }

        return -1;
    }
}