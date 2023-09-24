import java.util.*;

class Main {
    public static void main(String[] args){
        
    	Scanner sc = new Scanner(System.in);
    	int num = sc.nextInt();
    	int[] testCase = new int[num];
    	
    	for (int i=0; i<num; i++) {
    		int a = sc.nextInt();
    		int b = sc.nextInt();
    		
    		testCase[i] = a+b;
    	}    	
    	sc.close();
    	
    	for (int i=0; i<num; i++) {
    		System.out.println(testCase[i]);
    	}
    	
        }
}
