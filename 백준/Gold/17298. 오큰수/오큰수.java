import java.io.*;
import java.util.*;

class Solution {

    public int[] solution(int n, int[] nums) {
        int[] answer = new int[n];  // 입력 받은 수열의 오큰수 수열
        answer[n - 1] = -1; // 마지막 수는 무조건 오큰수가 없음 (-1)

        if (nums.length == 1) {
            return answer;
        }

        Deque<Integer> stack = new ArrayDeque<>();  // 오큰수를 구했으면 스택에 push

        stack.push(nums[n - 1]);
        for (int i = n - 2; i > -1; i--) {
            // 오른쪽에 있는 수 중에서 현재 수보다 작은 수는 스택에서 pop
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }

            // 오큰수가 없는 경우
            if (stack.isEmpty()) {
                answer[i] = -1;
                stack.push(nums[i]);
                continue;
            }

            // 오큰수가 있는 경우
            if (nums[i] < stack.peek()) {
                answer[i] = stack.peek();
                stack.push(nums[i]);
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 수열 A의 크기
        int[] A = Arrays.stream(br.readLine().split(" "))   // 수열 A
            .mapToInt(Integer::parseInt).toArray();

        StringBuilder result = new StringBuilder();
        Arrays.stream(new Solution().solution(n, A))
            .forEach(num -> result.append(num).append(" "));
        System.out.print(result);
    }
}