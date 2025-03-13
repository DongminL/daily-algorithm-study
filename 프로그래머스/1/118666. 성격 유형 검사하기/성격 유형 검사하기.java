class Solution {
    
    int[] RT = new int[2];  // RT 지표의 점수, (0: R, 1: T)
    int[] CF = new int[2];  // CF 지표의 점수, (0: C, 1: F)
    int[] JM = new int[2];  // JM 지표의 점수, (0: J, 1: M)
    int[] AN = new int[2];  // AN 지표의 점수, (0: A, 1: N)
    
    public String solution(String[] survey, int[] choices) {
        for (int i = 0; i < survey.length; i++) {
            summary(survey[i], choices[i]);
        }
        
        return check();    // 최종 성격 유형
    }
    
    // 성격 유형 정하기
    private String check() {
        String result = "";
        
        // 둘 중 더 높은 유형 선택
        if (RT[0] < RT[1]) {
            result += "T";
        } else {
            result += "R";
        }
        if (CF[0] < CF[1]) {
            result += "F";
        } else {
            result += "C";
        }
        if (JM[0] < JM[1]) {
            result += "M";
        } else {
            result += "J";
        }
        if (AN[0] < AN[1]) {
            result += "N";
        } else {
            result += "A";
        }
        
        return result;
    }
    
    // 각 지표별로 점수 매기기
    private void summary(String surveyName, int choice) {
        int score = toScore(choice);
        
        if (surveyName.equals("RT")) {
            if (score < 0) {
                RT[0] += Math.abs(score); 
            } else {
                RT[1] += score;
            }
            return;
        }
        
        if (surveyName.equals("TR")) {
            if (score < 0) {
                RT[1] += Math.abs(score); 
            } else {
                RT[0] += score;
            }
            return;
        }
        
        if (surveyName.equals("CF")) {
            if (score < 0) {
                CF[0] += Math.abs(score); 
            } else {
                CF[1] += score;
            }
            return;
        }
        
        if (surveyName.equals("FC")) {
            if (score < 0) {
                CF[1] += Math.abs(score); 
            } else {
                CF[0] += score;
            }
            return;
        }
        
        if (surveyName.equals("JM")) {
            if (score < 0) {
                JM[0] += Math.abs(score); 
            } else {
                JM[1] += score;
            }
            return;
        }
        
        if (surveyName.equals("MJ")) {
            if (score < 0) {
                JM[1] += Math.abs(score); 
            } else {
                JM[0] += score;
            }
            return;
        }
        
        if (surveyName.equals("AN")) {
            if (score < 0) {
                AN[0] += Math.abs(score); 
            } else {
                AN[1] += score;
            }
            return;
        }
        
        if (surveyName.equals("NA")) {
            if (score < 0) {
                AN[1] += Math.abs(score); 
            } else {
                AN[0] += score;
            }
            return;
        }
    }
    
    // 각 선택지를 점수로 변경
    private int toScore(int choice) {
        if (choice < 4) {
            return choice - 4;
        }
        return choice % 4;
    }
}