	.file	"Insert_sort.c"
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
	.globl	InsertionSort
	.type	InsertionSort, @function
InsertionSort:
.LFB3:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$0, -12(%rbp)
	jmp	.L5
.L9:
	movl	-12(%rbp), %eax
	cltq
	movl	Buf(,%rax,4), %eax
	movl	%eax, -4(%rbp)
	movl	-12(%rbp), %eax
	movl	%eax, -8(%rbp)
	jmp	.L6
.L8:
	movl	-8(%rbp), %eax
	subl	$1, %eax
	cltq
	movl	Buf(,%rax,4), %edx
	movl	-8(%rbp), %eax
	cltq
	movl	%edx, Buf(,%rax,4)
	subl	$1, -8(%rbp)
.L6:
	movl	-8(%rbp), %eax
	subl	$1, %eax
	cltq
	movl	Buf(,%rax,4), %eax
	cmpl	-4(%rbp), %eax
	jle	.L7
	cmpl	$0, -8(%rbp)
	jg	.L8
.L7:
	movl	-8(%rbp), %eax
	cltq
	movl	-4(%rbp), %edx
	movl	%edx, Buf(,%rax,4)
	addl	$1, -12(%rbp)
.L5:
	cmpl	$99, -12(%rbp)
	jle	.L9
	nop
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE3:
	.size	InsertionSort, .-InsertionSort
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
	jmp	.L11
.L13:
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
	jne	.L12
	movl	$10, %edi
	call	putchar
.L12:
	movl	-4(%rbp), %eax
	cltq
	movl	Buf(,%rax,4), %eax
	movl	%eax, %esi
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	addl	$1, -4(%rbp)
.L11:
	cmpl	$99, -4(%rbp)
	jle	.L13
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
	jmp	.L15
.L19:
	movl	-4(%rbp), %eax
	cltq
	movl	Buf(,%rax,4), %eax
	cmpl	-20(%rbp), %eax
	je	.L16
	cmpl	$0, -20(%rbp)
	jne	.L17
.L16:
	movl	$1, %eax
	jmp	.L18
.L17:
	addl	$1, -4(%rbp)
.L15:
	movl	-4(%rbp), %eax
	cmpl	-24(%rbp), %eax
	jl	.L19
	movl	$0, %eax
.L18:
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
	movl	$0, %eax
	call	InsertionSort
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
