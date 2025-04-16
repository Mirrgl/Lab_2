import Foundation

func firstEx(message: String) -> String {
    var newMessage = ""
    var letterPrev = message[message.startIndex]
    var countOfSimletters = 1

    for positionOfLetter in message.indices.dropFirst() {
        if letterPrev == message[positionOfLetter] {
            countOfSimletters += 1
        } else {
            newMessage += "\(letterPrev)\(countOfSimletters)"
            countOfSimletters = 1
        }
        letterPrev = message[positionOfLetter]
    }

    newMessage += "\(letterPrev)\(countOfSimletters)"

    return newMessage.count < message.count ? newMessage : message
}

func secondEx(message: String) -> Int {
    let morze: [Character: [Int]] = [
        "a": [1, 1], "b": [3, 1], "w": [1, 2], "g": [1, 2], "d": [2, 1],
        "e": [1, 0], "v": [3, 1], "z": [2, 2], "i": [2, 0], "j": [1, 3],
        "k": [1, 2], "l": [3, 1], "m": [0, 2], "n": [1, 1], "o": [0, 3],
        "p": [2, 2], "r": [2, 1], "s": [3, 0], "t": [0, 1], "u": [2, 1],
        "f": [3, 1], "h": [4, 0], "c": [2, 2], "q": [1, 3], "y": [1, 3],
        "x": [2, 2]
    ]
    
    let words = message.split(separator: " ").map(String.init)
    
    for word in words {
        if let firstChar = word.first, firstChar.isNumber {
            print("Only words")
            return 0
        }
    }

    var morzWords = Array(repeating: [0, 0], count: words.count)

    for (i, word) in words.enumerated() {
        for char in word {
            morzWords[i][0] += morze[char]?[0] ?? 0
            morzWords[i][1] += morze[char]?[1] ?? 0
        }
    }

    var setOfMorzWords = Set<[Int]>()
    for morzWord in morzWords {
        setOfMorzWords.insert(morzWord)
    }

    return setOfMorzWords.count
}

func thirdEx() {
    var numOfNums = 0
    if let input = readLine(), let number = Int(input) {
        numOfNums = number
    }
    
    if numOfNums < 1 {
        print("Only numbers > 1")
        return
    }
    
    for _ in 0..<numOfNums {
        if let input = readLine(), let num = Int(input) {
            if abs(num) < 10 {
                print(num)
                continue
            }

            var sumOfDigits = 0
            let copyNum = num
            var absNum = abs(num)
            while absNum > 0 {
                sumOfDigits += absNum % 10
                absNum /= 10
            }

            if sumOfDigits < 10 {
                print(copyNum)
            }
        }
    }
}

if let switcher = readLine() {
    switch Int(switcher) {
    case 1:
        if let message = readLine() {
            print(firstEx(message: message))
        }
    case 2:
        if let message = readLine() {
            print(secondEx(message: message))
        }
    case 3:
        thirdEx()
    default:
        print("Only 1, 2 or 3")
    }
}