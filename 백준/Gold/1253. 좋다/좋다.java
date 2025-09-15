import java.io.*;
import java.util.*;

class Solution {

    private final int n;
    private final int[] nums;

    public Solution(int n, int[] nums) {
        this.n = n;
        this.nums = nums;
    }

    int solution() {
        // 좋은 수의 개수
        int answer = 0;
        
        // 수열을 오름차순으로 정렬
        Arrays.sort(nums);

        // i번째 수가 좋은 수인지 탐색
        for (int i = 0; i < n; i++) {
            if (isGoodNumber(i)) {
                answer++;
            }
        }

        return answer;
    }

    // 해당 위치의 숫자가 좋은 수인지 확인
    private boolean isGoodNumber(int index) {
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            // 더하는 수가 만드려는 수가 같은 경우, 다른 위치의 수로 변경
            if (index == left) {
                left++;
                continue;
            }
            if (index == right) {
                right--;
                continue;
            }

            int sum = nums[left] + nums[right];

            // 좋은 수 일 때
            if (sum == nums[index]) {
                return true;
            }

            // 좋은 수가 아닐 때
            if (sum < nums[index]) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        System.out.println(new Solution(n, nums).solution());
    }
}