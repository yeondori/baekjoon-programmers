import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String input = "";
    	while ((input = br.readLine()) != null) {
    		StringTokenizer st = new StringTokenizer(input);
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
		
    		System.out.println(a+b);
    	} 
	}  
}