import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int m, int[] location) {
        // 굴다리를 모두 비추기 위한 가로등의 최소 높이
        int answer = n;

        // 가로등 길이의 범위
        int min = 1;
        int max = n;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (canAllLightUp(n, mid, m, location)) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return answer;
    }

    private boolean canAllLightUp(int maxHeight, int height, int lightCount, int[] location) {
        // 첫 번째 가로등의 높이가 짧을 경우
        if (height < location[0]) {
            return false;
        }

        // 2 ~ n-1 번째 가로등 사이 검사
        for (int i = 1; i < lightCount - 2; i++) {
            if (2 * height < location[i + 1] - location[i]) {
                return false;
            }
        }

        // 마지막 가로등의 높이가 짧을 경우
        if (height < maxHeight - location[lightCount - 1]) {
            return false;
        }

        return true;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 굴다리 길이
        int m = Integer.parseInt(br.readLine());    // 가로등의 개수
        // 가로등의 위치
        int[] location = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        System.out.println(new Solution().solution(n, m, location));
    }
}