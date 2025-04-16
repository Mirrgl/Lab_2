section .data
    prompt db "Only numbers > 1", 10, 0
    format_int db "%d", 0
    newline db 10, 0

section .bss
    num_of_nums resd 1
    i resd 1
    num resd 1
    copy_num resd 1
    sum_of_digits resd 1

section .text
    global main
    extern scanf, printf, exit

main:
    push ebp
    mov ebp, esp

    mov edi, format_int
    mov esi, num_of_nums
    xor eax, eax
    call scanf

    mov eax, [num_of_nums]
    cmp eax, 1
    jge .loop_init
    
    mov edi, prompt
    xor eax, eax
    call printf
    
    mov eax, 60     
    xor edi, edi        
    syscall

.loop_init:
    mov dword [i], 0    ; i = 0

.loop_condition:
    mov eax, [i]
    cmp eax, [num_of_nums]
    jge .loop_end

    mov rdi, format_int
    mov rsi, num
    xor eax, eax
    call scanf

    ; Check if abs(num) < 10
    mov eax, [num]
    mov [copy_num], eax  

    cdq                 
    xor eax, edx
    sub eax, edx        
    
    cmp eax, 10
    jl .print_num      

    mov dword [sum_of_digits], 0
    mov eax, [num]
    cdq               
    xor eax, edx
    sub eax, edx        

.sum_loop:
    xor edx, edx        
    mov ecx, 10
    div ecx             
    add [sum_of_digits], edx
    
    test eax, eax
    jnz .sum_loop
    
    mov eax, [sum_of_digits]
    cmp eax, 10
    jge .next_iteration
    
.print_num:
    mov rdi, format_int
    mov esi, [copy_num]
    xor eax, eax
    call printf
    
    mov rdi, newline
    xor eax, eax
    call printf

.next_iteration:
    inc dword [i]
    jmp .loop_condition

.loop_end:
    mov rsp, rbp
    pop rbp
    xor eax, eax       
    ret