import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = Integer.parseInt(br.readLine());
    	String str = br.readLine();
    	int findNum = Integer.parseInt(br.readLine());
    	int count = 0;
    	
    	String[] numStr = str.split(" ");
    	
    	int[] numArr = new int[N];
    	for (int i=0; i<N; i++) {
    		numArr[i] = Integer.parseInt(numStr[i]);
    		if (numArr[i] == findNum) {
    			count += 1;
    		}
    	} 
    	
    	System.out.println(count);
 	
	}  
}