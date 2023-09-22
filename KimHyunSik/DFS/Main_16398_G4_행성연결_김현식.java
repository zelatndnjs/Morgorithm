import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16398_G4_행성연결_김현식 {

	static int[] parents;

	static void makeSet(int v) {
		parents = new int[v];
		for (int i = 0; i < v; i++)
			parents[i] = i;
	}

	static int findSet(int v) {
		if (parents[v] == v)
			return v;
		return parents[v] = findSet(parents[v]);
	}

	static void union(int u, int v) {
		int uroot = findSet(u);
		int vroot = findSet(v);
		if (uroot == vroot)
			return;
		if (uroot < vroot)
			parents[vroot] = uroot;
		else
			parents[uroot] = vroot;
	}

	static class Planet implements Comparable<Planet> {
		int from, to, cost;

		public Planet(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Planet o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 및 초기화
		int v = Integer.parseInt(br.readLine());
		PriorityQueue<Planet> pq = new PriorityQueue<>();
		for (int r = 0; r < v; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < v; c++) {
				int val = Integer.parseInt(st.nextToken());
				if (val != 0 && r < c)
					pq.offer(new Planet(r, c, val));
			}
		}

		// 연결 비용 계산
		long result = 0;
		makeSet(v);
		while (!pq.isEmpty()) {
			Planet temp = pq.poll();
			
			// 연결되어 있는지 확인
			int fromP = findSet(temp.from);
			int toP = findSet(temp.to);
			if (fromP == toP)
				continue;

			// 연결 안되있으면 연결시키고 비용 갱신
			union(temp.from, temp.to);
			result += temp.cost;
		}
		System.out.println(result);
	}

}
