import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0; // 보트를 최소로 이용하는 횟수
        int reminder = limit;   // 보트에 탈 수 있는 남은 무게
        
        // 오름차순 정렬
        Arrays.sort(people);
        
        // 투 포인터 인덱스 
        int left = 0;
        int right = people.length - 1;
        
        // 보트에 태우기
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                answer++;
                
                left++;
                right--;
            } else {
                answer++;
                
                right--;
            }
        }
        
        return answer;  // 마지막 보트 추가
    }
}