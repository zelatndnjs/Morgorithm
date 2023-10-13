package structure.treeset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_14427_G3_수열과쿼리15_김현식 {
	static TreeSet<Element> tree;
	static Element elements[];

	public static void main(String[] args) throws IOException {
		int n, m;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder result = new StringBuilder();

		// 입력 및 초기화
		n = Integer.parseInt(st.nextToken());
		elements = new Element[n];
		tree = new TreeSet<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++)
			elements[i] = new Element(i, Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++)
			tree.add(elements[i]);

		// 계산 및 결과출력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				revise(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case 2:
				result.append(getMinIndex() + 1).append("\n");
				break;
			}
		}
		System.out.println(result);
	}

	// command 1, idx번째를 newVal로 수정
	static void revise(int idx, int newVal) {
		idx--;
		Element element = elements[idx];
		tree.remove(element);
		element.val = newVal;
		tree.add(element);
	}

	// command 2, 제일 작은값의 index값 가져오기
	static int getMinIndex() {
		return tree.first().idx;
	}

	static class Element implements Comparable<Element> {
		int idx, val;

		public Element(int idx, int val) {
			super();
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int compareTo(Element o) {
			if (this.val == o.val)
				return Integer.compare(this.idx, o.idx);
			return Integer.compare(this.val, o.val);
		}

	}
}
