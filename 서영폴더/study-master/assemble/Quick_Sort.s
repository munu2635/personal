	.file	"Quick_Sort.c"
	.comm	Buf,400,32
	.text
	.globl	MakeRandomNumber
	.type	MakeRandomNumber, @function
MakeRandomNumber:
.LFB2:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	$1, -8(%rbp)
	movl	$0, %edi
	call	time
	movl	%eax, %edi
	call	srand
	movl	$100, Buf(%rip)
	jmp	.L2
.L3:
	call	rand
	movl	%eax, %ecx
	movl	$1374389535, %edx
	movl	%ecx, %eax
	imull	%edx
	sarl	$5, %edx
	movl	%ecx, %eax
	sarl	$31, %eax
	subl	%eax, %edx
	movl	%edx, %eax
	movl	%eax, -4(%rbp)
	movl	-4(%rbp), %eax
	imull	$100, %eax, %eax
	subl	%eax, %ecx
	movl	%ecx, %eax
	movl	%eax, -4(%rbp)
	movl	-8(%rbp), %edx
	movl	-4(%rbp), %eax
	movl	%edx, %esi
	movl	%eax, %edi
	call	IsNumberExit
	testl	%eax, %eax
	jne	.L2
	movl	-8(%rbp), %eax
	cltq
	movl	-4(%rbp), %edx
	movl	%edx, Buf(,%rax,4)
	addl	$1, -8(%rbp)
.L2:
	cmpl	$99, -8(%rbp)
	jle	.L3
	nop
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	MakeRandomNumber, .-MakeRandomNumber
	.globl	QuickSort
	.type	QuickSort, @function
QuickSort:
.LFB3:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$32, %rsp
	movq	%rdi, -24(%rbp)
	movl	%esi, -28(%rbp)
	movl	%edx, -32(%rbp)
	movl	-32(%rbp), %eax
	cmpl	-28(%rbp), %eax
	jle	.L12
	movl	-32(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	movl	%eax, -8(%rbp)
	movl	-28(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -16(%rbp)
	movl	-32(%rbp), %eax
	movl	%eax, -12(%rbp)
.L10:
.L6:
	addl	$1, -16(%rbp)
	movl	-16(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	cmpl	-8(%rbp), %eax
	jl	.L6
.L7:
	subl	$1, -12(%rbp)
	movl	-12(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	cmpl	-8(%rbp), %eax
	jg	.L7
	movl	-16(%rbp), %eax
	cmpl	-12(%rbp), %eax
	jge	.L13
	movl	-16(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	movl	%eax, -4(%rbp)
	movl	-16(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rax, %rdx
	movl	-12(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rcx
	movq	-24(%rbp), %rax
	addq	%rcx, %rax
	movl	(%rax), %eax
	movl	%eax, (%rdx)
	movl	-12(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rax, %rdx
	movl	-4(%rbp), %eax
	movl	%eax, (%rdx)
	jmp	.L10
.L13:
	nop
	movl	-16(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	movl	%eax, -4(%rbp)
	movl	-16(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rax, %rdx
	movl	-32(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rcx
	movq	-24(%rbp), %rax
	addq	%rcx, %rax
	movl	(%rax), %eax
	movl	%eax, (%rdx)
	movl	-32(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-24(%rbp), %rax
	addq	%rax, %rdx
	movl	-4(%rbp), %eax
	movl	%eax, (%rdx)
	movl	-16(%rbp), %eax
	leal	-1(%rax), %edx
	movl	-28(%rbp), %ecx
	movq	-24(%rbp), %rax
	movl	%ecx, %esi
	movq	%rax, %rdi
	call	QuickSort
	movl	-16(%rbp), %eax
	leal	1(%rax), %ecx
	movl	-32(%rbp), %edx
	movq	-24(%rbp), %rax
	movl	%ecx, %esi
	movq	%rax, %rdi
	call	QuickSort
.L12:
	nop
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE3:
	.size	QuickSort, .-QuickSort
	.section	.rodata
.LC0:
	.string	"%4d "
	.text
	.globl	DisplayBuffer
	.type	DisplayBuffer, @function
DisplayBuffer:
.LFB4:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	$0, -4(%rbp)
	jmp	.L15
.L17:
	movl	-4(%rbp), %ecx
	movl	$1717986919, %edx
	movl	%ecx, %eax
	imull	%edx
	sarl	$2, %edx
	movl	%ecx, %eax
	sarl	$31, %eax
	subl	%eax, %edx
	movl	%edx, %eax
	sall	$2, %eax
	addl	%edx, %eax
	addl	%eax, %eax
	subl	%eax, %ecx
	movl	%ecx, %edx
	testl	%edx, %edx
	jne	.L16
	movl	$10, %edi
	call	putchar
.L16:
	movl	-4(%rbp), %eax
	cltq
	movl	Buf(,%rax,4), %eax
	movl	%eax, %esi
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	addl	$1, -4(%rbp)
.L15:
	cmpl	$99, -4(%rbp)
	jle	.L17
	movl	$10, %edi
	call	putchar
	nop
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE4:
	.size	DisplayBuffer, .-DisplayBuffer
	.globl	IsNumberExit
	.type	IsNumberExit, @function
IsNumberExit:
.LFB5:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	%edi, -20(%rbp)
	movl	%esi, -24(%rbp)
	movl	$0, -4(%rbp)
	jmp	.L19
.L23:
	movl	-4(%rbp), %eax
	cltq
	movl	Buf(,%rax,4), %eax
	cmpl	-20(%rbp), %eax
	je	.L20
	cmpl	$0, -20(%rbp)
	jne	.L21
.L20:
	movl	$1, %eax
	jmp	.L22
.L21:
	addl	$1, -4(%rbp)
.L19:
	movl	-4(%rbp), %eax
	cmpl	-24(%rbp), %eax
	jl	.L23
	movl	$0, %eax
.L22:
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE5:
	.size	IsNumberExit, .-IsNumberExit
	.section	.rodata
	.align 8
.LC1:
	.string	"\354\240\225\353\240\254\355\225\240 \353\215\260\354\235\264\355\204\260 \354\264\210\352\270\260\355\231\224."
	.align 8
.LC2:
	.string	"\354\240\225\353\240\254 \355\233\204 \353\215\260\354\235\264\355\204\260 \354\264\210\352\270\260\355\231\224"
	.text
	.globl	main
	.type	main, @function
main:
.LFB6:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$.LC1, %edi
	call	puts
	movl	$0, %eax
	call	MakeRandomNumber
	movl	$0, %eax
	call	DisplayBuffer
	movl	$.LC2, %edi
	call	puts
	movl	$99, %edx
	movl	$0, %esi
	movl	$Buf, %edi
	call	QuickSort
	movl	$0, %eax
	call	DisplayBuffer
	movl	$10, %edi
	call	putchar
	nop
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE6:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 5.4.0-6ubuntu1~16.04.9) 5.4.0 20160609"
	.section	.note.GNU-stack,"",@progbits
