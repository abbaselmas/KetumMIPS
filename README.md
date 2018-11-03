# KetumMIPS
backup

The Implementation of KETUM

 

As a senior architect building the next generation embedded processors as the subset of MIPS processors. The new processor shall be fast, and has the features of reduced instruction set of early MIPS processors. Before starting to design the processor itself, you must first determine the Instruction Set Architecture (ISA) for this new platform. After several weeks, you came up with a new design with the following properties:

 

·      The system will use a general purpose load/store architecture

·      The system will have 8 registers, each 16 bits wide

·      Instructions will have a fixed width of 16 bits

·      The instructions will include the following instructions found in MIPS, including loads/stores, arithmetic/logical, operations with immediates, shifts, branches, and jumps

o   add, sub, and, or, sll, srl, addi, andi, lw, sw, beq, bne, j, jr, jal, add.s

·      The shift instructions require only movement of half registers (ie: you are changing the location of a bit atmost 8 places). 

·      The registers available are $zero,$a0,$v0,$t0,$t1,$s0,$s1 and $ra with the addresses of 0 through 7 respectively.

·      $a0 and $v0 are used for input and output parameters respectively.




                                          add      $t4,      $zero,  $zero

                  main                beq      $a1,     $zero,  end

                                          add      $t4,      $t4,      $a0

                                          sub      $a1,     $a1,     1

                                          j           main

                  end                  addi     $t4,      $t4,      999

                                          add      $v0,     $t4,      $zero
