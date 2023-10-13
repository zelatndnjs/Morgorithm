package sunjobu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S1_연산자배치하기_김현식 {
	static final int NUM_OPERATOR = 3;
	static int n, max, min;
	static int[] nums, operator;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		nums = new int[n];
		operator = new int[n - 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int cur = 0;
		for (int i = 0; i < NUM_OPERATOR; i++) {
			int opCnt = Integer.parseInt(st.nextToken());
			for (int k = 0; k < opCnt; k++)
				operator[cur++] = i;
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		do {
			int calVal = getValue();
			max = Integer.max(max, calVal);
			min = Integer.min(min, calVal);
		} while (np());
		System.out.println(min + " " + max);
	}

	static boolean np() {
		int i = n - 2;
		while (i > 0 && operator[i - 1] >= operator[i])
			i--;
		if (i == 0)
			return false;

		int j = n - 2;
		while (operator[i - 1] >= operator[j])
			j--;
		swap(i - 1, j);

		int k = n - 2;
		while (i < k)
			swap(i++, k--);

		return true;
	}

	static int getValue() {
		int result = nums[0];

		for (int i = 0; i < n - 1; i++)
			switch (operator[i]) {
			case 0: // +
				result += nums[i + 1];
				break;
			case 1: // -
				result -= nums[i + 1];
				break;
			case 2: // *
				result *= nums[i + 1];
				break;
			}

		return result;
	}

	static void swap(int a, int b) {
		int temp = operator[a];
		operator[a] = operator[b];
		operator[b] = temp;
	}

}
