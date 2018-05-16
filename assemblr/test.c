#include <stdio.h>
#define MAX 100 // 이게 어떤식으로 변환되는지 확인


int Buf[MAX]; // 요거 전역변수

int main() {

  //이거 삽입정렬의 핵심코드부분
  int i, j, min, dummy;
  for(i = 0; i < MAX; i++){
    dummy = Buf[i];
    j = i;
    while(Buf[j-1] > dummy && j > 0){
      Buf[j] = Buf[j-1];
      j--;
      }
      Buf[j] = dummy;
      }
}

//TEST CODE FOR ASSEMBLR
