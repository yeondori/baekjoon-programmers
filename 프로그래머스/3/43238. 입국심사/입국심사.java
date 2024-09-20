class Solution {

    static final long MAX_AUDIT_TIME = 1_000_000_000;

    public long solution(int n, int[] times) {
        long min = 0;
        long max = MAX_AUDIT_TIME * n;
        
        long mid, cnt;
        while(min < max) {
            mid = (min + max) / 2;
            cnt = 0;            
            for (int i = 0, size = times.length; i < size; i++) {
                cnt += mid / times[i];
                
                if (cnt >= n) break;
            }
            
            if (cnt >= n) {
                max = mid;
            } else {
                min = mid+1;
            }
        }
        
        return min;
    }
}