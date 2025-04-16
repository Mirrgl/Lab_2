require 'set'

def first_ex(message)
  new_message = ""
  letter_prev = message[0]
  count_of_simletters = 1

  for position_of_letter in 1..message.length-1
    if letter_prev == message[position_of_letter]
      count_of_simletters += 1
    else
      new_message += "#{letter_prev}#{count_of_simletters}"
      count_of_simletters = 1
    end
    letter_prev = message[position_of_letter]
  end

  new_message += "#{letter_prev}#{count_of_simletters}"

  new_message.length < message.length ? new_message : message
end

def second_ex(message)
  morze = {
    'a' => [1, 1], 'b' => [3, 1], 'w' => [1, 2], 'g' => [1, 2],
    'd' => [2, 1], 'e' => [1, 0], 'v' => [3, 1], 'z' => [2, 2],
    'i' => [2, 0], 'j' => [1, 3], 'k' => [1, 2], 'l' => [3, 1],
    'm' => [0, 2], 'n' => [1, 1], 'o' => [0, 3], 'p' => [2, 2],
    'r' => [2, 1], 's' => [3, 0], 't' => [0, 1], 'u' => [2, 1],
    'f' => [3, 1], 'h' => [4, 0], 'c' => [2, 2], 'q' => [1, 3],
    'y' => [1, 3], 'x' => [2, 2]
  }
  
  words = message.split
  for word in words
    return puts "Only words" if word.match?(/\d/)
  end

  morz_words = Array.new(words.size) { [0, 0] }

  words.each_with_index do |word, i|
    word.each_char do |char|
      morz_words[i][0] += morze[char][0]
      morz_words[i][1] += morze[char][1]
    end
  end

  set_of_morz_words = Set.new(morz_words)

  set_of_morz_words.size
end

def third_ex
  num_of_nums = gets.to_i
  if num_of_nums < 1
    puts "Only numbers > 1"
    return
  end

  num_of_nums.times do
    num = gets.to_i

    if num.abs < 10
      puts num
      next
    end

    sum_of_digits = 0
    copy_num = num
    num = num.abs
    while num > 0
      sum_of_digits += num % 10
      num /= 10
    end

    puts copy_num if sum_of_digits < 10
  end
end

switcher = gets.chomp
case switcher.to_i
when 1
  message = gets.chomp
  puts first_ex(message)
when 2
  message = gets.chomp
  puts second_ex(message)
when 3
  third_ex
else
  puts "Only 1, 2 or 3"
end