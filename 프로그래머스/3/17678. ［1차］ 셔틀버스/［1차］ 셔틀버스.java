import java.io.*;
import java.util.*;

class Solution {
    
    static final int START_TIME = 540; // 9 * 60 + 0;
    
    public String solution(int n, int t, int m, String[] timetable) {
        
        PriorityQueue<Integer> waitingQ = new PriorityQueue<>();
        for (String time: timetable) {
            waitingQ.offer(toMinutes(time));
        }
        
        for (int shuttle = 1; shuttle <= n; shuttle++) {
           int time = START_TIME + (shuttle - 1) * t;
            for (int space = 1; space <= m; space++) {
              if (shuttle == n && space == m) {
                    if (waitingQ.isEmpty()) {
                        return toTimeFormat(time);
                    } else {
                        return toTimeFormat(Math.min(time, waitingQ.peek() - 1));
                    }
                }
                
                if (!waitingQ.isEmpty() && waitingQ.peek() <= time) {
                    waitingQ.poll();
                }
            }
        }
        return null;
    }
    
    private String toTimeFormat(int time) {
        int hour = time/60;
        int minutes = time%60;
        
        StringBuilder answer = new StringBuilder("");
        
        if (hour/10 < 1) {
            answer.append("0");
        }
        answer.append(hour).append(":");
        
        if (minutes/10 < 1) {
            answer.append("0");
        }
        answer.append(minutes);
        return answer.toString();
    }
    
    private int toMinutes(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        
        return hour * 60 + minutes;
    }
}