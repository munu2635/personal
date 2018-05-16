	.file	"test.c"
	.comm	Buf,400,32
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$0, -12(%rbp)
	jmp	.L2
.L6:
	movl	-12(%rbp), %eax
	cltq
	movl	Buf(,%rax,4), %eax
	movl	%eax, -4(%rbp)
	movl	-12(%rbp), %eax
	movl	%eax, -8(%rbp)
	jmp	.L3
.L5:
	movl	-8(%rbp), %eax
	subl	$1, %eax
	cltq
	movl	Buf(,%rax,4), %edx
	movl	-8(%rbp), %eax
	cltq
	movl	%edx, Buf(,%rax,4)
	subl	$1, -8(%rbp)
.L3:
	movl	-8(%rbp), %eax
	subl	$1, %eax
	cltq
	movl	Buf(,%rax,4), %eax
	cmpl	-4(%rbp), %eax
	jle	.L4
	cmpl	$0, -8(%rbp)
	jg	.L5
.L4:
	movl	-8(%rbp), %eax
	cltq
	movl	-4(%rbp), %edx
	movl	%edx, Buf(,%rax,4)
	addl	$1, -12(%rbp)
.L2:
	cmpl	$99, -12(%rbp)
	jle	.L6
	movl	$0, %eax
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 5.4.0-6ubuntu1~16.04.9) 5.4.0 20160609"
	.section	.note.GNU-stack,"",@progbits
