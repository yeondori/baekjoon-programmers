import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer nxStr = new StringTokenizer(br.readLine()); 
        int N = Integer.parseInt(nxStr.nextToken()); 
        int X = Integer.parseInt(nxStr.nextToken());
    	StringBuilder lessThanX = new StringBuilder("");
    	
    	String str = br.readLine();
    	
    	String[] numStr = str.split(" ");
    	
    	for (int i=0; i<N; i++) {
    		int num = Integer.parseInt(numStr[i]);
    		if (num < X) {
    			if (lessThanX.toString() != "")
    				lessThanX.append(" " + num);
    			else 
    				lessThanX.append(num);
    		}
    	} 
    	System.out.println(lessThanX.toString());
	}  
}