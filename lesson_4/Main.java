package geekbrains.java.dubovik;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static final int SIZE_MAP = 8;
    private static final int WIN_DOT = 5;
    private static char[][] map;
    private static int[][] winNext;
    private static String[] patternX;
    private static String[] pattern0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        patternPreWin();
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if(isWin()) {
                System.out.println("\nВы победили, поздравляем!");
                break;
            }
            if(isMapFull()) {
                System.out.println("\nНичья");
                break;
            }
            aiTurn();
            printMap();
            if(isWin()) {
                System.out.println("\nПобедил ИИ!");
                break;
            }
            if(isMapFull()) {
                System.out.println("\nНичья");
                break;
            }
        }
        scanner.close();
    }

    // паттерны которые будет искать AI: "Победа на следующем ходу" и "Победа" (можно прописать другие)
    private static void patternPreWin() {
        patternX = new String[WIN_DOT + 1];
        pattern0 = new String[WIN_DOT + 1];
        char firstPl;
        char seconPl;
        System.out.println("Паттерны \"Победа на следующем ходу\" и \"Победа\" которые будет искать AI:");
        for (int i = 0; i <= WIN_DOT; i++) {
            patternX[i] = "";
            pattern0[i] = "";
            for (int j = 0; j < WIN_DOT; j++) {
                if (i == j) {
                    firstPl = DOT_EMPTY;
                    seconPl = DOT_EMPTY;
                }
                else {
                    firstPl = DOT_X;
                    seconPl = DOT_0;
                }
                patternX[i] += Character.toString(firstPl);
                pattern0[i] += seconPl;
            }
            System.out.println(i + 1 + "-й вариант: " + patternX[i] + " или " + pattern0[i]); //можно убрать эту печать
        }
    }

    private static void initMap() {
        map = new char[SIZE_MAP][SIZE_MAP];
        winNext = new int[SIZE_MAP][SIZE_MAP];
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                map[i][j] = DOT_EMPTY;
                winNext[i][j] = 0; // создаём "нулевую" таблицу возможеостей для AI
            }
        }
    }

    private static void printMap() {
        System.out.println("\nИгровое поле:");
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void humanTurn() {
        int x = 0;
        int y = 0;
        do {
            try {
                System.out.print("\nВведите координату по горизонтали: ");
                y = scanner.nextInt() - 1;
                System.out.print("Введите координату по вертикали: ");
                x = scanner.nextInt() - 1;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Введите числа");
                scanner = new Scanner(System.in);
            }
        } while (!isValid(x, y));
        map[x][y] = DOT_X;
    }

    public static void aiTurn() {
        int x;
        int y;
        System.out.println("\nХод компьютера...");
        if (!winNextStep()) {
            //вместо рандома нужно прописать логику
            do {
                x = new Random().nextInt(SIZE_MAP);
                y = new Random().nextInt(SIZE_MAP);
            }
            while (!isValid(x, y));
            map[x][y] = DOT_0;
        }
    }

    //метод смотрит есть ли возможность победить ИИ (вес 2), если нет, то помешать человеку выиграть (вес 1)
    private static boolean winNextStep() {
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (winNext[i][j] == 2) {
                    map[i][j] = DOT_0;
                    winNext[i][j] = 0;
                    return true;
                }
            }
        }
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (winNext[i][j] == 1) {
                    map[i][j] = DOT_0;
                    winNext[i][j] = 0;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < SIZE_MAP && y >= 0 && y < SIZE_MAP && map[x][y] == DOT_EMPTY;
    }

    private static boolean isWin() {
        String stringMap;
        int posPat;
        int diagJpoint, diagIpoint;

        // Проверяем горизонтали
        for (int i = 0; i < SIZE_MAP; i++) {
//            StringBuilder stringMapBuilder = new StringBuilder();
            stringMap = "";
            for (int j = 0; j < SIZE_MAP; j++) {
                /*написал просто но IJI предложила заменить на StringBuilder
                stringMap += map[i][j];
                подробнее про этот метод http://developer.alexanderklimov.ru/android/java/stringbuffer.php
                stringMapBuilder.append(map[i][j]);
                попробовал concat(), на него не ругается, пока оставлю везде так*/
                stringMap = stringMap.concat(Character.toString(map[i][j]));
            }
//            stringMap = stringMapBuilder.toString();
            //проверка победы после совершённого хода
            if (stringMap.contains(patternX[WIN_DOT]) || stringMap.contains(pattern0[WIN_DOT])) return true;
            //проверка наличия паттернов "победа на следующем ходу" и выставления веса соответствующим точкам
            for (int w = 0; w < WIN_DOT; w++) {
                posPat = stringMap.indexOf(patternX[w]);
                if (posPat != -1) {
                    winNext[i][posPat + w] = 1;
                }
                posPat = stringMap.indexOf(pattern0[w]);
                if (posPat != -1) {
                    winNext[i][posPat + w] = 2;
                }
            }
        }

        //Проверяем вертикали
        for (int j = 0; j < SIZE_MAP; j++) {
            stringMap = "";
            for (int i = 0; i < SIZE_MAP; i++) {
                stringMap = stringMap.concat(Character.toString(map[i][j]));
            }
            //проверка победы после совершённого хода
            if (stringMap.contains(patternX[WIN_DOT]) || stringMap.contains(pattern0[WIN_DOT])) return true;
            //проверка наличия паттернов "победа на следующем ходу" и выставления веса соответствующим точкам
            for (int w = 0; w < WIN_DOT; w++) {
                posPat = stringMap.indexOf(patternX[w]);
                if (posPat != -1) {
                    winNext[posPat + w][j] = 1;
                }
                posPat = stringMap.indexOf(pattern0[w]);
                if (posPat != -1) {
                    winNext[posPat + w][j] = 2;
                }
            }
        }

        //Проверяем "прямые" диаганоли
        diagJpoint = 0;
        diagIpoint = SIZE_MAP - WIN_DOT;
        while (diagJpoint != SIZE_MAP - WIN_DOT) {
            stringMap = "";
            for (int i = diagIpoint, j = diagJpoint; i < SIZE_MAP && j < SIZE_MAP; i++, j++) {
                stringMap = stringMap.concat(Character.toString(map[i][j]));
            }
            //проверка победы после совершённого хода
            if (stringMap.contains(patternX[WIN_DOT]) || stringMap.contains(pattern0[WIN_DOT])) return true;
            //проверка наличия паттернов "победа на следующем ходу" и выставления веса соответствующим точкам
            for (int w = 0; w < WIN_DOT; w++) {
                posPat = stringMap.indexOf(patternX[w]);
                if (posPat != -1) {
                    winNext[diagIpoint + posPat + w][diagJpoint + posPat + w] = 1;
                }
                posPat = stringMap.indexOf(pattern0[w]);
                if (posPat != -1) {
                    winNext[diagIpoint + posPat + w][diagJpoint + posPat + w] = 2;
                }
            }
            //меняем диагональ
            diagIpoint--;
            if (diagIpoint < 0) {
                diagJpoint++;
                diagIpoint = 0;
            }
        }

        //Проверяем "обратные" диаганоли
        diagJpoint = WIN_DOT-1;
        diagIpoint = 0;
        while (diagIpoint != SIZE_MAP - WIN_DOT) {
            stringMap = "";
            for (int i = diagIpoint, j = diagJpoint; i < SIZE_MAP && j >= 0; i++, j--) {
                stringMap = stringMap.concat(Character.toString(map[i][j]));
            }
            //проверка победы после совершённого хода
            if (stringMap.contains(patternX[WIN_DOT]) || stringMap.contains(pattern0[WIN_DOT])) return true;
            //проверка наличия паттернов "победа на следующем ходу" и выставления веса соответствующим точкам
            for (int w = 0; w < WIN_DOT; w++) {
                posPat = stringMap.indexOf(patternX[w]);
                if (posPat != -1) {
                    winNext[diagIpoint + posPat + w][diagJpoint - posPat - w] = 1;
                }
                posPat = stringMap.indexOf(pattern0[w]);
                if (posPat != -1) {
                    winNext[diagIpoint + posPat + w][diagJpoint - posPat - w] = 2;
                }
            }
            //меняем диагональ
            diagJpoint++;
            if (diagJpoint > SIZE_MAP-1) {
                diagIpoint++;
                diagJpoint = SIZE_MAP-1;
            }
        }
        return false;
    }

}
