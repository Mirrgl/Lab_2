import kotlin.math.abs

fun first_ex(message: String): String {
    var new_message: String = ""
    var letter_prev: Char = message[0]
    var count_of_simletters: Int = 1

    for (position_of_letter in 1..message.count()-1) {
        if (letter_prev == message[position_of_letter]) {
            count_of_simletters++
        }
        else {
            new_message += letter_prev + count_of_simletters.toString()
            count_of_simletters = 1
        }
        letter_prev = message[position_of_letter]
    }
    new_message += letter_prev + count_of_simletters.toString()

    return (if (new_message.count() < message.count()) new_message else message)
}

fun second_ex(message: String): Int {
    val morze: Map<Char, Array<Int>> = mapOf(
    'a' to arrayOf(1, 1),
    'b' to arrayOf(3, 1),
    'w' to arrayOf(1, 2),
    'g' to arrayOf(1, 2),
    'd' to arrayOf(2, 1),
    'e' to arrayOf(1, 0),
    'v' to arrayOf(3, 1),
    'z' to arrayOf(2, 2),
    'i' to arrayOf(2, 0),
    'j' to arrayOf(1, 3),
    'k' to arrayOf(1, 2),
    'l' to arrayOf(3, 1),
    'm' to arrayOf(0, 2),
    'n' to arrayOf(1, 1),
    'o' to arrayOf(0, 3),
    'p' to arrayOf(2, 2),
    'r' to arrayOf(2, 1),
    's' to arrayOf(3, 0),
    't' to arrayOf(0, 1),
    'u' to arrayOf(2, 1),
    'f' to arrayOf(3, 1),
    'h' to arrayOf(4, 0),
    'c' to arrayOf(2, 2),
    'q' to arrayOf(1, 3),
    'y' to arrayOf(1, 3),
    'x' to arrayOf(2, 2))
    var words = message.split(' ')
    var morz_words = Array(words.size, {arrayOf(0,0)})
    
    for (i in words.indices) {
        var word: String = words[i]
        for (j in word.indices) {
            morz_words[i][0] += morze[word[j]]!!.get(0) as Int
            morz_words[i][1] += morze[word[j]]!!.get(1) as Int
        }
    }

    var set_of_morz_words = mutableSetOf(morz_words[0])
    for (morz_word in morz_words) {
        var isNotContained: Boolean = true
        for (set_word in set_of_morz_words) {
            if (set_word[0] == morz_word[0] && set_word[1] == morz_word[1]) {
                isNotContained = false;
                break;
            }
        }
        if (isNotContained) {
            set_of_morz_words.add(morz_word);
        }
    }
    return set_of_morz_words.size
}

fun third_ex() {
    var num_of_nums: Int = readln()!!.toInt()
    if (num_of_nums < 1) {
        println("Only numbers > 1")
        System.exit(1)
    }
    for (i in 0..num_of_nums-1) {
        var num: Int = readln()!!.toInt()

        if (abs(num) < 10) {
            println(num)
            continue
        }

        var sum_of_digits: Int = 0
        var copy_num: Int = num
        num = abs(num)
        while (num > 0) {
            sum_of_digits += num % 10
            num /= 10
        }

        if (sum_of_digits < 10) {
            println(copy_num)
        }
    }
}

fun main(){
    var switcher : Int = readln()!!.toInt()
    var message: String

    when(switcher) {
        1 -> {
            message = readln()
            println(first_ex(message))
        }
        2 -> {
            message = readln()
            println(second_ex(message))
        }
        3 -> {
            third_ex()
        }
        else -> {
            println("Only 1, 2 or 3")
        }
    }
}