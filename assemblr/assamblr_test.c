//	.file 	"test2.c"
#include <stdio.h>
#define MAX 20

int buf[MAX];
// 	.comm buf,80,32 -> .comm 변수명,총 바이트,2^5
//	.text
//	.globl	main
//	.type	main, @funtion

void main(){
	printf("a");
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
//-------------------------------------- 여기까지가 함수 선언/ 바디 시작
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




void a(){}
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

