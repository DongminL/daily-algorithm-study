import java.util.*;
import java.io.*;

class Solution {

    public void solution(int n, int startCity, int endCity, List<Bus>[] routes) {
        // 각 도시까지의 이전 도시
        int[] previousCity = new int[n + 1];

        PriorityQueue<Bus> pq = new PriorityQueue<>();

        // 각 도시까지 최소 비용
        int[] minimumCosts = new int[n + 1];
        Arrays.fill(minimumCosts, 100_000_001);

        // 시작 지점 설정
        pq.offer(new Bus(startCity, 0));
        minimumCosts[startCity] = 0;

        while (!pq.isEmpty()) {
            Bus current = pq.poll();

            // 더 적은 비용으로 올 수 있는 경우는 패스
            if (minimumCosts[current.cityNum] < current.cost) {
                continue;
            }

            // 최소 비용 갱신
            for (Bus next : routes[current.cityNum]) {
                if (minimumCosts[current.cityNum] + next.cost < minimumCosts[next.cityNum]) {
                    minimumCosts[next.cityNum] = minimumCosts[current.cityNum] + next.cost;
                    pq.offer(new Bus(next.cityNum, minimumCosts[next.cityNum]));

                    // 다음 도시의 이전 도시 번호를 현재 도시로 기록
                    previousCity[next.cityNum] = current.cityNum;
                }
            }
        }

        // 결과 출력
        StringBuilder result = new StringBuilder();
        result.append(minimumCosts[endCity]).append("\n");

        Deque<Integer> minimumRoute = getMinimumRoute(endCity, previousCity);
        result.append(minimumRoute.size()).append("\n");
        while (!minimumRoute.isEmpty()) {
            result.append(minimumRoute.pop()).append(" ");
        }

        System.out.println(result);
    }

    // 최소 비용 방문 도시 구하기
    public Deque<Integer> getMinimumRoute(int endCity, int[] previousCity) {
        Deque<Integer> stack = new ArrayDeque<>();

        int now = endCity;
        while(now != 0) {   // 시작 도시가 아닐때까지
            stack.push(now);
            now = previousCity[now];
        }

        return stack;
    }
}

class Bus implements Comparable<Bus> {

    int cityNum;
    int cost;

    public Bus(int cityNum, int cost) {
        this.cityNum = cityNum;
        this.cost = cost;
    }

    // 비용을 기준으로 오름차순
    @Override
    public int compareTo(Bus other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Bus>[] routes = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            routes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] busInfo = br.readLine().split(" ");
            int startCity = Integer.parseInt(busInfo[0]);;
            int endCity = Integer.parseInt(busInfo[1]);
            int cost = Integer.parseInt(busInfo[2]);
            routes[startCity].add(new Bus(endCity, cost));
        }

        String[] info = br.readLine().split(" ");
        int startCity = Integer.parseInt(info[0]);
        int endCity = Integer.parseInt(info[1]);

        new Solution().solution(n, startCity, endCity, routes);
    }
}