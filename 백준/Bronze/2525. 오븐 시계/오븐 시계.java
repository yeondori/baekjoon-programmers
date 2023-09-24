import java.util.*;

class Main {
    public static void main(String[] args){
        
    	Scanner sc = new Scanner(System.in);
    	int H = sc.nextInt();
    	int M = sc.nextInt();
    	int cookingTime = sc.nextInt();
    	    	
    	if ((M+cookingTime)>=60) {
    		H += (M+cookingTime)/60;
    		if (H>=24) 
    			H -= 24;
    			
    		M = (M+cookingTime)%60;
    }
    	else {
    		M = (M+cookingTime)%60;
    	}	
    	
         System.out.println(H+ " " + M);
        }
}