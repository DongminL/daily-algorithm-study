import java.io.*;
import java.util.*;

class Solution {

    int[] solution(int n, int[] heightInfo) {
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int biggerCount = 0;  // 왼쪽에 키 큰 사람의 수

            for (int j = 0; j < n; j++) {
                // 키가 (i + 1)인 사람 왼쪽에 주어진 정보대로 본인보다 큰 사람이 있는 경우
                if (biggerCount == heightInfo[i] && answer[j] == 0) {
                    answer[j] = i + 1;
                    break;
                }

                // 0이라면 (i + 1)보다 더 큰 사람이 올 자리
                if (answer[j] == 0) {
                    biggerCount++;
                }
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] heightInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        Arrays.stream(new Solution().solution(n, heightInfo))
            .forEach(e -> System.out.print(e + " "));
    }
}