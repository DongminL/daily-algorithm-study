import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int[] budgets, int targetBudget) {
        int answer = 0; // 배정된 예산들 중 최대값

        int start = 1;
        int end = Arrays.stream(budgets).max().getAsInt();

        // 이분 탐색으로 targetBudget을 달성하는 최대 예산 구하기
        while (start <= end) {
            int mid = (start + end) / 2;
            long total = sumBudgets(budgets, mid);

            if (total <= targetBudget) {
                start = mid + 1;
                answer = Math.max(answer, mid);

            } else {
                // 예산 초과
                end = mid - 1;
            }
        }

        return answer;
    }

    // 각 예산의 상한액 설정에 따른 전체 예산
    long sumBudgets(int[] budgets, int max) {
        return Arrays.stream(budgets)
            .mapToLong(budget -> Math.min(budget, max))
            .sum();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] budgets = parseIntArray(br.readLine());
        int targetBudget = Integer.parseInt(br.readLine());

        System.out.println(new Solution().solution(n, budgets, targetBudget));
    }

    private static int[] parseIntArray(String strNum) {
        return Arrays.stream(strNum.split(" "))
            .mapToInt(Integer::parseInt).toArray();
    }
}