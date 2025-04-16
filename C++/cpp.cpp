#include <iostream>
#include <string>
#include <map>
#include <vector>
#include <sstream>
#include <set>

using namespace std;

string first_ex(string message) {
    string new_message = "";
    char letter_prev = message[0];
    int count_of_simletters = 1;

    for (int position_of_letter = 1; position_of_letter < message.length(); position_of_letter++) {
        if (letter_prev == message[position_of_letter]) { //сравнение предыдущей буквы и нынешней
            count_of_simletters++;
        }
        else { // иначе прибавление к букве её кол-во строке и сброс счётчика
            new_message += letter_prev + to_string(count_of_simletters);
            count_of_simletters = 1;
        }
        letter_prev = message[position_of_letter]; // обновление предыдущей буквы
    }

    new_message += letter_prev + to_string(count_of_simletters);

    return (new_message.length() < message.length() ? new_message : message);
}

int second_ex(string message) {
    map<char, vector<int>> morze = {{'a', {1,1}},{'b', {3,1}},{'w',{1,2}},{'g',{1,2}},{'d',{2,1}},{'e',{1,0}},{'v',{3,1}},{'z',{2,2}},{'i',{2,0}},{'j',{1,3}},{'k',{1,2}},{'l',{3,1}},{'m',{0,2}},{'n',{1,1}},{'o',{0,3}},{'p',{2,2}},{'r',{2,1}},{'s',{3,0}},{'t',{0,1}},{'u',{2,1}},{'f',{3,1}},{'h',{4,0}},{'c',{2,2}},{'q',{1,3}},{'y',{1,3}},{'x',{2,2}}};
    vector<string> words;
    istringstream is(message);
    string word;
    while (is>>word) { // из потока добавляем слова в вектор. Также проверка на наличие цифр
        if (isdigit(word[0])) {
            cout << "Only words" << endl;
            return 0;
        }
        words.push_back(word);
    }

    vector<vector<int>> morz_words(words.size(), {0,0}); // вектор слов в коде морзе, заполненный нулями

    for (int i = 0; i < words.size(); i++) { // преобразование слов в сумму тире и точек слова
        string word = words[i];
        for (int j = 0; j < word.length(); j++) {
            morz_words[i][0] += morze[word[j]][0];
            morz_words[i][1] += morze[word[j]][1];
        }
    }

    set<vector<int>> set_of_morz_words; // множество из уникальных слов в морзе
    for (vector<int> morz_word: morz_words) {
        set_of_morz_words.insert(morz_word);
    }

    return (set_of_morz_words.size());
}

void third_ex() {
    int num_of_nums = 0;
    cin >> num_of_nums;
    if (num_of_nums < 1) { // проверка
        cout << "Only numbers > 1" << endl;
        exit;
    }
    for (int i = 0; i < num_of_nums; i++) {
        int num = 0;
        cin >> num;
        
        if (abs(num) < 10) { // если число меньше 10, то оно всегда имеет один десятичный разряд
            cout << num << endl;
            continue;
        }

        int sum_of_digits = 0;
        int copy_num = num;
        num = abs(num);
        while (num > 0) { // нахождение суммы цифр числа
            sum_of_digits += num % 10;
            num /= 10;
        }

        if (sum_of_digits < 10) {
            cout << copy_num << endl;
        }
    }
}


int main() {
    string switcher = "";
    string message = "";

    getline(cin,switcher);
    switch (stoi(switcher)) //выбор задания
    {
    case 1: {
            cin >> message;
            cout << first_ex(message);
        }
        break;
    
    case 2: {
            getline(cin, message);
            cout << second_ex(message) << endl;
        }
        break;

    case 3: {
            third_ex();
        }
        break;

    default: {
            cout << "Only 1, 2 or 3" << endl;
        }
        break;
    }
}