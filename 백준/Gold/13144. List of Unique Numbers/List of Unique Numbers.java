import java.io.*;
import java.util.*;

class Solution {

    long solution(int n, int[] nums) {
        // 수열에서 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수
        long answer = 0;

        // 중복 없는 수열의 구간 (Two Pointer)
        int start = 0;
        int end = 0;

        Set<Integer> checked = new HashSet<>(); // 수열에서 중복 검사용

        while (end < n) {
            // 연속된 수열에서 중복된 수가 없어질 때까지 시작 부분부터 제거
            while (checked.contains(nums[end])) {
                checked.remove(nums[start++]);
            }

            // 새로운 수를 수열에 추가
            checked.add(nums[end]);

            // 중복 없는 부분 수열의 개수 더하기
            answer += end - start + 1;

            // 다음 수 탐색
            end++;
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        System.out.println(new Solution().solution(n, nums));
    }
}