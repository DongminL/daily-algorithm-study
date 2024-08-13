import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n;    // 건물의 종류 수 (건물의 번호: 1 ~ n번)
    int[] times;    // 각 건물이 지어지는 데 걸리는 시간
    ArrayList<Integer>[] list;    // 건물이 지어질 순서 (인접 리스트)
    int[] frontBuilding;    // 각 건물보다 먼저 지어야할 건물의 개수 (진입 차수 배열)

    /* 각 건물이 지어지는 누적 시간 집계 */
    private int[] counting() {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] accumTimes = new int[n+1];    // 누적된 시간

        // 진입 차수가 0인 것부터 큐에 삽입
        IntStream.range(1, n+1)
                .filter(idx -> frontBuilding[idx] == 0)
                .forEach(queue::offerLast);

        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();    // 현재 지어진 빌딩

            // 다음에 지어질 빌딩 탐색 (next : 다음에 지어질 빌딩)
            list[cur].stream()
                    .peek(next -> {
                        frontBuilding[next]--;  // 앞서 지어야할 빌딩이 지어졌으므로 -1
                        accumTimes[next] = Math.max(accumTimes[next], accumTimes[cur] + times[cur]);  // 경과 시간 갱신 (만약 두개의 건물이 미리 지어져야할 경우, 더 오래 걸리는 시간을 기록하기 위함)
                    })
                    .filter(next -> frontBuilding[next] == 0)
                    .forEach(queue::offerLast);
        }

        return accumTimes;
    }

    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());

        // 배열 및 리스트 초기화
        times = new int[n+1];
        frontBuilding = new int[n+1];
        list = new ArrayList[n+1];
        IntStream.range(1, n+1).forEach(idx -> list[idx] = new ArrayList<>());

        // 건물이 지어지는 시간과 순서 입력 받음
        IntStream.range(1, n+1).forEach(cur -> {
            try {
                String[] schedule = br.readLine().split(" ");

                // 건물을 만드는데 걸리는 시간
                int time = Integer.parseInt(schedule[0]);
                times[cur] = time;

                // 건물이 지어지는 순서 구성
                Arrays.stream(schedule)
                        .skip(1)    // 첫 문자열(0번째 인덱스) 스킵
                        .limit(schedule.length - 2)    // (총 개수 - 2)번만 반복, 처음과 마지막 문자열 조회하지 않음
                        .map(Integer::parseInt)
                        .forEach(pre -> {    // 먼저 지어야할 건물들 순서 추가
                            list[pre].add(cur);
                            frontBuilding[cur]++;
                        });
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        });

        int[] accumTimes = counting();  // 각 건물이 지어지는 누적 시간 집계

        // 결과 출력
        IntStream.range(1, n+1)
                .map(idx -> times[idx] + accumTimes[idx])
                .forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}