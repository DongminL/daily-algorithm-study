import java.io.*;
import java.util.*;

class Solution {

    int[] solution(int n, int[] concentration) {
        // 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값
        int[] answer = new int[2];

        // 가장 0에 가까운 농도
        int min = Integer.MAX_VALUE;

        // Two Pointer
        int left = 0;
        int right = n - 1;

        // 0에 가장 가깝게 만드는 두 용액 찾기
        while (left < right) {
            int mixed = concentration[left] + concentration[right];

            // 섞었을 때 0에 가까운 두 용액 갱신
            if (Math.abs(mixed) < min) {
                min = Math.abs(mixed);

                answer[0] = concentration[left];
                answer[1] = concentration[right];

                // 0인 용액을 만들었다면 탐색 종료
                if (min == 0) {
                    break;
                }
            }

            // 0에 가까워지게 용액 교체
            if (mixed < 0) {
                left++;
            } else {
                right--;
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] concentration = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int[] answer = new Solution().solution(n, concentration);
        System.out.println(answer[0] + " " + answer[1]);
    }
}