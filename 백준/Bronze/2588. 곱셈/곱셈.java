import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        String bToStr = b+ "";
        int firstNum = bToStr.charAt(2) - '0';
        int secondNum = bToStr.charAt(1) - '0';
        int thirdNum = bToStr.charAt(0) - '0';
        
        System.out.println(a*firstNum);
        System.out.println(a*secondNum);
        System.out.println(a*thirdNum);
        System.out.println(a*b);
    }
}