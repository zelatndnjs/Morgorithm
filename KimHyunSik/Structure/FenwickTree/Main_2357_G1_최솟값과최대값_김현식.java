package structure.fenwicktree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2357_G1_최솟값과최대값_김현식 {
	static final int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;
	static int N;
	static int[] nums, minFront, minBack, maxFront, maxBack;

	public static void main(String[] args) throws IOException {
		int M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder result = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		minFront = new int[N + 1];
		minBack = new int[N + 1];
		maxFront = new int[N + 1];
		maxBack = new int[N + 1];
		Arrays.fill(minFront, MAX);
		Arrays.fill(minBack, MAX);
		Arrays.fill(maxFront, MIN);
		Arrays.fill(maxBack, MIN);

		for (int i = 1; i <= N; i++)
			nums[i] = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++)
			update(i);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			result.append(getMin(start, end)).append(" ").append(getMax(start, end)).append("\n");
		}
		System.out.println(result);
		show();
	}

	static void update(int idx) {
		int cur = idx;
		int prev = idx;
		while (cur <= N) {
			minFront[cur] = Integer.min(minFront[prev] > minFront[cur] ? minFront[cur] : minFront[prev], nums[cur]);
			maxFront[cur] = Integer.max(maxFront[prev] < maxFront[cur] ? maxFront[cur] : maxFront[prev], nums[cur]);
			prev = cur;
			cur += cur & -cur;
		}

		cur = idx;
		prev = idx;
		while (cur >= 1) {
			minBack[cur] = Integer.min(minBack[prev] > minBack[cur] ? minBack[cur] : minBack[prev], nums[cur]);
			maxBack[cur] = Integer.max(maxBack[prev] < maxBack[cur] ? maxBack[cur] : maxBack[prev], nums[cur]);
			prev = cur;
			cur -= cur & -cur;
		}
	}

	static int getMin(int start, int end) {
		if (start > end) {
			int temp = end;
			end = start;
			start = temp;
		}
		int result = MAX;

		int prev = start;
		int cur = prev + (prev & -prev);
		while (cur <= end) {
			result = Integer.min(result, minBack[prev]);
			prev = cur;
			cur += cur & -cur;
		}
		result = Integer.min(result, nums[prev]);

		prev = end;
		cur = prev - (prev & -prev);
		while (cur >= start) {
			result = Integer.min(result, minFront[prev]);
			prev = cur;
			cur -= cur & -cur;
		}
		return result;
	}

	static int getMax(int start, int end) {
		if (start > end) {
			int temp = end;
			end = start;
			start = temp;
		}
		int result = MIN;

		int prev = start;
		int cur = prev + (prev & -prev);
		while (cur <= end) {
			result = Integer.max(result, maxBack[prev]);
			prev = cur;
			cur += cur & -cur;
		}
		result = Integer.max(result, nums[prev]);

		prev = end;
		cur = prev - (prev & -prev);
		while (cur >= start) {
			result = Integer.max(result, maxFront[prev]);
			prev = cur;
			cur -= cur & -cur;
		}

		return result;
	}

	static void show() {
		System.out.print("idx\t");
		for (int i = 1; i <= N; i++)
			System.out.print(i + "\t");
		System.out.println();

		System.out.print("nums\t");
		for (int i = 1; i <= N; i++)
			System.out.print(nums[i] + "\t");
		System.out.println();

		System.out.println(
				"--------------------------------------------------------------------------------------------");

		System.out.print("minF\t");
		for (int i = 1; i <= N; i++)
			System.out.print(minFront[i] + "\t");
		System.out.println();

		System.out.print("minB\t");
		for (int i = 1; i <= N; i++)
			System.out.print(minBack[i] + "\t");
		System.out.println();

		System.out.println(
				"--------------------------------------------------------------------------------------------");

		System.out.print("maxF\t");
		for (int i = 1; i <= N; i++)
			System.out.print(maxFront[i] + "\t");
		System.out.println();

		System.out.print("maxB\t");
		for (int i = 1; i <= N; i++)
			System.out.print(maxBack[i] + "\t");
	}

}
