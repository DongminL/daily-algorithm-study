import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0; // 최소 단속 카메라 수
        
        // 나간 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (r1, r2) -> r1[1] - r2[1]);
        
        // 첫번째 단속 카메라 설치
        int camera = routes[0][1];    // 카메라를 설치한 지점
        answer++;
        
        // 단속 카메라 추가
        for (int[] route : routes) {
            // 카메라를 못 만날 경우
            if (camera < route[0]) {
                camera = route[1];  // 추가한 카메라 위치 갱신
                answer++;
            }
        }
        
        return answer;
    }
}