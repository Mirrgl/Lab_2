def first_ex(message):
    new_message = ""
    set_message = set()
    for letter in message:
        if letter not in set_message:
            new_message += letter+str(message.count(letter))
            set_message.add(letter)
    return (new_message if len(new_message) < len(message) else message)

def second_ex(message):
    morze = {'a':[1,1], 'b':[3,1], 'w':[1,2], 'g':[1,2], 'd':[2,1], 'e':[1,0], 'v':[3,1], 'z':[2,2], 'i':[2,0], 'j':[1,3], 'k':[1,2], 'l':[3,1], 'm':[0,2], 'n':[1,1], 'o':[0,3], 'p':[2,2], 'r':[2,1], 's':[3,0], 't':[0,1], 'u':[2,1], 'f':[3,1], 'h':[4,0], 'c':[2,2], 'q':[1,3], 'y':[1,3], 'x':[2,2]}
    return len(set([tuple([sum(list(map(lambda x: morze[x][i], word))) for i in range(2)]) if word.isdigit() == 0 else (print("Only words"), exit(0)) for word in message.split()])) #выполняет второе задание

def third_ex():
    num_of_numbers = int(input())
    if num_of_numbers < 1:
        print("Only numbers > 1")
        exit(0)
    for i in range(num_of_numbers):
        num = int(input())
        if abs(num) < 10:
            print(num)
            continue
        if sum([int(i) for i in str(abs(num))]) < 10:
            print(num)

switcher = int(input())

match switcher:
    case 1:
        message = input()
        print(first_ex(message))
    case 2:
        message = input()
        print(second_ex(message))
    case 3:
        third_ex()
    case _:
        print("Only 1, 2, or 3")