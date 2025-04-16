package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func Scan_str() string { // функция читает строку
	in := bufio.NewReader(os.Stdin)
	str, _ := in.ReadString('\n')
	return str
}

func first_ex(message string) string {
	new_message := ""
	letter_prev := string(message[0])
	count_of_simletters := 1

	for position_of_letter := 1; position_of_letter < len(message); position_of_letter++ {
		if letter_prev == string(message[position_of_letter]) {
			count_of_simletters++
		} else {
			new_message += letter_prev + strconv.Itoa(count_of_simletters)
			count_of_simletters = 1
		}
		letter_prev = string(message[position_of_letter])
	}
	new_message += letter_prev + strconv.Itoa(count_of_simletters)

	if len(new_message) < len(message) {
		return new_message
	} else {
		return message
	}
}

func second_ex(message string) int {
	var morze = map[string][2]int{"a": {1, 1}, "b": {3, 1}, "w": {1, 2}, "g": {1, 2}, "d": {2, 1}, "e": {1, 0}, "v": {3, 1}, "z": {2, 2}, "i": {2, 0}, "j": {1, 3}, "k": {1, 2}, "l": {3, 1}, "m": {0, 2}, "n": {1, 1}, "o": {0, 3}, "p": {2, 2}, "r": {2, 1}, "s": {3, 0}, "t": {0, 1}, "u": {2, 1}, "f": {3, 1}, "h": {4, 0}, "c": {2, 2}, "q": {1, 3}, "y": {1, 3}, "x": {2, 2}}
	words := strings.Fields(message)
	morz_words := make([][2]int, len(words))
	for i := 0; i < len(morz_words); i++ {
		morz_words[i] = [2]int{0, 0}
	}

	for i := 0; i < len(words); i++ {
		word := words[i]
		if _, err := strconv.Atoi(word); err == nil {
			fmt.Println("Only words!")
			os.Exit(1)
		}
		for j := 0; j < len(word); j++ {
			morz_words[i][0] += morze[string(word[j])][0]
			morz_words[i][1] += morze[string(word[j])][1]
		}
	}

	var set_of_morz_words = make([][2]int, 1)
	set_of_morz_words[0] = morz_words[0]
	for _, morz_word := range morz_words[1:] {
		is_in_set := true
		for i := 0; i < len(set_of_morz_words); i++ {
			if morz_word == set_of_morz_words[i] {
				is_in_set = false
				break
			}
		}
		if is_in_set == false {
			break
		}
		set_of_morz_words = append(set_of_morz_words, morz_word)
	}

	return len(set_of_morz_words)
}

func third_ex() {
	var num_of_nums string
	fmt.Scan(&num_of_nums)
	var num_of_nums_int, err = strconv.Atoi(num_of_nums)

	if err != nil {
		fmt.Println("Only numbers!")
		os.Exit(1)
	}

	for i := 0; i < num_of_nums_int; i++ {
		var num string
		fmt.Scan(&num)
		var num_int, err = strconv.Atoi(num)
		if err != nil {
			fmt.Println("Only numbers!")
			os.Exit(1)
		}
		if math.Abs(float64(num_int)) < 10 {
			fmt.Println(num_int)
			continue
		}

		sum_of_digits := 0
		num_int = int(math.Abs(float64(num_int)))
		for _, j := range num {
			var digit, _ = strconv.Atoi(string(j))
			sum_of_digits += digit
		}

		if sum_of_digits < 10 {
			fmt.Println(num)
		}
	}
}

func main() {
	var (
		switcher string
		message  string
	)

	fmt.Scan(&switcher)

	switcher_int, err := strconv.Atoi(switcher)

	if err != nil {
		fmt.Println("Only numbers!")
		os.Exit(1)
	}

	switch switcher_int {
	case 1:
		fmt.Scan(&message)
		fmt.Println(first_ex(message))

	case 2:
		message = Scan_str()
		fmt.Println(second_ex(message))

	case 3:
		third_ex()

	default:
		fmt.Println("Only 1, 2 or 3")
		os.Exit(1)
	}
}
