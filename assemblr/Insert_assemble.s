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
    pushq %rbp
    .cfi_def_cfa_offset 16
    .cfi_offset 6, -16
    movq %rsp, %rsb
    .cfi_def_cfa_register 6

    --insert 함수 호출 사용
    --출력해야하면 여기서 출력

    popq  %rbp
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
  .pushq %rbp
  .cfi_def_cfa_offset 16
  .cfi_def_cfa 6, -16
  .movq %rsp %rbp
  .cfi_def_cfa_register 6
  movl $0, -4(%rbp)
  movl $0, -8(%rbp)
  movl %0, -12(%rbp)
  movl %0, -16(%rbp)
  jmp .L2
.L3:
  addl $1, -4(%rbp)
.L2:
  cmpl $9, -4(%rbp)
  jg .L10
.L4:
  mov buf[i], -16(%rbp) -- Buf값 갖고 오는거 확인


  jmp .L3
.L10:
  nop
  nop
  .pop %rbp
  ret
  .cfi_def_cfa 7, 8
  .cfi_endproc
.LFE1:
  size insert, -insert
