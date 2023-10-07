import java.io.*;

public class Main {

    public static String flip(String str) {
        StringBuilder flippedStr = new StringBuilder("");
        for (int i=str.length()-1; i>=0; i--) {
            flippedStr.append(str.charAt(i));
        }
        return flippedStr.toString();
    }

    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder("");

        String s = br.readLine();

        boolean isTag = false;
        int tagSt = -1;
        int tagFn = -1;

        boolean isWord = false;
        int wordSt = -1;
        int wordFn = -1;


        for (int i=0; i<s.length();i++) {
            char c = s.charAt(i);

            switch (c) {
                case 60: //'<'
                    if (isWord) {
                        wordFn = i;
                        String flippedStr = flip(s.substring(wordSt,wordFn));
                        answer.append(flippedStr);
                        isWord = false;
                    }
                    tagSt = i;
                    isTag = true;
                    break;
                case 62: //'>'
                    tagFn = i;
                    answer.append(s.substring(tagSt, tagFn+1));
                    isTag = false;
                    break;
                case 32: //' '
                    if (isWord) {
                        wordFn = i;
                        String flippedStr = flip(s.substring(wordSt,wordFn));
                        answer.append(flippedStr).append(c);
                        isWord = false;
                    } else if (!isTag) answer.append(c);
                    break;
                default:
                    if (!isTag&&!isWord) {
                        isWord = true;
                        wordSt = i;

                    } else if (i==s.length()-1&&isWord) {
                        String flippedStr = flip(s.substring(wordSt));
                        answer.append(flippedStr);
                    }
                    break;
            }
        }
        System.out.println(answer);
    }
}