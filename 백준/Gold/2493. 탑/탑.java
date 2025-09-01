import java.io.*;
import java.util.*;

class Solution {

    int[] solution(int n, int[] tops) {
        // 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호 (1번부터 시작)
        int[] answer = new int[n];

        // 탑의 인덱스 번호를 스택에 저장
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int curNum = 1; curNum < n; curNum++) {
            int curHeight = tops[curNum];

            while (!stack.isEmpty()) {
                // 수신한 탑의 번호 기록
                if (curHeight <= tops[stack.peek()]) {
                    answer[curNum] = stack.peek() + 1;
                    break;
                }

                // 현재 탑보다 작은 탑은 앞으로 수신 못하기 때문에 스택에서 제거
                stack.pop();
            }

            stack.push(curNum);
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] tops = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        StringBuilder result = new StringBuilder();
        Arrays.stream(new Solution().solution(n, tops))
            .forEach(e -> result.append(e).append(" "));
        System.out.println(result);
    }
}