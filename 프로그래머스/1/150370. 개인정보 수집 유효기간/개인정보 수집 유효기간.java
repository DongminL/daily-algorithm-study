import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        // 약관 종류와 유효기간 매핑
        Map<String, Integer> termList = new HashMap<>();
        Arrays.stream(terms).forEach(term -> {
            StringTokenizer st = new StringTokenizer(term);
            String type = st.nextToken();
            Integer period = Integer.parseInt(st.nextToken());
            
            termList.put(type, period);
        });
        
        // 유효하지 않은 개인정보 파기
        List<Integer> answer = new ArrayList<>();
        IntStream.range(0, privacies.length).forEach(i -> {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            String collectedDate = st.nextToken();
            String type = st.nextToken();
            
            if (!isValid(today, collectedDate, termList.get(type))) {
                answer.add(i+1);
            }
        });
        
        answer.sort(Comparator.naturalOrder());
        
        return answer;
    }
    
    /* 해당 날짜를 날(day) 수로 바꾸기 */
    private int toTotalDay(String date)  {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        
        return (year * 12 * 28) + (month * 28) + day;
    }
    
    /* 해당 날짜에 유효 기간 더하기 */
    private int addTerm(String date, int term) {
        int totalDay = toTotalDay(date);
        int termDay = term * 28;
        
        return totalDay + termDay - 1;  // 수집한 당일 날짜 빼기
    }
    
    /* 유효한 개인정보인지 확인 */
    private boolean isValid(String today, String privacy, int term) {
        return toTotalDay(today) <= addTerm(privacy, term);
    }
}