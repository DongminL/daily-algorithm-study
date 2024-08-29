import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;   // 수업을 들을 수 있는 최대 학생의 수
        boolean[] isBorrow = new boolean[n+2];     // 각 학생이 빌려줄 수 있는 체육복의 여부 (양 끝 학생이 좌우를 탐색하기 위함)
        
        // 체육복 여부 갱신
        Arrays.stream(reserve).forEach(student -> isBorrow[student] = true);
        
        // 여벌이 있는 사람이 도둑 맞았을 때, 자신의 여벌 입기
         List<Integer> lostList = new ArrayList<>();    // 여벌 있는 사람이 도둑 맞은 경우 제외한 리스트
        for (int s : lost) {
            if (isBorrow[s]) {
                isBorrow[s] = false;
                answer++;
            } else {
                lostList.add(s);
            }
        }
        
        // 잃어버린 사람 정렬 (순번이 낮은 학생이 (자신 +1)번째 아이를 먼저 탐색할 수 있음)
        Collections.sort(lostList);
        
        // 체육복 빌리기
        for (int s : lostList) {
            if (isBorrow[s-1]) {
                isBorrow[s-1] = false;
                answer++;
                
                continue;
            }
            
            if (isBorrow[s+1]) {
                isBorrow[s+1] = false;
                answer++;
                
                continue;
            }
        }
        
        return answer;
    }
}