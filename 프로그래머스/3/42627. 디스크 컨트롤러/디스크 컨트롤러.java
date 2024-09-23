import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0; // 작업의 요청부터 종료까지 걸린 시간의 평균의 최소값
        int time = 0;   // 경과한 시간
        
        // 작업 스케줄러 오름차순 (Priority Queue)
        PriorityQueue<Job> runQ = new PriorityQueue<>((j1, j2) -> j1.runTime - j2.runTime);  // 작업 시간 기준 실행 스케줄러
        PriorityQueue<Job> startQ = new PriorityQueue<>((j1, j2) -> j1.start - j2.start);  // 요청 시간 기준 대기 스케줄러
        Arrays.stream(jobs)
            .forEach(job -> startQ.offer(new Job(job[0], job[1])));
        
        // 효율적인 작업 수행
        while (!startQ.isEmpty() || !runQ.isEmpty()) {
            // 현재 시간에 실행시킬 수 있는 작업을 다른 스케줄러로 옮기기
            while (!startQ.isEmpty() && startQ.peek().start <= time) {
                runQ.offer(startQ.poll());
            }
            
            // 해당 시간에 바로 실행시키 못하는 경우
            if (runQ.isEmpty()) {
                time = startQ.peek().start;
            } else {
                Job run = runQ.poll();  // 실행 중인 작업
                
                // 시간 갱신
                answer += (time - run.start) + run.runTime;
                time += run.runTime;
            }
        }
        
        return answer / jobs.length;
    }
}

class Job {
    
    int start;  // 요청되는 시점
    int runTime;    // 소요 시간
    
    public Job(int start, int runTime) {
        this.start = start;
        this.runTime = runTime;
    }
}