import java.io.*;
import java.util.*;

class Solution {

    int[] solution(int n, int[] values) {
        // 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값
        int[] answer = new int[2];

        int minValue = Integer.MAX_VALUE;   // 혼합한 용액의 최소 특성값 (절댓값)

        int left = 0;
        int right = n - 1;

        // 0에 가깝게 되는 두 용액 찾기
        while (left < right) {
            int mixedValue = values[left] + values[right];  // 혼합한 용액의 특성값

            // 최소값 갱신
            if (Math.abs(mixedValue) < minValue) {
                minValue = Math.abs(mixedValue);

                answer[0] = values[left];
                answer[1] = values[right];

                if (minValue == 0) {
                    break;
                }
            }

            // 농도에 따라 용액 변경
            if (mixedValue > 0) {
                right--;
            } else {
                left++;
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] values = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int[] answer = new Solution().solution(n, values);
        System.out.println(answer[0] + " " + answer[1]);
    }
}