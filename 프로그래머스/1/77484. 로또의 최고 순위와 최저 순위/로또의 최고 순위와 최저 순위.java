class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        answer[0] = rank(zeroCount(lottos) + winSameCount(lottos, win_nums));   // 최고 순위
        answer[1] = rank(winSameCount(lottos, win_nums));   // 최저 순위
        
        return answer;
    }
    
    /* 당첨 번호의 개수 확인 */
    private int winSameCount(int[] lottos, int[] win_nums){
        int count = 0;
        
        for(int i = 0; i < lottos.length; i++){
            for(int j = 0; j < win_nums.length; j++){
                if(lottos[i] == win_nums[j])
                    count++;
            }
        }
        
        return count;
    }
    
    /* 0으로 표기된 개수 */
    private int zeroCount(int[] lottos){
        int zeroCount = 0;
        
        for(int num : lottos)
            if(num == 0)
                zeroCount++;
        
        return zeroCount;
    }
    
    /* 당첨 번호와 일치하는 개수에 따른 등수 */
    private int rank(int num){
        switch(num){
            case 6 : return 1;
                
            case 5 : return 2;
               
            case 4 : return 3;
                
            case 3 : return 4;
                
            case 2 : return 5;
               
            default : return 6;
        }
    }
        
    
}