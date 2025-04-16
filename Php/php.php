<?php

function first_ex($message) {
    $newMessage = "";
    $previousLetter = $message[0];
    $countOfSimilarLetters = 1;

    for ($positionOfLetter = 1; $positionOfLetter < strlen($message); $positionOfLetter++) {
        if ($previousLetter === $message[$positionOfLetter]) {
            $countOfSimilarLetters++;
        } else {
            $newMessage .= $previousLetter . $countOfSimilarLetters;
            $countOfSimilarLetters = 1;
        }
        $previousLetter = $message[$positionOfLetter];
    }

    $newMessage .= $previousLetter . $countOfSimilarLetters;

    return (strlen($newMessage) < strlen($message) ? $newMessage : $message);
}

function second_ex($message) {
    $morse = [
        'a' => [1, 1], 'b' => [3, 1], 'w' => [1, 2], 'g' => [1, 2], 
        'd' => [2, 1], 'e' => [1, 0], 'v' => [3, 1], 'z' => [2, 2],
        'i' => [2, 0], 'j' => [1, 3], 'k' => [1, 2], 'l' => [3, 1],
        'm' => [0, 2], 'n' => [1, 1], 'o' => [0, 3], 'p' => [2, 2],
        'r' => [2, 1], 's' => [3, 0], 't' => [0, 1], 'u' => [2, 1],
        'f' => [3, 1], 'h' => [4, 0], 'c' => [2, 2], 'q' => [1, 3],
        'y' => [1, 3], 'x' => [2, 2]
    ];
    $words = preg_split('/\s+/', $message);
    $morseWords = array_fill(0, count($words), [0, 0]);

    for ($i = 0; $i < count($words); $i++) {
        $word = $words[$i];
        if (ctype_digit($word[0])) {
            echo "Only words\n";
            return 0;
        }
        
        for ($j = 0; $j < strlen($word); $j++) {
            $morseWords[$i][0] += $morse[$word[$j]][0];
            $morseWords[$i][1] += $morse[$word[$j]][1];
        }
    }

    $setOfMorseWords = [];
    foreach ($morseWords as $morseWord) {
        $setOfMorseWords[serialize($morseWord)] = $morseWord;
    }

    return count($setOfMorseWords);
}

function third_ex() {
    $numberOfNumbers = intval(trim(fgets(STDIN)));
    if ($numberOfNumbers < 1) {
        echo "Only numbers > 1\n";
        exit;
    }
    for ($i = 0; $i < $numberOfNumbers; $i++) {
        $num = intval(trim(fgets(STDIN)));

        if (abs($num) < 10) {
            echo $num . "\n";
            continue;
        }

        $sumOfDigits = 0;
        $copyNum = $num;
        $num = abs($num);
        while ($num > 0) {
            $sumOfDigits += $num % 10;
            $num = intval($num / 10);
        }

        if ($sumOfDigits < 10) {
            echo $copyNum . "\n";
        }
    }
}

$switcher = trim(fgets(STDIN));
switch (intval($switcher)) {
    case 1:
        $message = trim(fgets(STDIN));
        echo first_ex($message);
        break;
    
    case 2:
        $message = trim(fgets(STDIN));
        echo second_ex($message) . "\n";
        break;

    case 3:
        third_ex();
        break;

    default:
        echo "Only 1, 2 or 3\n";
        break;
}
?>