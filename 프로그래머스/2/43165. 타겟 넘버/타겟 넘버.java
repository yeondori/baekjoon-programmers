import java.util.*;

class Solution {
    
    static int[] inputNumbers;
    static int numberLen, answer, targetValue;
    
    public int solution(int[] numbers, int target) {
        inputNumbers = numbers.clone();
        numberLen = numbers.length;
        targetValue = target;
        answer = 0;

        recur(0, 0);
        return answer;
    }
    
    static void recur(int idx, int value) {
        if (idx == numberLen) {
            if (value == targetValue) answer++;
            return;
        }
        
        recur(idx+1, value+inputNumbers[idx]);
        recur(idx+1, value-inputNumbers[idx]);
    }
}