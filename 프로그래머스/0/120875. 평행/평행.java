class Solution {
    /* 두 직선이 평행이 되는 경우가 있으면 1을 없으면 0 */
    public int solution(int[][] dots) {
        if (gradient(dots[0], dots[1]) == gradient(dots[2], dots[3])) {
            return 1;
        }
        
        if (gradient(dots[0], dots[2]) == gradient(dots[1], dots[3])) {
            return 1;
        }
        
        if (gradient(dots[0], dots[3]) == gradient(dots[1], dots[2])) {
            return 1;
        }
        
        return 0;
    }
    
    /* 두 점의 직선의 기울기 구하기 */
    private double gradient(int[] dot1, int[] dot2) {
        return (double) (dot2[1] - dot1[1]) / (dot2[0] - dot1[0]) * 1.0;
    }
}