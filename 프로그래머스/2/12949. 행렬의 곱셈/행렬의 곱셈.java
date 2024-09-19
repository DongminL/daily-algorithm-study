class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];  // arr1 X arr2
        
        // 행렬곱 연산 하기
        for (int row = 0; row < answer.length; row++) {
            for (int col = 0; col < answer[0].length; col++) {
                answer[row][col] = matrixMul(row, col, arr1, arr2);
            }
        }
        
        return answer;
    }
    
    /* 행렬곱 */
    private int matrixMul(int row, int col, int[][] arr1, int[][] arr2) {
        int result = 0; // 행렬곱 결과
        
        // 행렬곱 연산
        int i = 0;
        for (int num1 : arr1[row]) {
            result += (num1 * arr2[i++][col]);
        }
        
        return result;
    }
}