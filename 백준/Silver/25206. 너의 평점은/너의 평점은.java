import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int courseNum = 20;
        double denom = 0.0;
        double numer = 0.0;

        for (int i=0; i<courseNum; i++) {
            String[] courseArr = br.readLine().split(" ");
            if (!courseArr[2].equals("P")) {
                double credit = Double.parseDouble(courseArr[1]);

                denom += (credit * GradeToDouble(courseArr[2]));
                numer += credit;
            }
        }
        System.out.println(denom/numer);
    }

    static double GradeToDouble(String s) {
        char grade = s.charAt(0);
        char plusZero;
        if (s.length()<2){
            plusZero = 0;
        }
        else {
            plusZero = s.charAt(1);
        }
        return (double) ((grade==70? 0.0:(69-grade)) + 0.5*(plusZero==43? 1:0));
    }
}