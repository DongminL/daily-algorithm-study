import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();
    StringTokenizer st;
    
    int n;    // 학생의 수    (1 ~ n번까지의 학생)
    int m;    // 키를 비교한 횟수
    ArrayList<Integer>[] list;    // 키 순서를 나타내는 인접 리스트
    int[] frontPeople;    // 각 번호의 학생의 앞 사람의 수 (진입 차수 배열)
    
    /* 키 순서대로 줄 세우기 (위상 정렬) */
    private void sorting() {
        Deque<Integer> queue = new ArrayDeque<>();
        
        // 진입 차수가 0인 것부터 큐에 삽입
        IntStream.range(1, n+1)
            .filter(student -> frontPeople[student] == 0)
            .forEach(queue::offerLast);
        
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();    // 현재 앞에 위치한 학생
            
            // 현재 앞에 위치한 학생의 뒤에 있는 학생을 탐색
            list[cur].stream()
                .peek(next -> frontPeople[next]--)    // 앞에 학생이 정렬하러 나갔으므로 -1
                .filter(next -> frontPeople[next] == 0)
                .forEach(queue::offerLast);
            
            result.append(cur + " ");    // 앞에 위치한 학생 결과로 추가
        }
    }
    
    private void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // 배열 및 리스트 초기화
        frontPeople = new int[n+1];
        list = new ArrayList[n+1];
        IntStream.range(1, n+1).forEach(idx -> list[idx] = new ArrayList<>());
        
        IntStream.range(0, m).forEach(idx -> {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // a는 b 앞에 있어야 함
            list[a].add(b); 
            frontPeople[b]++;
        });
        
        sorting();    // 위상 정렬
        
        System.out.println(result);
    }
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}