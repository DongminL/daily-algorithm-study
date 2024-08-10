import java.io.*;
import java.util.*;

public class Main {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int n;    // 사람의 수
    int m;    // 파티의 수
    int[] nums;    // 진신을 아는 사람의 번호
    int[] relation;    // 사람들이 속한 파티의 대표자 목록
    ArrayList<Integer>[] party;
    int result;    // 과장할 수 있는 파티의 수
    
    /*
        두 사람이 속한 파티를 합침
        B 파티의 대표자를 A 파티의 대표자로 연결
    */
    private void union(int represent, int b) {
        int representA = find(represent);    // 파티의 대표자가 속한 상위 파티의 대표자
        int representB = find(b);    // B가 속한 파티의 대표자
        
        // 두 파티의 대표자가 다를 때만 갱신
        if (representA != representB) {
            relation[representB] = representA;
        }
    }
    
    /* 해당 사람이 속한 파티의 대표자 반환 */
    private int find(int person) {
        // 해당 사람이 파티의 대표자일 때
        if (person == relation[person]) {
            return person;
        }
        
        // 대표자가 아닐 때 파티의 대표자를 찾아감
        return relation[person] = find(relation[person]); 
    }
    
    private void solution() throws IOException {
        // 사람의 수와 파티 수 입력받기
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // 진실을 아는 사람들 입력받기
        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken());    // 진실을 아는 사람의 수
        if (truthCount > 0) {
            nums = new int[truthCount];
            for (int i = 0; i < truthCount; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 파티의 인원 구성
        party = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            party[i] = new ArrayList<>();
         
            int partySize = Integer.parseInt(st.nextToken());    // 파티의 총인원
            for (int j = 0; j < partySize; j++) {
                party[i].add(Integer.parseInt(st.nextToken()));    // 파티 인원 추가
            }
        }
        
        // 개별 파티로 초기화
        relation = new int[n+1];
        for (int i = 1; i <= n; i++) {
            relation[i] = i;
        }
        
        // 각 사람들의 파티 연관 관계 구성
        for (int i = 0; i < m; i++) {
            int represent = party[i].get(0);    // 각 파티의 0번째 사람이 대표자
            
            // 같은 파티의 속한 사람들 연결
            for (int j = 1; j < party[i].size(); j++) {
                union(represent, party[i].get(j));
            }
        }
        
        /*
            과장 가능한 파티의 수 구하기
            (각 파티의 대표자와 진실을 아는 사람이 속한 파티의 대표자가 달라야 과장 가능)
        */
        result = m;
        for (int i = 0; i < m; i++) {
            int represent = party[i].get(0);    // 각 파티의 대표자
            
            for (int j = 0; j < truthCount; j++) {
                if (find(represent) == find(nums[j])) {
                    result--;
                    break;
                }
            }
        }
        
        System.out.println(result);
    }
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}