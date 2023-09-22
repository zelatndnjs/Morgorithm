#include <iostream>
using namespace std;

int getNext(int* arr, int N);
bool isInt(double a);

int main() {
	int N;
	cin >> N;
	int* arr = new int[N + 1];
	for (int i = 1; i <= N; i++)
		cin >> arr[i];

	try {
		cout << getNext(arr, N) << endl;
	}
	catch (char exception) {
		cout << exception << endl;
	}
}

int getNext(int* arr, int N) {
	// 원소개수가 1개나 2개일 경우 처리
	if (N == 1 || (N == 2 && arr[1] != arr[2])) 
		throw 'A';

	// 모든 원소가 같으면 다음도 같은원소다
	for (int i = 2; i <= N; i++)
		if (arr[i] != arr[i - 1])
			break;
		else if (i == N)
			return arr[1];

	// 핵심 로직
	double a, b;
	a = (double)(arr[3] - arr[2]) / (double)(arr[2] - arr[1]);
	b = (double)arr[2] - (double)(arr[1] * a);

	// a나 b가 정수가 아닌경우
	if (!isInt(a) || !isInt(b))
		throw 'B';

	// 규칙이 깨진 경우
	for (int i = 2; i <= N; i++)
		if ((double)arr[i] != a * arr[i - 1] + b)
			throw 'B';

	// 다음 원소값
	return (int)(a * arr[N] + b);
}

bool isInt(double a) {
	if (a == (int)a)
		return true;
	else
		return false;
}