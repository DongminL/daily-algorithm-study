import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int n;    // 친구의 수
    int m;    // 친구 관계의 수
    int[][] relationCnt;    // 각 친구끼리의 얽힌 관계의 수

    /* 각 친구끼리의 관계의 수 구하기 (플루이드-워셜 알고리즘) */
    private void fw() {
        IntStream.range(1, n+1).forEach(k ->{
            IntStream.range(1, n+1).forEach(i ->{
                IntStream.range(1, n+1).forEach(j -> relationCnt[i][j] =
                        Math.min(relationCnt[i][k] + relationCnt[k][j], relationCnt[i][j]));
            });
        });
    }

    private void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 배열 초기화
        relationCnt = new int[n+1][n+1];
        IntStream.range(1, n+1).forEach(i -> {
            IntStream.range(1, n+1)
                    .filter(j -> i != j)
                    .forEach(j -> relationCnt[i][j] = 500001);    // 친구 관계의 수가 최대 5000이기 때문
        });

        // 친구 관계 추가
        IntStream.range(0, m).forEach(i -> {
            try {
                // 친구 관계 입력 받기
                st = new StringTokenizer(br.readLine());
                int f1 = Integer.parseInt(st.nextToken());
                int f2 = Integer.parseInt(st.nextToken());

                // 친구 관계 중복 방지 및 추가
                if (relationCnt[f1][f2] != 1) {
                    relationCnt[f1][f2] = 1;
                    relationCnt[f2][f1] = 1;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fw();    // 각 친구 관계의 수 찾기

        // 케빈 베이컨의 수 구하기
        int[] kevin = new int[n+1];    // 각 사람들의 케빈 베이컨의 수
        int[] result = new int[] {500001, -1};    // 0번째: 최소값, 1번째: 최소값을 갖는 사람
        IntStream.range(1, n+1).forEach(i -> {
            IntStream.range(1, n+1).filter(j -> relationCnt[i][j] != 500001).forEach(j -> kevin[i] += relationCnt[i][j]);

            // 최소값에 대한 정보 업데이트
            if (kevin[i] < result[0]) {
                result[0] = kevin[i];
                result[1] = i;
            }
        });

        System.out.println(result[1]);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}