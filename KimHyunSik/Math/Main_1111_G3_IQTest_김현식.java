package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1111_G3_IQTest_김현식 {

	public static void main(String[] args) throws IOException {
		int N;
		int[] arr;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		try {
			System.out.println(getNext(arr, N));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static int getNext(int[] arr, int N) throws Exception {
		// 원소개수가 3미만이면 경우의 수가 많다
		if (N == 1 || (N == 2 && arr[1] != arr[2]))
			throw new Exception("A");
		for (int i = 2; i <= N; i++)
			if (arr[i] != arr[i - 1])
				break;
			else if (i == N)
				return arr[1];

		double a, b;
		a = (double) (arr[3] - arr[2]) / (double) (arr[2] - arr[1]);
		b = (double) arr[2] - (double) (arr[1] * a);
//		System.out.println("a:" + a + "\nb:" + b);
		// a나 b가 정수가 아닌경우
		if (!isInt(a) || !isInt(b))
			throw new Exception("B");

		// 규칙이 깨진 경우
		for (int i = 2; i <= N; i++)
			if ((double) arr[i] != a * arr[i - 1] + b) {
//				System.out.println((double)arr[i] + "\t" + (arr[i - 1] * a + b));
				throw new Exception("B");
			}

		return (int) (a * arr[N] + b);
	}

	static boolean isInt(double a) {
		if (a == (int) a)
			return true;
		else
			return false;
	}

}
