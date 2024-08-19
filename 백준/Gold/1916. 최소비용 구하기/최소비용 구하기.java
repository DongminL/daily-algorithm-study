import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int n;    // 도시의 개수
    int m;    // 버스의 개수
    ArrayList<Bus>[] list;    // 버스로 연결된 도시들의 관계 (인접 리스트)
    int[] minimumCost;    // 출발지로부터 각 도시까지의 최소 비용
    
    /* start -> end 도시까지의 최소 비용 구하기 (다익스트라) */
    private int dijkstra(int start, int end) {
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        
        // 초기값
        pq.offer(new Bus(start, 0));
        minimumCost[start] = 0;
        
        // 연결된 도시들 탐색
        while (!pq.isEmpty()) {
            Bus cur = pq.poll();    // 현재 탄 버스
            
            // 이미 방문한 도시는 건너띄기
            if (!visited[cur.dest]) {
                // 버스가 목적지에 도착했다고 가정
                visited[cur.dest] = true;
            
                // 다음 도시까지의 최소 비용 구하기
                list[cur.dest].stream()
                    .filter(next -> minimumCost[cur.dest] + next.price < minimumCost[next.dest])    // 최소 비용을 갱신할 수 있을 때만 탐색
                    .forEach(next -> {
                        minimumCost[next.dest] = minimumCost[cur.dest] + next.price;    // 최소 비용 갱신
                    
                        // 갱신된 비용으로 설정한 후 다음 도시부터 탐색하도록 추가
                        pq.offer(new Bus(next.dest, minimumCost[next.dest]));
                    });
            }
        }
        
        return minimumCost[end];    // 도착 지점까지의 최소 비용
    }
    
    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        // 배열 및 리스트 초기화
        list = new ArrayList[n+1];
        minimumCost = new int[n+1];
        IntStream.range(1, n+1).forEach(i -> {
            list[i] = new ArrayList<>();
            minimumCost[i] = Integer.MAX_VALUE;
        });
        
        // 버스 정보 입력 받기
        IntStream.range(0, m).forEach(i -> {
           try {
               // 입력받은 버스 정보 (0번째: 출발 도시, 1번째: 도착 도시, 2번째: 버스 비용)
               int[] busInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
               
               // 버스 정보 추가
               list[busInfo[0]].add(new Bus(busInfo[1], busInfo[2]));
               
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        });
        
        // 입력받은 출발과 도착 도시 번호 (0번째: 출발지, 1번째: 도착지)
        int[] startEnd = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        // 구한 최소 비용 출력
        int cost = dijkstra(startEnd[0], startEnd[1]);    
        System.out.println(cost);
    }
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Bus implements Comparable<Bus> {
    
    int dest;    // 도착할 도시의 번호
    int price;    // 버스의 비용
    
    public Bus(int dest, int price) {
        this.dest = dest;
        this.price = price;
    }
    
    // 오름차순
    @Override
    public int compareTo(Bus b) {
        return Integer.compare(this.price, b.price);
    }
}