import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int k, int[] nums) {
        // K개 이하로 포함한 최장 연속 부분 수열의 길이
        int answer = 0;

        // Two Pointer
        int start = 0;
        int end = 0;

        Map<Integer, Integer> count = new HashMap<>();

        while (end < n) {
            // 원소 개수 누적
            count.put(nums[end], count.getOrDefault(nums[end], 0) + 1);

            // 중복된 숫자 K개 이하로 유지
            while (k < count.get(nums[end])) {
                count.put(nums[start], count.getOrDefault(nums[start], 0) - 1);
                start++;
            }

            end++;
            answer = Math.max(answer, end - start); // 최장 길이 갱신
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine());
        int[] nums = toIntArray(br.readLine());

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], nums));
    }

    private static int[] toIntArray(String inputStr) {
        return Arrays.stream(inputStr.split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}