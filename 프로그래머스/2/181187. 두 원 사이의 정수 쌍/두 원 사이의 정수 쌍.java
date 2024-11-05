class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;    // 두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수
        
        // 두 원 사이의 정수 값을 갖는 점 구하기 (1 사분면만)
        for (int x = 1; x <= r2; x++) {  // x 좌표가 1부터 시작해야 x, y축에 있는 점들이 중복이 안됨
            // y 좌표의 최대값
            int maxY = (int) Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2)));
            // y 좌표의 최소값
            int minY = (int) Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2)));
            
            // 같은 x 좌표를 갖는 점의 개수 구하기
            answer += (maxY - minY + 1);
        }
        
        // 위에서는 1 사분면만 구했으므로 4배 해준다!
        return answer * 4;
    }
}