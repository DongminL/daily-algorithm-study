import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int d, int k, int c, int[] belt) {
        int answer = 0; // 초밥의 가짓수의 최댓값

        // 먹은 스시 (key: 종류, value: 먹은 개수)
        Map<Integer, Integer> eatenCount = new HashMap<>();
        for (int i = 0; i < k; i++) {
            eatenCount.put(
                belt[i],
                eatenCount.getOrDefault(belt[i], 0) + 1
            );
        }
        answer = updateCount(answer, c, eatenCount);

        for (int start = 1; start < n; start++) {
            int end = start + k - 1;

            // 이전 종류의 개수 조정
            if (eatenCount.get(belt[start - 1]) - 1 == 0) {
                eatenCount.remove(belt[start - 1]);
            } else {
                eatenCount.put(
                    belt[start - 1],
                    eatenCount.getOrDefault(belt[start - 1], 0) - 1
                );
            }

            // 새로 먹은 스시 종류의 개수 추가
            eatenCount.put(
                belt[end % n],
                eatenCount.getOrDefault(belt[end % n], 0) + 1
            );

            answer = updateCount(answer, c, eatenCount);
        }

        return answer;
    }

    // 쿠폰 적용 후 먹은 스시 종류의 개수 갱신
    private int updateCount(int answer, int c, Map<Integer, Integer> eatenCount) {
        if (eatenCount.containsKey(c)) {
            // 이미 쿠폰에 적힌 종류의 초밥을 먹은 경우
            answer = Math.max(answer, eatenCount.size());
        } else {
            // 쿠폰에 적힌 종류의 초밥을 먹지 않은 경우
            answer = Math.max(answer, eatenCount.size() + 1);
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int[] belt = new int[inputInfo[0]];
        for (int i = 0; i < belt.length; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(new Solution().solution(
            inputInfo[0], inputInfo[1], inputInfo[2], inputInfo[3], belt
        ));
    }
}