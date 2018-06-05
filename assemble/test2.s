	.file	"test2.c"
	.text
	.globl	buf
	.data
	.align 32
	.type	buf, @object
	.size	buf, 40
buf:
	.long	3
	.long	5
	.long	2
	.long	1
	.long	4
	.long	7
	.long	10
	.long	6
	.long	8
	.long	9
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
	subq	$16, %rsp
	movl	$0, -12(%rbp)
	jmp	.L2
.L5:
	movl	-4(%rbp), %eax
	subl	$1, %eax
	cltq
	leaq	0(,%rax,4), %rdx
	leaq	buf(%rip), %rax
	movl	(%rdx,%rax), %edx
	movl	-4(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rcx
	leaq	buf(%rip), %rax
	movl	%edx, (%rcx,%rax)
.L3:
	movl	-12(%rbp), %eax
	subl	$1, %eax
	cltq
	leaq	0(,%rax,4), %rdx
	leaq	buf(%rip), %rax
	movl	(%rdx,%rax), %eax
	cmpl	%eax, -8(%rbp)
	jge	.L4
	cmpl	$0, -4(%rbp)
	jg	.L5
.L4:
	addl	$1, -12(%rbp)
.L2:
	cmpl	$9, -12(%rbp)
	jle	.L3
	movl	$0, %eax
	call	a
	nop
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.section	.rodata
.LC0:
	.string	"everything ok"
	.text
	.globl	a
	.type	a, @function
a:
.LFB1:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	leaq	.LC0(%rip), %rdi
	call	puts@PLT
	leaq	.LC0(%rip), %rdi
	call	puts@PLT
	nop
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE1:
	.size	a, .-a
	.ident	"GCC: (Ubuntu 7.3.0-16ubuntu3) 7.3.0"
	.section	.note.GNU-stack,"",@progbits
