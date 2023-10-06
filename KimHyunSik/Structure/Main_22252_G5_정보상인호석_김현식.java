package graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_22252_G5_정보상인호석_김현식 {
	static HashMap<String, PriorityQueue<Integer>> Info;
	static long Cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Info = new HashMap<>();
		Cost = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int getOrBuy = Integer.parseInt(st.nextToken());
			if (getOrBuy == 1)
				getInfo(st);
			else if (getOrBuy == 2)
				buyInfo(st);
		}
		System.out.println(Cost);
	}

	static void getInfo(StringTokenizer st) {
		String name = st.nextToken(); // 정보 이름
		int n = Integer.parseInt(st.nextToken()); // 정보 개수
		if (Info.get(name) == null)
			Info.put(name, new PriorityQueue<>(Collections.reverseOrder()));
		for (int i = 0; i < n; i++)
			Info.get(name).offer(Integer.parseInt(st.nextToken()));

	}

	static void buyInfo(StringTokenizer st) {
		String name = st.nextToken(); // 정보 이름
		int n = Integer.parseInt(st.nextToken()); // 구매할 정보 개수
		if (Info.get(name) == null)
			return;
		int sellCnt = Integer.min(n, Info.get(name).size()); // 팔 정보의 개수
		for (int i = 0; i < sellCnt; i++)
			Cost += Info.get(name).poll();
		
	}

}