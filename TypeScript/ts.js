function firstEx(message) {
    let newMessage = "";
    let previousLetter = message[0];
    let countOfSimilarLetters = 1;
    for (let positionOfLetter = 1; positionOfLetter < message.length; positionOfLetter++) {
        if (previousLetter === message[positionOfLetter]) { // сравнение предыдущей буквы и нынешней
            countOfSimilarLetters++;
        }
        else { // иначе прибавление к букве её кол-во строке и сброс счётчика
            newMessage += previousLetter + countOfSimilarLetters.toString();
            countOfSimilarLetters = 1;
        }
        previousLetter = message[positionOfLetter]; // обновление предыдущей буквы
    }
    newMessage += previousLetter + countOfSimilarLetters.toString();
    return (newMessage.length < message.length ? newMessage : message);
}
function secondEx(message) {
    const morse = new Map([
        ['a', [1, 1]], ['b', [3, 1]], ['w', [1, 2]], ['g', [1, 2]],
        ['d', [2, 1]], ['e', [1, 0]], ['v', [3, 1]], ['z', [2, 2]],
        ['i', [2, 0]], ['j', [1, 3]], ['k', [1, 2]], ['l', [3, 1]],
        ['m', [0, 2]], ['n', [1, 1]], ['o', [0, 3]], ['p', [2, 2]],
        ['r', [2, 1]], ['s', [3, 0]], ['t', [0, 1]], ['u', [2, 1]],
        ['f', [3, 1]], ['h', [4, 0]], ['c', [2, 2]], ['q', [1, 3]],
        ['y', [1, 3]], ['x', [2, 2]]
    ]);
    const words = message.split(' ');
    const morseWords = Array.from({ length: words.length }, () => [0, 0]); // вектор слов в коде морзе, заполненный нулями
    for (let i = 0; i < words.length; i++) { // преобразование слов в сумму тире и точек слова
        const word = words[i];
        for (let j = 0; j < word.length; j++) {
            const morseValue = morse.get(word[j]);
            if (morseValue) {
                morseWords[i][0] += morseValue[0];
                morseWords[i][1] += morseValue[1];
            }
        }
    }
    const setOfMorseWords = new Set(); // множество из уникальных слов в морзе
    for (const morseWord of morseWords) {
        setOfMorseWords.add(morseWord.join(','));
    }
    return setOfMorseWords.size;
}
function thirdEx() {
    let numOfNumbers = 0;
    const input = prompt("Enter number of inputs:");
    if (input) {
        numOfNumbers = parseInt(input);
    }
    if (numOfNumbers < 1) { // проверка
        console.log("Only numbers > 1");
        return;
    }
    for (let i = 0; i < numOfNumbers; i++) {
        const numInput = prompt("Enter a number:");
        let num = numInput ? parseInt(numInput) : 0;
        if (Math.abs(num) < 10) { // если число меньше 10, то оно всегда имеет один десятичный разряд
            console.log(num);
            continue;
        }
        let sumOfDigits = 0;
        const copyNum = num;
        num = Math.abs(num);
        while (num > 0) { // нахождение суммы цифр числа
            sumOfDigits += num % 10;
            num = Math.floor(num / 10);
        }
        if (sumOfDigits < 10) {
            console.log(copyNum);
        }
    }
}
function main() {
    const switcherInput = prompt("Enter task number:");
    let switcher = switcherInput ? parseInt(switcherInput) : 0;
    switch (switcher) { // выбор задания
        case 1: {
            const messageInput = prompt("Enter message:");
            if (messageInput) {
                console.log(firstEx(messageInput));
            }
            break;
        }
        case 2: {
            const messageInput = prompt("Enter message:");
            if (messageInput) {
                console.log(secondEx(messageInput));
            }
            break;
        }
        case 3: {
            thirdEx();
            break;
        }
        default: {
            console.log("Only 1, 2 or 3");
            break;
        }
    }
}
main();
