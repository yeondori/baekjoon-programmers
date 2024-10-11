import java.util.*;

class Solution {
    
    static Set<String> uniqueGems;
    static Map<String, Integer> curGems;
    static int uniqueNum, rangeStart, rangeEnd, minDiff, uniqueCount;
    
    public int[] solution(String[] gems) {
        
        uniqueGems = new HashSet<>(Arrays.asList(gems));
        uniqueNum = uniqueGems.size();
        uniqueCount = 0; // 추가된 부분: 고유 보석의 개수를 추적
        
        solve(gems);
        return new int[]{rangeStart + 1, rangeEnd + 1};
    }
    
    static void solve(String[] gems) {
        int start = 0;
        int end = 0;
        minDiff = gems.length + 1;
        
        curGems = new HashMap<>();
        int curDiff;
        
        while (end < gems.length) {
            String endGem = gems[end];
            
            // end 포인터 위치의 보석을 추가
            curGems.put(endGem, curGems.getOrDefault(endGem, 0) + 1);
            if (curGems.get(endGem) == 1) {
                uniqueCount++; // 새로운 보석이 추가된 경우 고유 개수 증가
            }
            
            // 모든 보석이 포함된 경우 start 포인터를 이동해 최소 구간을 찾음
            while (uniqueCount == uniqueNum) {
                curDiff = end - start + 1;
                if (curDiff < minDiff) {
                    rangeStart = start;
                    rangeEnd = end;
                    minDiff = curDiff;
                }
                
                String startGem = gems[start];
                curGems.put(startGem, curGems.get(startGem) - 1);
                if (curGems.get(startGem) == 0) {
                    curGems.remove(startGem);
                    uniqueCount--; // 보석이 제거된 경우 고유 개수 감소
                }
                start++;
            }
            
            end++;
        }
    }
}
