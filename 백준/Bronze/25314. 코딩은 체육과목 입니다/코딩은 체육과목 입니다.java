import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

     	Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int num = N/4;
        String strLong = "";
        
    	for (int i=0; i<num; i++) {
    		strLong += "long ";
    	}
    	
        System.out.println(strLong + "int");

    }
}