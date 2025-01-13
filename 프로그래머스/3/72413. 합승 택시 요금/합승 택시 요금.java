import java.util.*;

class Solution {
    
    private List<Taxi>[] routes; // 택시 경로에 대한 인접 리스트
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE; // 최적 예상 택시요금
        
        // 인접 리스트 초기화
        routes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            routes[i] = new ArrayList<>();
        }
        
        // 양방향 엣지로 추가
        for (int[] fare : fares) {
            routes[fare[0]].add(new Taxi(fare[1], fare[2]));
            routes[fare[1]].add(new Taxi(fare[0], fare[2]));
        }
        
        
        int[] routeOfStart = dijkstra(n, s);    // s 지점부터 시작한 최소 요금
        int[] routeOfA = dijkstra(n, a);    // a 지점부터 시작한 최소 요금
        int[] routeOfB = dijkstra(n, b);    // b 지점부터 시작한 최소 요금
        
        // 합승 택시 요금의 최소 요금 구하기
        for (int i = 1; i <= n; i++) {
            // 양방향 엣지이기 때문에 시작 지점과 출발 지점이 바껴도 똑같음
            int fare = routeOfStart[i] + routeOfA[i] + routeOfB[i];
            answer = Math.min(answer, fare);
        }
        
        return answer;
    }
    
    // 최소 요금 구하기
    private int[] dijkstra(int n, int start) {
        // 각 지점까지의 최소 비용
        int[] minimumFares = new int[n + 1];
        Arrays.fill(minimumFares, 20_000_001);
        
        Queue<Taxi> pq = new PriorityQueue<>();
        
        // 시작 지점 설정
        pq.offer(new Taxi(start, 0));
        minimumFares[start] = 0;
        
        while (!pq.isEmpty()) {
            Taxi current = pq.poll();   // 현재 위치
            
            // 더 적은 비용으로 올 수 있는 경우는 패스
            if (minimumFares[current.location] < current.fare) {
                continue;
            }
            
            for (Taxi next : routes[current.location]) {
                // 최소 요금이 갱신되는 경우에만 탐색
                if (minimumFares[current.location] + next.fare < minimumFares[next.location]) {
                    minimumFares[next.location] = minimumFares[current.location] + next.fare;
                    pq.offer(new Taxi(next.location, minimumFares[next.location]));
                }
            }
        }
        
        return minimumFares;
    }
    
    static class Taxi implements Comparable<Taxi> {
        
        int location;    // 위치
        int fare;   // 요금
        
        public Taxi(int location, int fare) {
            this.location = location;
            this.fare = fare;
        }
        
        public int compareTo(Taxi o) {
            return Integer.compare(this.fare, o.fare);
        }
    }
}