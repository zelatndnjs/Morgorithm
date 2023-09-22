package graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_1620_S4_나는야포켓몬마스터이다솜_김현식 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static HashMap seqMap, nameMap; // key에 순서 or 이름

	public static void main(String[] args) throws IOException {
		init();
		makeDogam();
		printResult();
	}

	// 초기 입력 및 초기화
	static void init() throws IOException {
		seqMap = new HashMap<Integer, String>();
		nameMap = new HashMap<String, Integer>();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	// M번만큼 입력 받아 도감 만들기
	static void makeDogam() throws IOException{
		int seq=1;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			
			// 이미 잡은 포켓몬이면 스킵
			if(nameMap.containsKey(name))
				continue;
			
			// 새로운 포켓몬 도감에 추가
			seqMap.put(seq, name);
			nameMap.put(name, seq);
			seq++;
		}
	}
	
	// 결과출력
	static void printResult() throws IOException{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			try {
				// 입력 받은게 숫자인 경우
				int seq = Integer.parseInt(input);
				sb.append(seqMap.get(seq)).append("\n");
			}catch(NumberFormatException e) {
				// 입력 받은게 포켓몬 이름은 경우
				String name = input;
				sb.append(nameMap.get(name)).append("\n");
			}
		}
		System.out.println(sb);
	}

}
