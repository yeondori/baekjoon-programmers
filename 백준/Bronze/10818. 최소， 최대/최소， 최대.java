import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer nxStr = new StringTokenizer(br.readLine()," "); 
        
        int N = Integer.parseInt(nxStr.nextToken()); 
    	StringTokenizer numbers = new StringTokenizer(br.readLine(), " "); 
    	
    	int min = 1_000_000;
    	int max = -1_000_000;
    	
    	for (int i=0; i<N; i++) {
    		int num = Integer.parseInt(numbers.nextToken());
    		
    		if (num < min) 
    			min = num;
    		if (num > max)
    			max = num;
    	} 
    	System.out.println(min + " " + max);
	}  
}