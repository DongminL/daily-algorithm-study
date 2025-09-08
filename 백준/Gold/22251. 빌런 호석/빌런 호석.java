import java.io.*;
import java.util.*;

class Solution {

    // LED로 표현하는 숫자들
    private boolean[][] ledNums = {
        { true, true, true, false, true, true, true },    // 0
        { false, false, true, false, false, true, false },    // 1
        { false, true, true, true, true, false, true },    // 2
        { false, true, true, true, false, true, true },    // 3
        { true, false, true, true, false, true, false },    // 4
        { true, true, false, true, false, true, true },    // 5
        { true, true, false, true, true, true, true },    // 6
        { false, true, true, false, false, true, false },    // 7
        { true, true, true, true, true, true, true },    // 8
        { true, true, true, true, false, true, true }    // 9
    };

    int solution(int n, int k, int p, int x) {
        // 엘리베이터 LED를 올바르게 반전시킬 수 있는 경우의 수
        int answer = 0;

        int[] xLed = numberToLed(k, x);
        for (int i = 1; i <= n; i++) {
            // 같은 수는 건너띄기
            if (i == x) {
                continue;
            }

            if (canReverse(p, xLed, numberToLed(k, i))) {
                answer++;
            }
        }

        return answer;
    }

    // target 숫자로 반전시킬 수 있는 여부
    private boolean canReverse(int p, int[] xLed, int[] targetLed) {
        int diffCount = 0;  // LED 위치가 다른 개수

        for (int i = 0; i < xLed.length; i++) {
            // 같은 자리에 같은 숫자면 건너띄기
            if (xLed[i] == targetLed[i]) {
                continue;
            }

            for (int j = 0; j < 7; j++) {
                if (ledNums[xLed[i]][j] != ledNums[targetLed[i]][j]) {
                    diffCount++;

                    // 최대로 반전시킬 개수를 초과할 때, 반전 불가
                    if (diffCount > p) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // 일반적인 숫자를, 길이가 K인 LED 표현에 맞게 변경 (11 -> 0011)
    private int[] numberToLed(int k, int number) {
        int[] result = new int[k];

        for (int i = k - 1; i > -1; i--) {
            result[i] = number % 10;
            number /= 10;

            if (number == 0) {
                break;
            }
        }

        return result;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        System.out.println(
            new Solution().solution(inputInfo[0], inputInfo[1], inputInfo[2], inputInfo[3])
        );
    }
}