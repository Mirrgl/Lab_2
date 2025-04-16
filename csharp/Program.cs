using System;
using System.Collections.Generic;

public class Example
{
    public static bool DoesHaveDigits(string word)
    {
        bool hasDigits = false;
        foreach (char letter in word)
        {
            if (char.IsDigit(letter))
            {
                hasDigits = true;
                break;
            }
        }
        return hasDigits;
    }

    public static string FirstExample(string message)
    {
        char[] messageArray = message.ToCharArray();
        string newMessage = "";
        char previousLetter = messageArray[0];
        int countOfSimilarLetters = 1;

        for (int positionOfLetter = 1; positionOfLetter < message.Length; positionOfLetter++)
        {
            if (previousLetter == messageArray[positionOfLetter])
            {
                countOfSimilarLetters++;
            }
            else
            {
                newMessage += previousLetter + countOfSimilarLetters.ToString();
                countOfSimilarLetters = 1;
            }
            previousLetter = messageArray[positionOfLetter];
        }

        newMessage += previousLetter + countOfSimilarLetters.ToString();

        return (newMessage.Length < message.Length ? newMessage : message);
    }

    public static int SecondExample(string message)
    {
        Dictionary<string, int[]> morse = new Dictionary<string, int[]>
        {
            { "a", new int[] { 1, 1 } },
            { "b", new int[] { 3, 1 } },
            { "w", new int[] { 1, 2 } },
            { "g", new int[] { 1, 2 } },
            { "d", new int[] { 2, 1 } },
            { "e", new int[] { 1, 0 } },
            { "v", new int[] { 3, 1 } },
            { "z", new int[] { 2, 2 } },
            { "i", new int[] { 2, 0 } },
            { "j", new int[] { 1, 3 } },
            { "k", new int[] { 1, 2 } },
            { "l", new int[] { 3, 1 } },
            { "m", new int[] { 0, 2 } },
            { "n", new int[] { 1, 1 } },
            { "o", new int[] { 0, 3 } },
            { "p", new int[] { 2, 2 } },
            { "r", new int[] { 2, 1 } },
            { "s", new int[] { 3, 0 } },
            { "t", new int[] { 0, 1 } },
            { "u", new int[] { 2, 1 } },
            { "f", new int[] { 3, 1 } },
            { "h", new int[] { 4, 0 } },
            { "c", new int[] { 2, 2 } },
            { "q", new int[] { 1, 3 } },
            { "y", new int[] { 1, 3 } },
            { "x", new int[] { 2, 2 } }
        };

        string[] words = message.Split(' ');

        int[][] morseWords = new int[words.Length][];
        for (int i = 0; i < morseWords.Length; i++)
        {
            morseWords[i] = new int[2];
        }

        for (int i = 0; i < words.Length; i++)
        {
            string word = words[i];
            if (DoesHaveDigits(word))
            {
                Console.WriteLine("Only words!");
                Environment.Exit(1);
            }
            foreach (char letter in word)
            {
                morseWords[i][0] += morse[letter.ToString()][0];
                morseWords[i][1] += morse[letter.ToString()][1];
            }
        }

        HashSet<string> setOfMorseWords = new HashSet<string>();
        setOfMorseWords.Add($"{morseWords[0][0]},{morseWords[0][1]}");
        foreach (int[] morseWord in morseWords)
        {
            string morseWordString = $"{morseWord[0]},{morseWord[1]}";
            if (!setOfMorseWords.Contains(morseWordString))
            {
                setOfMorseWords.Add(morseWordString);
            }
        }

        return setOfMorseWords.Count;
    }

    public static void ThirdExample()
    {
        var input = Console.ReadLine();
        int numberOfNumbers = int.Parse(input);
        if (numberOfNumbers < 1)
        {
            Console.WriteLine("Only numbers > 1");
            Environment.Exit(1);
        }
        for (int i = 0; i < numberOfNumbers; i++)
        {
            int number = int.Parse(Console.ReadLine());

            if (Math.Abs(number) < 10)
            {
                Console.WriteLine(number);
                continue;
            }

            int sumOfDigits = 0;
            int copyNumber = number;
            number = Math.Abs(number);
            while (number > 0)
            {
                sumOfDigits += number % 10;
                number /= 10;
            }

            if (sumOfDigits < 10)
            {
                Console.WriteLine(copyNumber);
            }
        }
    }

    public static void Main(string[] args)
    {
        string numString = Console.ReadLine();
        string message;

        int num = int.Parse(numString);

        switch (num)
        {
            case 1:
                message = Console.ReadLine();
                Console.WriteLine(FirstExample(message));
                break;

            case 2:
                message = Console.ReadLine();
                Console.WriteLine(SecondExample(message));
                break;

            case 3:
                ThirdExample();
                break;

            default:
                Console.WriteLine("Only 1, 2 or 3!");
                Environment.Exit(1);
                break;
        }
    }
}