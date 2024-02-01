import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, alphaNum, maxWord;
	static String[] teachAlpha;
	static String[] alpha;
	static List<Set<String>> words;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// antic는 무조건 단어의 앞뒤에 있음. 가르칠 글자 수가 5보다 작다면 단어를 읽을 수 없다.
		if (K<5) {
			System.out.println(0);
			return;
		}
		
		// 공통된 글자 5개를 뺀다
		K-= 5;
				
		Set<String> uniqueAlpha = new HashSet<>();
		teachAlpha = new String[K];
		words = new ArrayList<>(N);
		
		for (int i = 0; i < N; i++) {
			HashSet<String> word = new HashSet<String>();
			// 공통된 글자 5개를 제외하고 set에 넣어준다.
			List<String> alphaRemoveANTIC = Arrays.asList(br.readLine().split(""));
			for (int j = 0; j<alphaRemoveANTIC.size(); j++) {
				if (alphaRemoveANTIC.get(j).equals("a") || alphaRemoveANTIC.get(j).equals("n") || alphaRemoveANTIC.get(j).equals("t") 
						|| alphaRemoveANTIC.get(j).equals("i") || alphaRemoveANTIC.get(j).equals("c") ) {
					continue;
				}
				word.add(alphaRemoveANTIC.get(j));
			}
			words.add(word);
			// 공통 글자 5개를 제외한 모든 글자의 합집합
			uniqueAlpha.addAll(word);
		}
		
		alphaNum = uniqueAlpha.size();
		// 가르칠 글자가 가지고 있는 글자보다 많다면 모든 단어를 읽을 수 있다.
		if (K>=alphaNum) {
			System.out.println(N);
			return;
		}
		// N>K 이면 nCk를 구해 읽을 수 있는 최대 단어 수를 구한다.
		alpha = uniqueAlpha.toArray(new String[alphaNum]);
		combination(0,0);
		System.out.println(maxWord);
	}

	private static void combination(int idx, int start) {
		if (idx == K) {
			maxWord = Math.max(maxWord, getWordNum());
			return;
		}
		for (int i = start; i < alphaNum; i++) {
			teachAlpha[idx] = alpha[i];
			combination(idx + 1, i + 1);
		}
	}

	private static int getWordNum() {
		int wordNum = 0;
		Set<String> teachAlphas = new HashSet<>(Arrays.asList(teachAlpha));
		for (Set<String> word : words) {
			if (teachAlphas.containsAll(word)) {
				wordNum++;
			}
		}
		return wordNum;
	}
}
