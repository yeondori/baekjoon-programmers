import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

    	Scanner sc = new Scanner(System.in);
        long totalPrice = sc.nextLong();
        int totalProductNum = sc.nextInt();
        long calTotalPrice = 0;
        
        for (int i=0;i<totalProductNum;i++) {
        	int product = sc.nextInt();
        	int num = sc.nextInt();
        	
        	calTotalPrice += product*num;       			
        }    
        
        System.out.println((totalPrice == calTotalPrice)? "Yes" : "No");    
    }
}