import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int[][] pillars) {
        // 창고 다각형의 면적
        int answer = 0;

        // 기둥 위치 순으로 정렬
        Arrays.sort(pillars, (p1, p2) -> Integer.compare(p1[0], p2[0]));

        int maxPillarIndex = 0;   // 가장 높은 기둥의 인덱스
        for (int i = 0; i < n; i++) {
            if (answer < pillars[i][1]) {
                answer = pillars[i][1];
                maxPillarIndex = i;
            }
        }

        int height = pillars[0][1]; // 기준 높이

        // 가장 높은 기둥 기준으로 왼쪽부터
        for (int i = 1; i <= maxPillarIndex; i++) {
            if (height < pillars[i][1]) {
                answer += height * (pillars[i][0] - pillars[i - 1][0]);

                height = pillars[i][1];
            } else {
                answer += height * (pillars[i][0] - pillars[i - 1][0]);
            }
        }

        // 가장 높은 기둥 기준으로 오른쪽부터
        height = pillars[n - 1][1];
        for (int i = n - 2; i >= maxPillarIndex; i--) {
            if (height < pillars[i][1]) {
                answer += height * (pillars[i + 1][0] - pillars[i][0]);

                height = pillars[i][1];
            } else {
                answer += height * (pillars[i + 1][0] - pillars[i][0]);
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] pillars = new int[n][2];
        for (int i = 0; i < n; i++) {
            pillars[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        System.out.println(new Solution().solution(n, pillars));
    }
}