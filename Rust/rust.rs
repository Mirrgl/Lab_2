use std::collections::HashMap;
use std::io::{self, BufRead};
use std::collections::HashSet;

fn first_ex(message: &str) -> String {
    let mut new_message = String::new();
    let mut letter_prev = message.chars().nth(0).unwrap();
    let mut count_of_simletters = 1;

    for position_of_letter in 1..message.len() {
        if letter_prev == message.chars().nth(position_of_letter).unwrap() {
            count_of_simletters += 1;
        } else {
            new_message.push(letter_prev);
            new_message.push_str(&count_of_simletters.to_string());
            count_of_simletters = 1;
        }
        letter_prev = message.chars().nth(position_of_letter).unwrap();
    }

    new_message.push(letter_prev);
    new_message.push_str(&count_of_simletters.to_string());

    if new_message.len() < message.len() {
        new_message
    } else {
        message.to_string()
    }
}

fn second_ex(message: &str) -> usize {
    let morze: HashMap<char, (usize, usize)> = [
        ('a', (1, 1)), ('b', (3, 1)), ('w', (1, 2)), ('g', (1, 2)), 
        ('d', (2, 1)), ('e', (1, 0)), ('v', (3, 1)), ('z', (2, 2)), 
        ('i', (2, 0)), ('j', (1, 3)), ('k', (1, 2)), ('l', (3, 1)), 
        ('m', (0, 2)), ('n', (1, 1)), ('o', (0, 3)), ('p', (2, 2)), 
        ('r', (2, 1)), ('s', (3, 0)), ('t', (0, 1)), ('u', (2, 1)), 
        ('f', (3, 1)), ('h', (4, 0)), ('c', (2, 2)), ('q', (1, 3)), 
        ('y', (1, 3)), ('x', (2, 2))
    ].iter().cloned().collect();

    let words: Vec<&str> = message.split_whitespace().collect();
    let mut morz_words = vec![(0, 0); words.len()];

    for (i, &word) in words.iter().enumerate() {
        if word.chars().any(|c| c.is_digit(10)) {
            println!("Only words");
            return 0;
        }
        for c in word.chars() {
            if let Some(&(dot, dash)) = morze.get(&c) {
                morz_words[i].0 += dot;
                morz_words[i].1 += dash;
            }
        }
    }

    let set_of_morz_words: HashSet<(usize, usize)> = morz_words.into_iter().collect();
    set_of_morz_words.len()
}

fn third_ex() {
    let stdin = io::stdin();
    let num_of_nums: usize = stdin.lock().lines().next().unwrap().unwrap().parse().unwrap();
    
    if num_of_nums < 1 {
        println!("Only numbers > 1");
        return;
    }

    for _ in 0..num_of_nums {
        let num: i32 = stdin.lock().lines().next().unwrap().unwrap().parse().unwrap();

        if num.abs() < 10 {
            println!("{}", num);
            continue;
        }

        let mut sum_of_digits = 0;
        let mut copy_num = num;
        let mut num = num.abs();

        while num > 0 {
            sum_of_digits += num % 10;
            num /= 10;
        }

        if sum_of_digits < 10 {
            println!("{}", copy_num);
        }
    }
}

fn main() {
    let stdin = io::stdin();
    let mut switcher = String::new();
    let mut message = String::new();
    stdin.read_line(&mut switcher);

    match switcher.trim().parse::<i32>() {
        Ok(1) => {
            stdin.read_line(&mut message);
            println!("{}", first_ex(&message));
        },
        Ok(2) => {
            stdin.read_line(&mut message);
            println!("{}", second_ex(&message));
        },
        Ok(3) => {
            third_ex();
        },
        _ => {
            println!("Only 1, 2 or 3");
        }
    }
}