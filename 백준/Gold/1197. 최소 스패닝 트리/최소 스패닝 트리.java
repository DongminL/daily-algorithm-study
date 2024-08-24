import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int n;    // 정점의 개수
    int m;    // 간선의 개수
    PriorityQueue<Edge> list;    // 그래프의 엣지리스트 (가중치로 오름차순 정렬)
    int[] represent;    // 각 노드의 대표자

    /* 해당 노드의 대표자 찾기 */
    private int find(int a) {
        if (represent[a] == a) {
            return a;
        }

        return find(represent[a]);
    }

    /* 두 노드를 하나의 그래프로 병합 */
    private boolean union(Edge edge) {
        int representA = find(edge.a);    // 노드 a의 대표자
        int representB = find(edge.b);    // 노드 b의 대표자

        // B를 A의 그래프로 병합
        if (representA != representB) {
            represent[representB] = representA;
            return true;    // 사이클 없이 병합 할 때
        }
        
        return false;    // 사이클이 생겨 병합하지 못할 때
    }

    /* 최소 신장 트리의 가중치 구하기 (MST 알고리즘) */
    private int mst() {
        int mstWeight = 0;    // MST의 가중치
        int edgeCnt = 0;    // 연결된 엣지의 수
        
        // MST 구성
        while (edgeCnt != n-1) {
            Edge cur = list.poll();    // 현재 엣지
            
            // 두 노드의 대표자가 다를 때만 병합 (사이클 방지)
            if (union(cur)) {
                edgeCnt++;
                
                // 가중치 추가
                mstWeight += cur.weight;
            }
        }
        
        return mstWeight;
    }

    private void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // 배열 및 리스트 초기화
        list = new PriorityQueue<>();
        represent = new int[n+1];
        IntStream.range(1, n+1).forEach(i -> represent[i] = i);
        
        // 간선의 정보 입력받기
        IntStream.range(0, m).forEach(i -> {
           try {
               st = new StringTokenizer(br.readLine());
               int a = Integer.parseInt(st.nextToken());
               int b = Integer.parseInt(st.nextToken());
               int weight = Integer.parseInt(st.nextToken());
               
               list.offer(new Edge(a, b, weight));    // 간선에 대한 정보 추가
               
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        });
        
        // MST의 가중치 출력
        System.out.println(mst());
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

/* 그래프의 엣지에 대한 클래스 */
class Edge implements Comparable<Edge> {

    int a;    // A 노드의 번호
    int b;    // B 노드의 번호
    int weight;    // 가중치

    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);    // 오름차순으로 정렬되게 함
    }
}