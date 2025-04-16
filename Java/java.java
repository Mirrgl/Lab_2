import java.util.*;

public class java {

    public static boolean does_have_digits (String word) {
        boolean has_digits = false;
        for (char letter : word.toCharArray()) {
            if (Character.isDigit(letter)) {
                has_digits = true;
                break;
            }
        }
        return has_digits;
    }

    public static String first_ex (String message) {
        char[] message_array = message.toCharArray();
        String new_message = "";
        char letter_prev = message_array[0];
        int count_of_simletters = 1;

        for (int position_of_letter = 1; position_of_letter < message.length(); position_of_letter++) {
            if (letter_prev == message_array[position_of_letter]) {
                count_of_simletters++;
            }
            else {
                new_message += letter_prev + Integer.toString(count_of_simletters);
                count_of_simletters = 1;
            }
            letter_prev = message_array[position_of_letter];
        }

        new_message += letter_prev + Integer.toString(count_of_simletters);

        return (new_message.length() < message.length() ? new_message : message);
    }

    public static int second_ex (String message) {
        HashMap<String, int[]> morze = new HashMap<>();
        morze.put("a", new int[] {1,1});
        morze.put("b", new int[] {3,1});
        morze.put("w", new int[] {1,2});
        morze.put("g", new int[] {1,2});
        morze.put("d", new int[] {2,1});
        morze.put("e", new int[] {1,0});
        morze.put("v", new int[] {3,1});
        morze.put("z", new int[] {2,2});
        morze.put("i", new int[] {2,0});
        morze.put("j", new int[] {1,3});
        morze.put("k", new int[] {1,2});
        morze.put("l", new int[] {3,1});
        morze.put("m", new int[] {0,2});
        morze.put("n", new int[] {1,1});
        morze.put("o", new int[] {0,3});
        morze.put("p", new int[] {2,2});
        morze.put("r", new int[] {2,1});
        morze.put("s", new int[] {3,0});
        morze.put("t", new int[] {0,1});
        morze.put("u", new int[] {2,1});
        morze.put("f", new int[] {3,1});
        morze.put("h", new int[] {4,0});
        morze.put("c", new int[] {2,2});
        morze.put("q", new int[] {1,3});
        morze.put("y", new int[] {1,3});
        morze.put("x", new int[] {2,2});

        String[] words = message.split(" ");

        int[][] morz_words = new int[words.length][2];
        for (int[] morz_word : morz_words) {
            morz_word[0] = 0;
            morz_word[1] = 0;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (does_have_digits(word)) {
                System.out.println("Only words!");
                System.exit(1);
            }
            for (char letter : word.toCharArray()) {
                morz_words[i][0] += morze.get(""+letter)[0];
                morz_words[i][1] += morze.get(""+letter)[1];
            }
        }

        HashSet<int[]> set_of_morz_words = new HashSet<>();
        set_of_morz_words.add(morz_words[0]);
        for (int[] morz_word : morz_words) {
            boolean isNotContained = true;
            for (int[] set_word : set_of_morz_words) {
                if (set_word[0] == morz_word[0] && set_word[1] == morz_word[1]) {
                    isNotContained = false;
                    break;
                }
            }
            if (isNotContained) {
                set_of_morz_words.add(morz_word);
            }
        }

        return set_of_morz_words.size();
    }

    public static void third_ex () {
        Scanner in = new Scanner(System.in);
        int num_of_nums = in.nextInt();
        if (num_of_nums < 1) {
            System.out.println("Only numbers > 1");
            System.exit(1);
        }
        for (int i = 0; i < num_of_nums; i++) {
            int num = in.nextInt();

            if (Math.abs(num) < 10) {
                System.out.println(num);
                continue;
            }

            int sum_of_digits = 0;
            int copy_num = num;
            num = Math.abs(num);
            while (num > 0) {
                sum_of_digits += num % 10;
                num /= 10;
            }

            if (sum_of_digits < 10) {
                System.out.println(copy_num);
            }
        }
    }

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String num_string = in.nextLine();
        String message;

        int num = Integer.parseInt(num_string);

        switch (num) {
            case 1:
                message = in.nextLine();
                System.out.println(first_ex(message));
                break;

            case 2:
                message = in.nextLine();
                System.out.println(second_ex(message));
                break;

            case 3:
                third_ex();
                break;

            default:
                System.out.println("Only 1, 2 or 3!");
                System.exit(1);
                break;
        }
    }
}