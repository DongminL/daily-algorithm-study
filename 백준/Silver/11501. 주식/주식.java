import java.io.*;
import java.util.*;
import java.util.stream.*;

class Solution {

    long[] solution(int t, List<Integer>[] tests) {
        // 테스트케이스 별, 최대 이익
        long[] answer = new long[t];

        for (int i = 0; i < t; i++) {
            long total = 0;  // 전체 수익
            int maxPrice = 0;   // 특정 날짜 이후의 최대 주가

            // 최근 순으로 탐색
            for (int current = tests[i].size() - 1; current > -1; current--) {
                // 최대 주가일 때 팔기
                if (tests[i].get(current) < maxPrice) {
                    total += maxPrice - tests[i].get(current);

                } else {
                    // 최대 주가 갱신
                    maxPrice = tests[i].get(current);
                }
            }

            answer[i] = total;
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        List<Integer>[] tests = new ArrayList[t];
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            tests[i] = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        }

        Arrays.stream(new Solution().solution(t, tests))
            .forEach(System.out::println);
    }
}