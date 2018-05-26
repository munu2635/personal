#include <stdio.h>
#include <stdlib.h>
#include <time.h>


#define MAX 100
#define TRUE 1
#define FALSE 0

void MackRandomNumber();
void QuickSort(int data[], int, int);
void DisplayBuffer();
int IsNumberExit(int, int);
int Buf[MAX];

void MakeRandomNumber() {
	int i, Num;
	i = 1;
	srand((unsigned)time(NULL));
	Buf[0] = 100;

	while (i < MAX) {
		Num = rand()%MAX;
		if(!IsNumberExit(Num, i)) {
			Buf[i] = Num;
			i++;
		}
	}
}

void QuickSort(int data[], int left, int right) {
	int i, j, num, temp;

	if(right > left){
		num = data[right];
		i = left - 1;
		j = right;

		while(TRUE){
			while(data[++i] < num);
			while(data[--j] > num);
			if(i >= j)
				break;
			temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
		temp = data[i];
		data[i] = data[right];
		data[right] = temp;

		QuickSort(data, left, i-1);
		QuickSort(data, i+1, right);
	}
}

void DisplayBuffer() {
	int i;

	for(i = 0; i < MAX; i++){
		if((i % 10) == 0)
			printf("\n");

		printf("%4d ", Buf[i]);

	}
	printf("\n");
}

int IsNumberExit(int number, int index) {
	int i;

	for( i = 0; i < index; i++){
		if(Buf[i] == number || number == 0)
			return TRUE;
	}

	return FALSE;
}

void main() {
	printf("정렬할 데이터 초기화.\n");

	MakeRandomNumber();
	DisplayBuffer();
	printf("정렬 후 데이터 초기화\n");

	QuickSort(Buf, 0, MAX-1);
	DisplayBuffer();

	printf("\n");
}
