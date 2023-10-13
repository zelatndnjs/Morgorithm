package structure.fenwicktree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2042_G1_구간합구하기_김현식 {
	static long[] arr, tree;
	static int N;

	public static void main(String[] args) throws IOException {
		int M, K;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder result = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N + 1];
		tree = new long[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = Long.parseLong(br.readLine());
		for (int i = 1; i <= N; i++)
			update(i, arr[i]);
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				long temp = arr[b];
				arr[b] = c;
				update(b, c - temp);
			} else if (a == 2)
				result.append(getSum((int) c) - getSum(b - 1)).append("\n");
		}
		System.out.println(result);
	}

	// 1. b번째 수를 gap(c-기존원소값)로 바꾸고 누적합 배열 sum 업데이트하기
	// gap = 새값 - 기존값
	static void update(int b, long gap) {

		while (b <= N) {
			tree[b] += gap;
			b += (b & -b);
		}
	}

	// 2. b~c까지 구간합 구하기
	static long getSum(int b) {
		long ans = 0;
		while (b > 0) {
			ans += tree[b];
			b -= (b & -b);
		}
		return ans;
	}

}
