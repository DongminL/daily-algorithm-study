import java.io.*;
import java.util.*;

class Solution {

    public int solution(int n, int[] nums) {
        int answer = 0; // 좋은 수의 개수

        // 주어진 수열을 오름차순 정렬
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            int left = 0;   // 가장 작은 값의 인덱스
            int right = n - 1;  // 가장 큰 값의 인덱스

            // Binary Search
            while (left < right) {
                // 다른 수로 i 번째 값을 만들어야 하기 때문에 둘 중 같은 수면 패스
                if (i == left) {
                    left++;
                    continue;
                } else if (i == right) {
                    right--;
                    continue;
                }

                // 좋다 수일 때
                if (nums[left] + nums[right] == nums[i]) {
                    answer++;
                    break;
                }

                // 좋다 수가 아닐 때 인덱스 이동
                if (nums[left] + nums[right] < nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 수의 개수
        int[] A = Arrays.stream(br.readLine().split(" "))   //  수열 A
            .mapToInt(Integer::parseInt).toArray();

        System.out.println(new Solution().solution(n, A));
    }
}