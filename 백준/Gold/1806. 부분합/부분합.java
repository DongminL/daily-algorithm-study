import java.io.*;
import java.util.*;

class Solution {

    public int solution(int n, int s, int[] nums) {
        int answer = Integer.MAX_VALUE;

        // Two Pointer
        int left = 0;
        int right = 0;

        int sum = 0;

        while (right <= n) {
            if (s <= sum) {
                answer = Math.min(answer, right - left);

                sum -= nums[left++];
            } else {
                if (right < n) {
                    sum += nums[right];
                }
                right++;
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = parseIntArray(br.readLine());
        int[] nums = parseIntArray(br.readLine());

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], nums));
    }

    private static int[] parseIntArray(String strNum) {
        return Arrays.stream(strNum.split(" "))
            .mapToInt(Integer::parseInt).toArray();
    }
}