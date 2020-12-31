package geekbrains.java.dubovik;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Задание 1");
        guess_number();

        System.out.println("\nЗадание 2");
        guess_words();

    }

    public static void guess_number() {
        Scanner input = new Scanner(System.in);
        int input_num, rand_num, try_num;
        while (true) {
            System.out.println("Попробуйте угадать число от 0 до 9 за три попытки!");
            Random rand = new Random();
            rand_num = rand.nextInt(10);
            try_num = 1;
            while (try_num <= 3) {
                System.out.print("Попытка " + try_num + ": ");
                input_num = input.nextInt();
                if (input_num == rand_num) {
                    System.out.println("Вы угадали!");
                    break;
                } else {
                    if (rand_num > input_num) {
                        System.out.println("Загаданное число больше!");
                    } else {
                        System.out.println("Загаданное число меньше!");
                    }
                    try_num++;
                }
            }
            if (try_num > 3) System.out.println("Вы не угадали :(");
            do {
                System.out.print("Если хотите сыграть ещё, введите 1, если хотите выйти - 0: ");
                input_num = input.nextInt();
            }
            while (input_num < 0 || input_num > 1);

            if (input_num == 1) continue;
            if (input_num == 0) break;
        }
        //input.close();
    }

    public static void guess_words() {
        Scanner input = new Scanner(System.in);
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random ran = new Random();
        String rand_words = words[ran.nextInt(words.length)];
        System.out.println("(" + rand_words + ")");
        System.out.print("Угадайте загаданное слово: ");
        while (true) {
            String input_words = input.next();
            if (rand_words.equals(input_words)) {
                System.out.println("Вы угадали, это слово - " + input_words);
                break;
            }
            else {
                System.out.print("Вы не угадали, но в загаданном и введённом слове могут совпадать каие-то буквы,\n" +
                        "секундочку проверяем: ");
                char[] randWord = rand_words.toCharArray();
                char[] inpuWord = input_words.toCharArray();
                for (int i = 0; i < 15 ; i++) {
                    if (i > randWord.length-1 || i > inpuWord.length-1) {
                        System.out.print("#");
                    }
                    else {
                        if (randWord[i] == inpuWord[i]) {
                            System.out.print(randWord[i]);
                        }
                        else {
                            System.out.print("#");
                        }
                    }
                }
                System.out.print("\nПопробуйте ещё раз: ");
            }
        }
        input.close();
    }

}