import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int[][] skylinePoints) {
        // 스카이라인만 보고, 세워진 건물의 최소 개수
        int answer = 0;

        // 이전 건물의 높이 저장
        Deque<Integer> stack = new ArrayDeque<>();

        for (int[] skylinePoint : skylinePoints) {
            int curHeight = skylinePoint[1];    // 현재 높이

            // 현재 높이보다 높은 지점에 대해서 건물로 판단
            while (!stack.isEmpty() && curHeight < stack.peek()) {
                stack.pop();
                answer++;
            }

            // 더 높으면 다른 건물이기 때문에 스택에 push (스택이 비어있으면 0보다 큰 것만 저장)
            if ((stack.isEmpty() || curHeight > stack.peek()) && curHeight > 0) {
                stack.push(curHeight);
            }
        }

        // 남아있는 건물 처리
        answer += stack.size();

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] skylinePoints = new int[n][2];
        for (int i = 0; i < n; i++) {
            skylinePoints[i] = toIntArray(br.readLine().split(" "));
        }

        System.out.println(new Solution().solution(n, skylinePoints));
    }

    private static int[] toIntArray(String[] strArray) {
        return Arrays.stream(strArray)
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}