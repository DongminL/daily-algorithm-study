import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 각 사람의 인덱스 번호 저장
        Map<String, Integer> indexList = new HashMap<>();
        IntStream.range(0, friends.length).forEach(i -> indexList.put(friends[i], i));
        
        // 선물 지수와 서로 주고받은 횟수 기록
        int[] giftFactor = new int[friends.length]; // 각 친구들의 선물 지수
        int[][] giftCount = new int[friends.length][friends.length];    // A와 B가 주고받은 횟수
        Arrays.stream(gifts).forEach(gift -> {
            // A -> B에게 선물 전달
            StringTokenizer st = new StringTokenizer(gift);
            String A = st.nextToken();
            String B = st.nextToken();
            
            // 선물 지수 갱신
            giftFactor[indexList.get(A)]++;
            giftFactor[indexList.get(B)]--;
            
            // 주고받은 횟수 갱신
            giftCount[indexList.get(A)][indexList.get(B)]++;
        });
        
        // 가장 많이 받을 선물의 개수 
        int answer = maxReceive(giftFactor, giftCount);
        return answer;
    }
    
    /* 다음 달에 가장 많이 받을 친구의 선물의 수 구하기 */
    private int maxReceive(int[] giftFactor, int[][] giftCount) {
        int[] max = {0};    // 가장 많이 받을 친구의 선물의 수
        
        IntStream.range(0, giftFactor.length).forEach(A -> {
            int[] nextGiftCount = {0};  // 다음 달에 받을 선물의 개수
            
            // 다음 달에 받을 선물의 개수 구하기
            IntStream.range(0, giftFactor.length)
                .filter(B -> A != B && willGet(giftFactor, giftCount, A, B))
                .forEach(B -> nextGiftCount[0]++);
            
            max[0] = Math.max(nextGiftCount[0], max[0]);
        });
        
        return max[0];
    }
    
    /* 다음 달에 A가 B에게 선물을 받을 여부 */
    private boolean willGet(int[] giftFactor, int[][] giftCount, int A, int B) {
        // 두 사람이 선물을 주고받은 기록이 있다면
        if (giftCount[A][B] > giftCount[B][A]) {
            return true;
            
        } else if (giftCount[A][B] == giftCount[B][A]) {    // 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면
            if (giftFactor[A] > giftFactor[B]) {
                return true;
            }
            
        } 
        
        return false;   // 그 외
    }
}