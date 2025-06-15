import java.io.*;
import java.util.*;

class Solution {

    int[] solution(int n, int x, int[] daily) {
        int maxVisitor = -1; // 가장 많은 방문자 수
        int periodCount = -1;    // 가장 많은 방문자 수가 나온 기간의 개수

        // 방문자가 아예 없는 경우
        if (isZero(daily)) {
            return new int[] { maxVisitor, periodCount };
        }

        int[] sumArr = new int[n - x + 1];  // 누적 합 배열
        for (int i = 0; i < x; i++) {
            sumArr[0] += daily[i];
        }
        maxVisitor = sumArr[0];
        periodCount = 1;

        // 두 번째 기간부터 누적 합으로 계산
        for (int now = 1; now < n - x + 1; now++) {
            int previous = now - 1;
            sumArr[now] = sumArr[previous] - daily[previous] + daily[previous + x];

            // 최대 방문자 수와 같은 경우
            if (maxVisitor == sumArr[now]) {
                periodCount++;
            }

            // 더 큰 방문자 수가 나온 경우
            if (maxVisitor < sumArr[now]) {
                maxVisitor = sumArr[now];
                periodCount = 1;
            }
        }

        return new int[] { maxVisitor, periodCount };
    }

    private boolean isZero(int[] array) {
        return Arrays.stream(array).allMatch(v -> v == 0);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] info = toIntArray(br.readLine().split(" "));
        int[] daily = toIntArray(br.readLine().split(" "));

        int[] result = new Solution().solution(info[0], info[1], daily);
        for (int value : result) {
            if (value == -1) {
                System.out.println("SAD");
                return;
            }
            System.out.println(value);
        }
    }

    private static int[] toIntArray(String[] strArr) {
        return Arrays.stream(strArr)
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}