import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int m, int[][] routes) {
        // 각 헛간까지 최소 비용
        int[] minCosts = new int[n + 1];
        Arrays.fill(minCosts, 50_000_001);

        // 인접 리스트 구성
        List<Node>[] adjacencyList = makeAdjacencyList(n, routes);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 지점
        minCosts[1] = 0;
        pq.offer(new Node(1, 0));

        // 최소 비용 구하기
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 갱신 이전의 상태는 패스
            if (current.cost != minCosts[current.num]) {
                continue;
            }

            // 최종 목적지 도착
            if (current.num == n) {
                break;
            }

            for (Node next : adjacencyList[current.num]) {
                // 최소 비용 갱신
                if (current.cost + next.cost < minCosts[next.num]) {
                    minCosts[next.num] = current.cost + next.cost;
                    pq.offer(new Node(next.num, minCosts[next.num]));
                }
            }
        }

        return minCosts[n];
    }

    // 양방향 인접 리스트 만들기
    private List<Node>[] makeAdjacencyList(int n, int[][] routes) {
        List<Node>[] adjacencyList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int[] route : routes) {
            // 양방향 매핑
            adjacencyList[route[0]].add(new Node(route[1], route[2]));
            adjacencyList[route[1]].add(new Node(route[0], route[2]));
        }

        return adjacencyList;
    }

    static class Node implements Comparable<Node> {

        int num;   // 해당 지점의 번호
        int cost;   // 해당 지점까지 필요한 총 여물의 개수

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine().split(" "));
        int[][] routes = new int[inputInfo[1]][3];
        for (int i = 0; i < inputInfo[1]; i++) {
            routes[i] = toIntArray(br.readLine().split(" "));
        }

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], routes));
    }

    // String[] -> int[]
    private static int[] toIntArray(String[] array) {
        return Arrays.stream(array)
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}