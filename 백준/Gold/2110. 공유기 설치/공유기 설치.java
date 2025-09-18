import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int c, int[] points) {
        // 가장 인접한 두 공유기 사이의 최대 거리
        int answer = 0;

        // 오름차순 정렬
        Arrays.sort(points);

        // 공유기 간 거리 범위 (Two Point)
        int left = 1;
        int right = points[n - 1] - points[0];

        // 공유기 간의 최소 거리가 최대가 되는 거리 찾기 (Binary Search)
        while (left <= right) {
            int mid = (left + right) / 2;

            // 공유기 설치가 가능한 경우
            if (isPossibleDistance(c, points, mid)) {
                answer = mid;

                // 공유기 거리를 더 멀리 해보기 위함
                left = mid + 1;
            } else {
                // 설치가 불가능하기 때문에, 거리를 더 좁혀본다.
                right = mid - 1;
            }
        }

        return answer;
    }

    // distance 거리 이상으로 공유기를 설치 가능한지 여부
    private boolean isPossibleDistance(int targetCount, int[] points, int distance) {
        int prePoint = points[0];   // 이전 지점
        int count = 1;  // 설치한 개수 (첫 번째 지점은 기본적으로 설치하고 시작)

        for (int i = 1; i < points.length; i++) {
            int curPoint = points[i];   // 현재 지점

            // 지정된 거리 이상일 때,
            if (curPoint - prePoint >= distance) {
                prePoint = curPoint;
                count++;

                // 보유한 공유기 개수 이상으로 설치할 수 있으면, 해당 거리로 설치 가능함
                if (count >= targetCount) {
                    return true;
                }
            }
        }

        return false;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int[] points = new int[inputInfo[0]];
        for (int i = 0; i < points.length; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], points));
    }
}