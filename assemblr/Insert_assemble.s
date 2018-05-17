  	.globl buf
  	.data
  	.align 16
  	.type buf, @object
  	.size buf, 40 // int x 10
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
  	.text
  	.globl main
	.type main, @function
main:
.LFB0:
    	.cfi_startproc
    	pushq 	%rbp
    	.cfi_def_cfa_offset 16
    	.cfi_offset 6, -16
    	movq 	%rsp, %rsb
    	.cfi_def_cfa_register 6
	call insert
	popq  	%rbp
    	.cfi_def_cfa 7, 8
    	ret
    	.cfi_endproc
.LFE0:
  	size main, -main
  	.globl insert
  	.type insert, @function
insert:
.LFB1:
  	.cfi_startproc
  	.pushq	 %rbp
  	.cfi_def_cfa_offset 16
  	.cfi_def_cfa 6, -16
  	.movq 	%rsp %rbp
  	.cfi_def_cfa_register 6
  	subq	$16, %rbp
.L2:
  	cmpl 	$9, -4(%rbp)
  	jg 	.L10
.L4:
	movl	-8(%rbp), %eax
	cltq
	leaq	0(,%rax, 4), %rdx
	leaq	buf(%rip), %rax
	movl	(%rdx, %rax), %eax
	move	%eax, -16(%rbp)
	movl	-8(%rbp), -4(%rbp)
	cltq
.L5:
	movl	-8(%rbp), %eax
	subl	$1, %eax
	cltq
	cmpl	buf(%rip), -16(%rbp)
	jnae	.L6
	cmpl	-8(%rbp), $0
	jnae	.L6
	movl	-8(%rbp)
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
	lead 	0(,%rbp, 4), %rcx
	lead	buf(%rip), %rax
	movl	-16(%rbp), %edx
	movl	%edx,  %(rcx, %rax)
	addl	$1 -4(%rbp)
  	jmp	.L3
.L10:
  	nop
  	nop
  	.pop 	%rbp
  	ret
  	.cfi_def_cfa 7, 8
  	.cfi_endproc
.LFE1:
  	size insert, -insert
