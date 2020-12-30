package geekbrains.java.dubovik;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 1:");
        inverseValue();

        System.out.println("\nЗадание 2:");
        fillingValue();

        System.out.println("\nЗадание 3:");
        multiplyValue();

        System.out.println("\nЗадание 4:");
        diagonalValue();

        System.out.println("\nЗадание 5:");
        minmaxValue();

        System.out.print("\nЗадание 6: для массива - ");
//        int[] checkArray = {1, 1, 1, 2, 1};
//        int[] checkArray = {1, 1, 1, 2, 1, 1};
        int[] checkArray = {2, 2, 2, 1, 2, 2, 10, 1};
//        int[] checkArray = {2, 2, 2, 1, 2, 2, 10, 1, 1};
        System.out.print(Arrays.toString(checkArray));
        System.out.println(" - " + checkBalance(checkArray));

        System.out.println("\nЗадание 7:");
        Scanner invalue = new Scanner(System.in);
        System.out.print("Введите на сколько сдвинуть массив (положительное число - вправо, отрицательное - влево: ");
        int inShift = invalue.nextInt();
        int[] newArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int shift;
        String direct;
        if (inShift > 0) {
            direct = "--->>";
            shift = inShift;
        }
        else {
            direct = "<<---";
            shift = Math.abs(newArray.length + inShift);
        }
        System.out.println("Начальный массив - " + Arrays.toString(newArray));
        System.out.print("Сдвиг " + direct + " на " + Math.abs(inShift) + " - ");
        shiftArray(newArray, shift);
    }

    public static void inverseValue() {
        int[] inverseArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(inverseArray));
        for (int i = 0; i < inverseArray.length; i++) {
            if (inverseArray[i] == 0) inverseArray[i] = 1;
            else if (inverseArray[i] == 1) inverseArray[i] = 0;
        }
        System.out.println(Arrays.toString(inverseArray));
    }

    public static void fillingValue() {
        int[] fillArray = new int[8];
        for (int i = 0; i < fillArray.length-1; i++) {
            fillArray[i+1] = fillArray[i] + 3;
        }
        System.out.println(Arrays.toString(fillArray));
    }

    public static void multiplyValue() {
        int[] multiArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(multiArray));
        for (int i = 0; i < multiArray.length; i++) {
            if (multiArray[i] < 6) multiArray[i] = multiArray[i] * 2;
        }
        System.out.println(Arrays.toString(multiArray));
    }

    public static void diagonalValue() {
        int[][] diagArray = new int[5][5];
        for (int i = 0; i < diagArray.length; i++) {
            for (int j = 0; j < diagArray[i].length; j++) {
                if (j == i) diagArray[i][j] = 1;
                System.out.print(diagArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void minmaxValue() {
        int[] minmaxArray = {12, 5, -3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(minmaxArray));
        int min = minmaxArray[1], max = min;
        for (int val : minmaxArray) {
            if(val < min) min = val;
            if(val > max) max = val;
        }
        System.out.println("Минимальное значение в массиве = " + min + ", максимальное = " + max);
    }

    public static boolean checkBalance(int[] arrays) {
        int sum = 0, sum1 = 0;
        boolean res = false;
        for (int val : arrays) {
            sum += val;
        }
        for (int i = 0; i < arrays.length; i++) {
            sum1 += arrays[i];
            sum -= arrays[i];
            if (sum == sum1) {
                res = true;
                System.out.print(" - после позиции " + (i+1));
                break;
            }
        }
        return res;
    }

    public static void shiftArray(int[] arrays, int shifter) {
        int mindNum1, mindNum2;
        for (int s = 1; s <= shifter; s++) {
            mindNum1 = arrays[0];
            for (int i = 0; i < arrays.length-1; i++) {
                mindNum2 = arrays[i+1];
                arrays[i+1] = mindNum1;
                mindNum1 = mindNum2;
            }
            arrays[0] = mindNum1;
        }
        System.out.println(Arrays.toString(arrays));
    }
}