class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int len = diffs.length;
        int minLev = 1;
        int maxLev = 0;
        
        for (int i = 0; i < len; i++) {
            if (diffs[i] > maxLev) {
                maxLev = diffs[i];
            }
        }
        
        int curLev;
        while(minLev < maxLev) {
            curLev = (minLev + maxLev) / 2;
            
            long time = 0;
            for (int i = 0; i < len; i++) {
                if (diffs[i] > curLev) {
                    int resolve = diffs[i] - curLev;
                    time += resolve * (times[i] + times[i-1]) + times[i];
                } else {
                    time += times[i];
                }
            }
            
            if (time > limit) {
                minLev = curLev + 1;
            } else {
                maxLev = curLev;
            }
        
        }
    
        return minLev;
    }
}