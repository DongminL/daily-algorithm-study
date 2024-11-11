class Solution {
    public int rob(int[] nums) {
        // 앞선 집들에서 가져올 수 있는 돈의 최대값을 누적함 (DP)
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                nums[i] = Math.max(nums[i-1], nums[i]);
            } else {
                nums[i] = Math.max(nums[i-1], nums[i] + nums[i-2]);
            }
        }
        
        // 최대로 털 수 있는 돈
        return nums[nums.length - 1];
    }
}