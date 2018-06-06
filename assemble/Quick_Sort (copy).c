#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void QuickSort(int data[], int, int);
int Buf[] = {1, 10, 9, 8, 3, 5, 4, 6, 7, 2} ;


void Quick(int left, int right) {
	int i, j, num, temp;

	if(right > left){
		num = Buf[right];
		i = left - 1;
		j = right;

		while(1){
			while(Buf[++i] < num);
			while(Buf[--j] > num);
			if(i >= j)
				break;
			temp = Buf[i];
			Buf[i] = Buf[j];
			Buf[j] = temp;
		}
		temp = Buf[i];
		Buf[i] = Buf[right];
		Buf[right] = temp;

		Quick(left, i-1);
		Quick(i+1, right);
	}
}
void DisplayBuffer() {
	int i;

	for(i = 0; i < 10; i++){
		if((i % 10) == 0)
			printf("\n");

		printf("%4d ", Buf[i]);

	}
	printf("\n");
}

void main() {


	Quick(0, 10-1);
	DisplayBuffer();

	printf("\n");
}
