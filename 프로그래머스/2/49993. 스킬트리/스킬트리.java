import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0; // 가능한 스킬트리 개수
        
        // String skill -> Set<Character>
        Set<Character> singleSkills = new HashSet<>(skill.chars()
                                                     .mapToObj(c -> (char) c)
                                                     .collect(Collectors.toList()));
        
        // 유저가 만든 스킬트리가 유효한지 검사
        for (String s : skill_trees) {
            // 유저가 만든 스킬트리 순서
            String skillByUser = toCustomSkillTree(s, singleSkills);
            
            // skill : CBD일 때, skillByUser : CBD, CB (O) / BD (X)
            if (skill.startsWith(skillByUser)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    /* 선행 스킬 순서에 해당하는 스킬의 순서 만들기 */
    private String toCustomSkillTree(String skillByUser, Set<Character> singleSkills) {
        // 유저가 만든 스킬트리의 스킬 순서
        String customSkillTree = "";
        
        // 선행 스킬 순서에 해당하는 스킬만 순서대로 구성
        for (char c : skillByUser.toCharArray()) {
            if (singleSkills.contains(c)) {
                customSkillTree += c;
            }
        }
        
        return customSkillTree;
    }
}