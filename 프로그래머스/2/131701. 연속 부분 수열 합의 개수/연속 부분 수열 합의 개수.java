import java.util.*;
import java.util.stream.*;

class Solution {
    
    // 원형 수열의 연속 부분 수열 합으로 만들 수 있는 수
    Set<Integer> sumValues;
    
    public int solution(int[] elements) {
        sumValues = new HashSet<>();
        
        // 연속된 수의 개수를 1부터 n까지 증가시켜 경우의 수를 구함
        for (int i = 1; i <= elements.length; i++) {
            combin(elements, elements.length, 0, i);
        }
        
        return sumValues.size();
    }
    
    /* 조합 (n: 배열의 크기, cur: 현재 인덱스, m: 조합의 크기) */ 
    private void combin(int[] elements, int n, int cur, int m) {
        if (cur == n) {
            return;
        }
        
        // 조합의 합 계산
        sumCombin(elements, elements.length, cur, m);
        
        // 다음 수로 이동
        combin(elements, elements.length, cur+1, m);
    }
    
    /* 조합된 수의 합 (n: 배열의 크기, cur: 현재 인덱스, m: 조합의 크기) */ 
    private void sumCombin(int[] elements, int n, int cur, int m) {
        int sum = 0;    // 조합의 합
        
        // 원형 수열의 연속 부분 수열 합 계산
        for (int i = cur; i < cur + m; i ++) {
            sum += elements[i % n]; // % 연산으로 원형 효과
        }
        
        // 만들어진 부분 수열의 합 추가
        sumValues.add(sum);
    }
}