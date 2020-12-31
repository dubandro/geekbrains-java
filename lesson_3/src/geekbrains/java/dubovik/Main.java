package geekbrains.java.dubovik;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Задание 1");
        guess_number();

        /*System.out.println("\nЗадание 2");
        guess_words(); допишу завтра между салатами))*/

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
            
            System.out.print("Если хотите сыграть ещё, введите 1, если хотите выйти - 0: ");
            input_num = input.nextInt();
            if (input_num == 1) continue;
            if (input_num == 0) break;
        }
        input.close();
    }
}