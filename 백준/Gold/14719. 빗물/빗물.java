import java.io.*;
import java.util.*;

class Solution {

    int solution(int h, int w, int[] heights) {
        // 고이는 빗물의 총량
        int answer = 0;

        // 처음과 마지막은 물이 고일 수 없음
        for (int i = 1; i < w - 1; i++) {
            int leftMaxHeight = 0;  // 현재 위치 기준으로, 왼쪽에서 가장 높은 벽
            int rightMaxHeight = 0; // 현재 위치 기준으로, 왼쪽에서 가장 높은 벽

            // 왼쪽에서 가장 높은 벽 구하기
            for (int j = 0; j < i; j++) {
                leftMaxHeight = Math.max(leftMaxHeight, heights[j]);
            }

            // 오른쪽에서 가장 높은 벽 구하기
            for (int j = i + 1; j < w; j++) {
                rightMaxHeight = Math.max(rightMaxHeight, heights[j]);
            }

            // 물이 고일 수 있는 경우
            if (heights[i] < leftMaxHeight && heights[i] < rightMaxHeight) {
                // 현재 위치에서 고인 물의 양 추가
                answer += Math.min(leftMaxHeight, rightMaxHeight) - heights[i];
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine().split(" "));
        int[] heights = toIntArray(br.readLine().split(" "));

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], heights));
    }

    private static int[] toIntArray(String[] array) {
        return Arrays.stream(array)
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}