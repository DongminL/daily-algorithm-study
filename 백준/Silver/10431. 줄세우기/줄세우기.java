import java.io.*;
import java.util.*;

class Solution {

    int[][] solution(int n, int[][] heights) {
        // 테스트 케이스별 학생들이 뒤로 물러난 걸음 수의 총합
        int[][] answer = new int[n + 1][1];

        for (int i = 1; i <= n; i++) {
            bubbleSort(answer[i], heights[i]);
        }

        return answer;
    }

    private void bubbleSort(int[] count, int[] arr) {
        for (int i = arr.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;

                    count[0]++;
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 테스트 케이스 개수
        int[][] heights = new int[n + 1][20];   // 테스트 케이스별 키 배열

        for (int i = 0; i < n; i++) {
            int[] inputData = toIntArray(br.readLine());
            heights[inputData[0]] = Arrays.stream(inputData).skip(1).toArray();
        }

        StringBuilder result = new StringBuilder();
        int[][] answer = new Solution().solution(n, heights);
        for (int i = 1; i <= n; i++) {
            result.append(i).append(" ").append(answer[i][0]).append("\n");
        }
        System.out.println(result);
    }

    private static int[] toIntArray(String inputStr) {
        return Arrays.stream(inputStr.split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}