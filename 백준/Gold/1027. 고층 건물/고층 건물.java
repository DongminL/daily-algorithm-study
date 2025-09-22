import java.io.*;

class Solution {

    int solution(int n, int[] heights) {
        // 가장 많은 고층 빌딩이 보이는 빌딩에서 보이는 빌딩의 수
        int answer = 0;

        for (int a = 1; a <= n; a++) {
            int count = 0;  // 해당 빌딩에서 보이는 다른 빌딩 수

            double preSlope = 1_000_000_001;

            // a 건물 기준으로 왼쪽 건물들
            for (int b = a - 1; b > 0; b--) {
                double curSlope = calculateSlope(a, heights[a], b, heights[b]);

                // 다른 빌딩이 보이는 경우
                if (preSlope > curSlope) {
                    preSlope = curSlope;
                    count++;
                }
            }

            preSlope = -1_000_000_001;

            // a 건물 기준으로 오른쪽 건물들
            for (int b = a + 1; b <= n; b++) {
                double curSlope = calculateSlope(a, heights[a], b, heights[b]);

                // 다른 빌딩이 보이는 경우
                if (preSlope < curSlope) {
                    preSlope = curSlope;
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }

    // 두 점 사이의 기울기 구하기
    private double calculateSlope(int x1, int y1, int x2, int y2) {
        return (double) (y2 - y1) / (x2 - x1);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] strNums = br.readLine().split(" ");

        int[] heights = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            heights[i] = Integer.parseInt(strNums[i - 1]);
        }

        System.out.println(new Solution().solution(n, heights));
    }
}