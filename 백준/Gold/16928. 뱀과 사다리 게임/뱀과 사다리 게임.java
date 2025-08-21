import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int m, Map<Integer, Integer> ladders, Map<Integer, Integer> snakes) {
        // 100번 칸에 도착하기 위해 주사위를 굴리는 최소 횟수
        int answer = 0;

        boolean[] visited = new boolean[101];
        Queue<PlayInfo> queue = new ArrayDeque<>();

        // 초기값 설정
        queue.offer(new PlayInfo(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            PlayInfo current = queue.poll();

            // 100번 칸에 먼저 도달한 것이 주사위를 최소로 던진 횟수
            if (current.point == 100) {
                answer = current.count;
                break;
            }

            // 주사위를 던지는 경우
            for (int i = 1; i <= 6; i++) {
                int nextPoint = current.point + i;

                // 보드판을 넘거나 같은 칸에 재방문은 하지 않는다.
                if (nextPoint > 100 || visited[nextPoint]) {
                    continue;
                }

                visited[nextPoint] = true;
                if (ladders.containsKey(nextPoint)) {
                    // 사다리를 타는 경우
                    queue.offer(new PlayInfo(ladders.get(nextPoint), current.count + 1));
                    visited[ladders.get(nextPoint)] = true;

                } else if (snakes.containsKey(nextPoint)) {
                    // 뱀을 타는 경우
                    queue.offer(new PlayInfo(snakes.get(nextPoint), current.count + 1));
                    visited[snakes.get(nextPoint)] = true;

                } else {
                    queue.offer(new PlayInfo(nextPoint, current.count + 1));
                }
            }
        }

        return answer;
    }

    static class PlayInfo {

        int point;  // 위치
        int count;  // 주사위를 던진 횟수

        public PlayInfo(int point, int count) {
            this.point = point;
            this.count = count;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine());

        // 사다리의 정보 (key: x, value: y)
        Map<Integer, Integer> ladders = new HashMap<>();
        for (int i = 0; i < inputInfo[0]; i++) {
            int[] ladder = toIntArray(br.readLine());
            ladders.put(ladder[0], ladder[1]);
        }

        // 뱀의 정보 (key: u, value: v)
        Map<Integer, Integer> snakes = new HashMap<>();
        for (int i = 0; i < inputInfo[1]; i++) {
            int[] snake = toIntArray(br.readLine());
            snakes.put(snake[0], snake[1]);
        }

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], ladders, snakes));
    }

    private static int[] toIntArray(String inputStr) {
        return Arrays.stream(inputStr.split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}