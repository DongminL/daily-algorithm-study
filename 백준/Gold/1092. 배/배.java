import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[] cranes, int m, int[] boxes) {
        int answer = 0; // 모든 박스를 배로 옮기는데 드는 시간의 최솟값

        // 크레인과 박스 무게를 오름차순 정렬
        Arrays.sort(cranes);
        Arrays.sort(boxes);

        // 모든 상자를 옮기지 못할 때
        if (boxes[m-1] > cranes[n-1]) {
            return -1;
        }

        boolean[] moved = new boolean[m];   // 배로 옮긴 박스인지 확인
        // 박스를 크레인으로 옮길 때 걸리는 시간 구하기
        while (m > 0) {
            // 박스와 크레인 무게 비교
            for (int i = n - 1; i > -1; i--) {
                // 모든 상자를 옮겼을 때
                if (m < 1) {
                    break;
                }

                for (int j = boxes.length - answer - 1; j > -1; j--) {
                    if (moved[j]) {
                        continue;
                    }
                    // 크레인에 맞는 다른 박스 찾기
                    if (cranes[i] < boxes[j]) {
                        continue;
                    }

                    // 배로 옮길 때
                    moved[j] = true;
                    m--;
                    break;
                }
            }

            answer++;
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 크레인의 개수
        int[] cranes = Arrays.stream(br.readLine().split(" "))    // 각 크레인의 무게 제한
                .mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());    // 박수의 개수
        int[] boxes = Arrays.stream(br.readLine().split(" "))    // 각 박스의 무게
                .mapToInt(Integer::parseInt).toArray();

        System.out.println(new Solution().solution(n, cranes, m, boxes));
    }
}