import java.util.*;

class Main {
    public static void main(String[] args){
        
    	Scanner sc = new Scanner(System.in);
    	
    	int[] diceNum = new int[3];
    	diceNum[0] = sc.nextInt();
    	diceNum[1] = sc.nextInt();
    	diceNum[2] = sc.nextInt();
    	
    	Arrays.sort(diceNum);
    	
    	if (diceNum[0]==diceNum[2])
    		System.out.println(10_000+diceNum[0]*1_000);
    	else if ((diceNum[0]==diceNum[1])||(diceNum[1]==diceNum[2]))
    		System.out.println(1_000+diceNum[1]*100);
    	else
    		System.out.println(diceNum[2]*100);

        }
}
