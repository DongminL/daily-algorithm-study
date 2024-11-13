import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = arr[0]; // 배열에서 최소 공배수
        
        // 점진적으로 최소 공배수 구하기
        for (int i = 1; i < arr.length; i++) {
            answer = lcm(answer, arr[i]);
        }
        
        return answer;
    }
    
    // 최대 공약수 구하기 (유클리드 호제법)
    private int gcd(int a, int b) {
        while (b != 0) {
            int rest = a % b;
            a = b;
            b = rest;
        }
        
        return a;
    }
    
    // 최소 공배수 구하기
    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}