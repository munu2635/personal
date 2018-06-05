//	.file 	"test2.c"
#include <stdio.h>
#define MAX 10

void a();
int buf[MAX] = { 3, 5, 2, 1, 4, 7, 10, 6, 8, 9 };
//	-> int buf[MAX] = { 3, 5, 2, 1, 4 };
//	.globl buf
//	.data
//	.align	16
//	.type	buf, @object
//	.size	buf, 20
//buf:	-------------------------------- buf object
//	.long	3			왭 int로 해놨는데 long형으로 설정되는거지?
//	.long	5
//	.long	2
//	.long	1
//	.long 	4
//	.text
//	.globl	main
//	.type	main,	@funtion



//	-> int buf[MAX];
// 	.comm buf,80,32 -> .comm 변수명,총 바이트,2^5
//	.text
//	.globl	main
//	.type	main, @funtion

void main(){
	int i, j, dummy;
	//make ather
	for(i = 0; i<MAX ;i++){
		 while (buf[i-1] > dummy && j >  0 ){
			 buf[j] = buf[j-1];
		}
	}


//	leaq 0(,%rax,4), %rcx
//	leaq buf(%rip), %raxs
//	movl (%rdx, %rax), %eax
//	movl	%eax, -4(%rbp)
//	addl	$1, -8(%rbp)



	// movl 4+buf(%rip), %eax
	// movl %eax, -4(%rbp)
	// maybe wasn't buf['1'] anther number?


	a(); //이거 확인 해야함
	//call a just call...

}

//-------------------------------------- 함수 시작
// main:
// .LFBO:
//	.cfi_startproc
//	pushq	%rbp
//	.cfi_def_cfa_offset 16
//	.cfi_offset 6, -16
//	movq	%rsp, %rdp
//	.cfi_def_cfa_register 6


// 	subq	$16, %rsp --> // int i, j, k, l;
//	movl	$0, -4(%rbp) //for( i = 0; ... part
//	jmp	.L2 //.L2로 점프 비교값부터 확인 for문 진입
//.L3:
//	addl	$1, -4(%rbp) // for문에서 i++ 라면
//.L2:
//	cmpl $4, -4(%rbp) // for문에서 비교하는  부분인건가? -
 		// cmpl a, b a와 b를 비교한다.
		// je 비교결과가 같을 때 점프
		// jne 비교결과가 다를 떄 점프
		// jne 크지 않을 때 점프
		// jle 작거나 같을때 점프 등등 필요할때ㅐ 찾아보기
//	jle .l3 // 작거나 같을때 .L3으로 점프한다 만약 값이 크다면 다음줄로
//.L6 // 여기 부터 while문
//	je .L8  // 비교결과가 같을때 .L8로 이동
//	jmp .L6 // 아니면 계속 반복
//.L8 // 분기가 있을때마다 .Lx를 사용 x값을 설정하는 건 어떻게 되는건지는 모름
//	nop
//	nop
//---------------------------------------------- 여기서 부터 다시 마무리 부분으로 이동

//-------------------------------------- 여기까지가 함수 선언/ 바디 시작
//case1	= > printf("a");
//	movl	$97, %edi
//	call	putchar
//	nop
//-------------------------------------- 함수 바디 끝
//	popq	%rbp
//	.cfi_def_cfa	7, 8
//	ref
//	.cf_endproc
//-------------------------------------- 함수 끝마무리
// .LFE0:
//	.size	main, .-main	-------- 함수 종료
//	.globl	a		-------- 다음 함수 정의
//	.type	a, @function




void a(){

	printf("everything ok\n");
	printf("everything ok\n");
} //기본 함수 print
//-------------------------------------- a 함수 시작
// a:
// .LFB1:
//	.cfi_startproc
//	pushq	%rbp
//	.cfi_def_cfa_offset 16
//	.cfi_offset 6, -16
//	movq	%rsp, %rbp
//	.cfi_def_cfa_register 6
//	nop
//	popq	%rbp
//	.cfi_def_cfa 7, 8
//	ret
//	.cfi_endproc
// .LFE1:
//	.size	a, .-a
//	.ident "GCC: - "
//	.section	.note.GNU-stack, "",@progbits
//-------------------------------------- a 함수 끝
