import java.util.*;

class Solution {
    
    static int curOrder, answer;
    static Map<Character, Integer> skillOrder;
    
    public int solution(String skill, String[] skill_trees) {
        answer = 0;
        
        skillOrder = new HashMap<>();
        for (int i = 0, len = skill.length(); i < len; i++ ) {
            skillOrder.put(skill.charAt(i), i);
        }
        
        for (String sk : skill_trees) {
            solve(sk);
        }
        
        return answer;
    }
    
    static void solve(String skill) {
        curOrder = 0;
        for (char ch : skill.toCharArray()) {
            
            if (skillOrder.get(ch) == null) continue;
            
            if (curOrder == skillOrder.get(ch)) {
                curOrder++;
            } else {
                return;
            }
        }
        answer++;
    }
}