  	.globl buf
  	.data
  	.align 32
  	.type buf, @object
  	.size buf, 40
buf:
  	.long 5
  	.long 10
  	.long 7
  	.long 2
  	.long 6
  	.long 1
  	.long 9
  	.long 3
  	.long 8
  	.long 4
  	.section	.rodata
.LC0:
	.string "%2d "
	.text
  	.globl main
	.type main, @function
main:
.LFB0:
    	.cfi_startproc
    	pushq 	%rbp
    	.cfi_def_cfa_offset 16
    	.cfi_offset 6, -16
    	movq 	%rsp, %rbp
    	.cfi_def_cfa_register 6
	call insert
	subq	$16, %rsp
	movl	$0, -4(%rbp)
	jmp	.L2
.L3:
	movl	-4(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	leaq	buf(%rip), %rax
	movl	(%rdx,%rax), %eax
	movl	%eax, %esi
	leaq	.LC0(%rip), %rdi
	movl	$0, %eax
	call 	printf@PLT
	addl	$1, -4(%rbp)
.L2:
	cmpl	$9, -4(%rbp)
	jle	.L3
	movl	$0, %eax
	nop
	popq  	%rbp
    	.cfi_def_cfa 7, 8
    	ret
    	.cfi_endproc
.LFE0:
  	.size main, .-main
	.section	.rodata
.LC1:
	.string "everything ok"
	.text
  	.globl insert
  	.type insert, @function
insert:
.LFB1:
  	.cfi_startproc
  	pushq	 %rbp
  	.cfi_def_cfa_offset 16
  	.cfi_def_cfa 6, -16
  	movq 	%rsp, %rbp
  	.cfi_def_cfa_register 6
  	subq	$16, %rbp
	leaq	.LC1(%rip), %rdi
	call	puts@PLT
.L7:
  	cmpl 	$9, -4(%rbp)
  	jg 	.L10

.L4:
	movl	-8(%rbp), %eax
	movl	%eax, -4(%rbp)
	cltq
	leaq	0(,%rax, 4), %rdx
	leaq	buf(%rip), %rax
	movl	(%rdx, %rax), %eax
	movl	%eax, -16(%rbp)
	cltq
.L5:
	movl	-8(%rbp), %eax
	subl	$1, %eax
	cltq
	leaq	0(,%rax,4), %rdx
	leaq	buf(%rip), %rax
	movl	(%rdx, %rax), %eax
	cmpl	%eax, -16(%rbp)
	jnae	.L6
	movl	$0, %ebp
	cmpl	-8(%rbp), %ebp
	jnae	.L6
	leaq	0(,%rax,4), %rdx
	leaq	buf(%rip), %rax
	movl	(%rdx, %rax), %eax
	movl	-8(%rbp), %eax
	cltq
	leaq 	0(,%rax,4), %rcx
	leaq	buf(%rip), %rcx
	movl	%edx,	(%rcx, %rax)
	movl	%eax, buf(%rdx)
	subl	$1, -8(%rbp)
	jmp	.L5
.L6:
	movl	-8(%rbp), %eax
	cltq
	leaq 	0(,%rbp, 4), %rcx
	leaq	buf(%rip), %rax
	movl	-16(%rbp), %edx
	movl	%edx,  (%rcx, %rax)
	addl	$1, -4(%rbp)
  	jmp	.L7
.L10:
  	leaq	.LC0(%rip), %rbp
	call	puts@PLT
  	nop
  	pop 	%rbp
  	ret
  	.cfi_def_cfa 7, 8
  	.cfi_endproc
.LFE1:
  	.size insert, .-insert
