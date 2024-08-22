import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder(); // 출력할 최종 결과

    int n;    // 도시의 수
    int m;    // 버스의 수
    int[][] minimumCost;    // 각 도시들까지의 최소 비용 (int[시작 도시][도착 도시])

    /* 모든 도시들 간의 최소 비용 구하기 (플로이드-워셜 알고리즘) */
    private void fw() {
        IntStream.range(1, n+1).forEach(k -> {
            IntStream.range(1, n+1).forEach(start -> {
                IntStream.range(1, n+1).forEach(end -> minimumCost[start][end] =
                        Math.min(minimumCost[start][k] + minimumCost[k][end], minimumCost[start][end]));
            });
        });
    }

    private void solution() throws IOException {
        // 1~2번째 줄 입력 받기
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 배열 및 리스트 초기화
        minimumCost = new int[n+1][n+1];
        IntStream.range(1, n+1).forEach(i -> {
            IntStream.range(1, n+1)
                    .filter(j -> i != j)
                    .forEach(j -> minimumCost[i][j] = 10000001);  // 주어진 비용의 최대값은 100000이기 때문에 그보다 높은 수로 설정
        });

        // 버스 정보 입력받기
        IntStream.range(0, m).forEach(i -> {
            try {
                // 0번째: 출발 도시, 1번째: 도착 도시, 2번째: 버스 비용
                int[] info = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

                // 도시간 연결 관계 추가 (같은 노선의 버스가 있을 수 있기 때문에 비교)
                minimumCost[info[0]][info[1]] = Math.min(info[2], minimumCost[info[0]][info[1]]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fw();   // 모든 도시들 간의 최소 비용 탐색

        // 최종 결과 출력
        IntStream.range(1, n+1).forEach(start -> {
            Arrays.stream(minimumCost[start])
                    .skip(1)    // 0번째 인덱스 스킵
                    .forEach(cost -> {
                if (cost == 10000001) {
                    result.append(0 + " ");
                } else {
                    result.append(cost + " ");
                }
            });
            result.append("\n");
        });
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}