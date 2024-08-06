import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // Interval 오름차순 정렬
        
        ArrayList<int[]> overlap = new ArrayList<>();
        // 이전 인터벌 선언 및 intervals 배열의 첫 번째 값으로 설정
        int[] prevInterval = intervals[0];
        
        // Merge Intervals
        for(int i = 1; i < intervals.length; i++) {
            // 이전 인터벌이 현재 탐색중인 인터벌보다 앞에 있는 경우
            if(prevInterval[1] < intervals[i][0]) {
                // 이전 인터벌을 결과 리스트에 추가
                overlap.add(prevInterval);
                // 이전 인터벌을 현재 탐색중인 인터벌로 갱신
                prevInterval = intervals[i];
            } else {  // 이전 인터벌과 현재 탐색중인 인터벌이 겹치는 경우
                // 이전 인터벌의 종료점을 두 인터벌의 종료점 중 큰 값으로 갱신
                prevInterval[1] = Math.max(prevInterval[1], intervals[i][1]);
            }
        }
        overlap.add(prevInterval);  // 마지막 인터벌 추가
        
        // 리스트를 배열로 변환하여 반환
        return overlap.toArray(new int[overlap.size()][]);
    }
}