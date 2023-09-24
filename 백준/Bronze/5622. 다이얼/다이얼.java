import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] alphaToNum = {"ABC", "DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};

        String[] alpha = br.readLine().split("");
        int time = 0;
        for (int i = 0; i<alpha.length; i++) {
            for (int j=0; j<alphaToNum.length; j++) {
                if (alphaToNum[j].contains(alpha[i])) {
                    time += j+3;
                }
            }
        }
        System.out.println(time);
    }
}