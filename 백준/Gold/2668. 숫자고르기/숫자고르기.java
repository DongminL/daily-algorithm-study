import java.io.*;
import java.util.*;

class Solution {

    List<Integer> solution(int n, int[] nums) {
        // 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 
        // 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한 정수들
        List<Integer> answer = new ArrayList<>();

        boolean[] visited = new boolean[n + 1];

        // 첫째 줄에서 뽑기
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            
            // 뽑힌 정수 추가
            if (isCircle(i, i, visited, nums)) {
                answer.add(i);
            }
            
            visited[i] = false;
        }

        // 오름차순 정렬
        Collections.sort(answer);

        return answer;
    }

    // start -> end 까지 순환인지 여부 (순환되는 정수들이 위의 조건을 만족하는 집합이 될 수 있음)
    private boolean isCircle(int start, int end, boolean[] visited, int[] nums) {
        boolean result = false;

        // 첫 번째와 두 번재 줄의 값이 같아지면 탐색 종료
        if (end == nums[start]) {
            return true;
        }

        // 다음 번호로 순환되는지 탐색
        if (!visited[nums[start]]) {
            visited[nums[start]] = true;
            result = isCircle(nums[start], end, visited, nums);
            visited[nums[start]] = false;
        }

        return result;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> answer = new Solution().solution(n, nums);

        StringBuilder result = new StringBuilder();
        result.append(answer.size()).append("\n");
        answer.stream().forEach(e -> result.append(e).append("\n"));
        System.out.println(result);
    }
}