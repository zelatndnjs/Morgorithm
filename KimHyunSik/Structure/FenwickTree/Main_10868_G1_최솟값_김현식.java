package structure.fenwicktree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10868_G1_최솟값_김현식 {
	static int[] nums, min, minReverse;
	static int N;

	public static void main(String[] args) throws IOException {
		int M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder result = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		min = new int[N + 1];
		minReverse = new int[N + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		Arrays.fill(minReverse, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++)
			nums[i] = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			updateMin(i);
			updateMinReverse(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			result.append(getMin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
		}
		System.out.println(result);
	}

	static void updateMin(int idx) {
		int preMin = min[idx];
		while (idx <= N) {
			min[idx] = Integer.min(min[idx], nums[idx]);
			min[idx] = Integer.min(preMin, min[idx]);
			preMin = min[idx];
			idx += idx & -idx;
		}
	}

	static void updateMinReverse(int idx) {
		int preMin = minReverse[idx];
		while (idx >= 1) {
			minReverse[idx] = Integer.min(minReverse[idx], nums[idx]);
			minReverse[idx] = Integer.min(preMin, minReverse[idx]);
			preMin = minReverse[idx];
			idx -= idx & -idx;
		}
	}

	// start이상 end 이하중 최소값 리턴
	static int getMin(int start, int end) {
		if (start > end) {
			int temp = end;
			end = start;
			start = temp;
		}
		int minVal = Integer.MAX_VALUE;

		int prev = start;
		int cur = prev + (prev & -prev);
		while (cur <= end) {
			minVal = Integer.min(minVal, minReverse[prev]);
			prev = cur;
			cur = prev + (prev & -prev);
		}
		minVal = Integer.min(minVal, nums[prev]);

		prev = end;
		cur = prev - (prev & -prev);
		while (cur >= start) {
			minVal = Integer.min(minVal, min[prev]);
			prev = cur;
			cur = prev - (prev & -prev);
		}
		return minVal;
	}

}
