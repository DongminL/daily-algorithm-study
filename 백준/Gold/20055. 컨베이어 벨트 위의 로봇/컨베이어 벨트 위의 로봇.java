import java.io.*;
import java.util.*;
import java.util.stream.*;

class Solution {

    int solution(int n, int k, int[] durability) {
        int answer = 0; // 종료될 때 진행 중이던 단계

        // 전체 컨베이어 벨트 위치별 내구도
        LinkedList<Integer> beltDurability = Arrays.stream(durability)
            .boxed()
            .collect(Collectors.toCollection(LinkedList::new));
        // 컨베이어 벨트 위의 위치별 로봇 유무
        LinkedList<Boolean> robotOnBelt = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            robotOnBelt.offerFirst(Boolean.FALSE);
        }

        while (true) {
            answer++;

            // 1단계: 벨트 한 칸 회전
            rotate(beltDurability);
            moveDown(robotOnBelt);
            rotate(robotOnBelt);
            moveDown(robotOnBelt);

            // 2단계: 로봇 이동
            for (int i = n - 2; i > -1; i--) {
                if (beltDurability.get(i + 1) > 0 && robotOnBelt.get(i) && !robotOnBelt.get(i + 1)) {
                    // 로봇 한 칸 이동
                    robotOnBelt.set(i, Boolean.FALSE);
                    robotOnBelt.set(i + 1, Boolean.TRUE);

                    // 내구도 -1
                    beltDurability.set(i + 1, beltDurability.get(i + 1) - 1);
                }
                moveDown(robotOnBelt);
            }

            // 3단계: 로봇 올리기
            if (!robotOnBelt.peek() && beltDurability.peek() > 0) {
                moveUp(robotOnBelt);

                // 내구도 -1
                beltDurability.offerFirst(beltDurability.pollFirst() - 1);
            }

            // 4단계: 과정 종료
            long zeroCount = beltDurability.stream().filter(e -> e == 0).count();
            if (k <= zeroCount) {
                break;
            }
        }

        return answer;
    }

    // 한 칸 회전
    private <T> void rotate(LinkedList<T> list) {
        if (list.isEmpty()) {
            return;
        }
        list.offerFirst(list.pollLast());
    }

    // 로봇 올리기
    private void moveUp(LinkedList<Boolean> robotOnBelt) {
        if (robotOnBelt.isEmpty()) {
            return;
        }
        robotOnBelt.set(0, Boolean.TRUE);
    }

    // 로봇 내리기 (로봇이 내리는 위치에 도달하면 그 즉시 내린다)
    private void moveDown(LinkedList<Boolean> robotOnBelt) {
        if (robotOnBelt.isEmpty()) {
            return;
        }
        robotOnBelt.set(robotOnBelt.size() - 1, Boolean.FALSE);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine());
        int[] durability = toIntArray(br.readLine());

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], durability));
    }

    private static int[] toIntArray(String inputStr) {
        return Arrays.stream(inputStr.split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}