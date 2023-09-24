import java.util.*;

class Main {
    public static void main(String[] args){
        
    	Scanner sc = new Scanner(System.in);
    	int num = sc.nextInt();
    	sc.close();
    	
    	for (int i=1; i<=9; i++) {
    		System.out.printf("%d * %d = " + num*i +"%n" ,num, i);
    	}
    		
    	
        }
}