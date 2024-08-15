import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // int[] -> HashSet
        HashSet<Integer> set = Arrays.stream(nums)
                .boxed() // int -> Integer
                .collect(Collectors.toCollection(HashSet::new)); // Integer[] -> HashSet

        int max = 0; // 최대로 연속된 수열의 길이

        for (int num : set) {
            // num으로 시작하는 연속된 수열 찾기
            if (!set.contains(num - 1)) {
                int cur = num;  // 현재 시작값
                int length = 1; // 해당 연속된 수열의 길이

                // 다음 연속값을 찾음
                while (set.contains(cur + 1)) {
                    cur++;
                    length++;
                }

                max = Math.max(max, length); // 최대 길이 갱신
            }
        }

        return max;
    }
}