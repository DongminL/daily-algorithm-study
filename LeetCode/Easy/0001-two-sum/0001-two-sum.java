import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Key : nums[index], value : index
        Map<Integer, Integer> map = new HashMap<>();
        IntStream.range(0, nums.length).forEach(i -> map.put(nums[i], i));

        // 두 수의 합이 target과 같은 인덱스 찾기 (target - nums[i] = remainder)
        for (int i = 0; i < nums.length; i++) {
            Integer remainderIdx = map.get(target - nums[i]);  // target - nums[i] 값의 인덱스

            // remainder 값이 존재할 때
            if (remainderIdx != null && remainderIdx != i)
                return new int[] { i, remainderIdx };
        }

        return null;
    }
}