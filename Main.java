package geekbrains.java.dubovik;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char greekPi = '\u03C0';
        char symb = '\u2757';
        //char symb = '!';
        //char symb = 33;

        String name = "Vitaly";

        byte bt1 = -128; // до 127
        short st1 = -32768; // до 32767
        int it1, it2;
        long lg1 = 100500L; //для примера
        double pi = 3.14159265358979323;
        System.out.println("\nЧисло double " + greekPi + " = " + pi);
        System.out.println("Число float " + greekPi + " = " + (float)pi);
        System.out.printf("Обычная запись - %.2f\n", pi);

        float a, b, c, d;
        a = (float)(Math.random()*10);
        b = (float)(Math.random()*10);
        c = (float)(Math.random()*10);
        d = (float)(Math.random()*10);
        System.out.println("\nЧетыре случайных float-числа от 0 до 10: a = " + a + ", b = " + b + ", " +
                "c = " + c + ", d = " + d);

        //Задание 3
        System.out.println("Задание 3: выражение a*(b+(c/d)) = " + calculate(a, b, c, d) + "\n");

        it1 = (int)(Math.random()*10);
        it2 = (int)(Math.random()*10);
        System.out.println("Два случайных целых числа от 0 до 10 - первое: " + it1 + ", второе: " + it2);

        //Задание 4
        System.out.println("Задание 4: лежит ли сумма этих чисел между 10 и 20 (включительно) - " +
                between10and20(it1, it2) + "\n");

        //Задание 5
        printSign(it1-it2);

        //Задание 6
        System.out.println(returnSign(it1-it2) + " для отрицательного значения\n");

        //Задание 7
        printName(name, symb);

        //Задание 8
        Scanner in = new Scanner(System.in);
        int year;
        while (true){
            System.out.print("Введите год чтобы узнать високосный он или нет или введите 0 (ноль) для выхода: ");
            year = in.nextInt();
            if(year  == 0) break;
            leapYear(year);
        }

        //Вопрос приведения типов переменных описан в методе
        forUnderstanding();
        }

    public static float calculate(float a, float b, float c, float d){
        return a*(b+(c/d));
    }

    public static boolean between10and20(int num1, int num2){
       /* if((num1 + num2) >= 10 && (num1 + num2) <= 20) return true;
        else return false;*/
        return (num1 + num2) >= 10 && (num1 + num2) <= 20; // IJ предложила исправить на это
    }

    public static void printSign(int num) {
        if(num >= 0) System.out.print("Задание 5-6: Передано положительное число, ");
        else System.out.print("Задание 5-6: Передано отрицательное число, ");
    }

    public static boolean returnSign(int num) {
        return num < 0;
    }

    public static void printName(String name, char em){
        System.out.println("Задание 7: Hello, " + name + em + "\n" +
                "Прошу ответить на вопрос из forUnderstanding()" + "\n");
    }

    public static void leapYear(int year) {
        String leapOrNot = "НЕ високосный.\n";
        int mod4 = year % 4;
        int mod100 = year % 100;
        int mod400 = year % 400;

        if(mod4 == 0 && mod100 != 0) leapOrNot = "високосный!\n";
        if(mod100 == 0 && mod400 == 0) leapOrNot = "високосный!\n";

        System.out.println("\nВы задали год - " + year + ", этот год " + leapOrNot);
    }

    public static void forUnderstanding() {
        /*
        - интересует поведение примитивных переменных и приведение от одного типа к другому
        - ВОПРОС 1: делают ли это, например int = char (или наоборот) в реальном коде?
        - ВОПРОС 2: почему в int, float, double можно напрямую присвоить значение любой примитивной переменной,
                    (кроме boolean конечно), а char, byte и short только через явное приведение
        */

        char c1 = 72;
        char c2 = 'H';
        char c3 = 2;
        char c4 = '!';

        String s1 = "ello";
        String s4 = "3";

        boolean bl1 = true;

        String c1s1c4 = c1 + s1 + c4;
        String c2s4 = c2 + s4;
        String bres = "boolean bl1 = " + bl1; // без "boolean bl1 = " + даёт ошибку

        int ic2 = c2; // присваивает без приведения
        int ic3 = c3;
        int ic23 = ic2 + ic3;
        int ci23 = c2 + c3; // суммирует без приведения

        char cc23 = (char) ic23; // без приведения не работает
        byte bt2 = (byte) c2;
        short st4 = (short) c4;
        float ft2 = c2; // с float и double работает как с int без приведения
        double sum = cc23 + bt2 + st4 + ft2;

        System.out.println("String: " + c1s1c4 + " | " + c2s4 + " | " + bres);
        System.out.println("int: " + ic2 + " + " + ic3 + " = " + ic23 + " - " + ci23 + " = " + (ic23 - ci23));
        System.out.println("Остальные: " + cc23 + "(" + ic23 + ")" + " + " + bt2 + " + " + st4 + " + " + ft2 + " = " + sum);

        // Другой вариант того же:
        String resAll = "\n" +
                "String: " + c1s1c4 + " | " + c2s4 + " | " + bres + "\n" +
                "int: " + ic2 + " + " + ic3 + " = " + ic23 + " - " + ci23 + " = " + (ic23 - ci23) + "\n" +
                "Остальные: " + cc23 + "(" + ic23 + ")" + " + " + bt2 + " + " + st4 + " + " + ft2 + " = " + sum;
        System.out.println("Другой вариант того же: " + resAll);
    }

}
