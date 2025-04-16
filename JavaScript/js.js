function first_ex(message) {
    let new_message = "";
    let letter_prev = message[0];
    let count_of_simletters = 1;

    for (let position_of_letter = 1; position_of_letter < message.length; position_of_letter++) {
        if (letter_prev === message[position_of_letter]) {
            count_of_simletters++;
        } else {
            new_message += letter_prev + count_of_simletters;
            count_of_simletters = 1;
        }
        letter_prev = message[position_of_letter];
    }

    new_message += letter_prev + count_of_simletters;

    return (new_message.length < message.length ? new_message : message);
}

function second_ex(message) {
    const morze = {
        'a': [1, 1], 'b': [3, 1], 'w': [1, 2], 'g': [1, 2], 'd': [2, 1],
        'e': [1, 0], 'v': [3, 1], 'z': [2, 2], 'i': [2, 0], 'j': [1, 3],
        'k': [1, 2], 'l': [3, 1], 'm': [0, 2], 'n': [1, 1], 'o': [0, 3],
        'p': [2, 2], 'r': [2, 1], 's': [3, 0], 't': [0, 1], 'u': [2, 1],
        'f': [3, 1], 'h': [4, 0], 'c': [2, 2], 'q': [1, 3], 'y': [1, 3],
        'x': [2, 2]
    };
    const words = message.split(/\s+/);
    const morz_words = Array.from({ length: words.length }, () => [0, 0]);

    for (let i = 0; i < words.length; i++) {
        const word = words[i];
        if (/\d/.test(word[0])) {
            console.log("Only words");
            return 0;
        }
        for (let j = 0; j < word.length; j++) {
            morz_words[i][0] += morze[word[j]][0];
            morz_words[i][1] += morze[word[j]][1];
        }
    }

    const set_of_morz_words = new Set(morz_words.map(JSON.stringify));
    return set_of_morz_words.size;
}

function third_ex() {
    let num_of_nums = parseInt(prompt("Enter number of inputs:"));
    if (num_of_nums < 1) {
        console.log("Only numbers > 1");
        return;
    }
    for (let i = 0; i < num_of_nums; i++) {
        let num = parseInt(prompt("Enter a number:"));
        
        if (Math.abs(num) < 10) {
            console.log(num);
            continue;
        }

        let sum_of_digits = 0;
        let copy_num = num;
        num = Math.abs(num);
        while (num > 0) {
            sum_of_digits += num % 10;
            num = Math.floor(num / 10);
        }

        if (sum_of_digits < 10) {
            console.log(copy_num);
        }
    }
}

const switcher = prompt("Enter task number:");
switch (parseInt(switcher)) {
    case 1: {
        const message = prompt("Enter message:");
        console.log(first_ex(message));
        break;
    }
    case 2: {
        const message = prompt("Enter message:");
        console.log(second_ex(message));
        break;
    }
    case 3: {
        third_ex();
        break;
    }
    default: {
        console.log("Only 1, 2 or 3");
        break;
    }
}