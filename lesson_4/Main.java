package geekbrains.java.dubovik;
// Домашнее задание к 4му уроку, в связи с болезнью прошу отсрочку пару дней (писал через форму обращения)
// Реализовано для поля 5х5 и количество фишек 4, можно исправить на поле любой ширины и любой высоты -
// Проверка условий не зависит от размера поля и количества выстроенных в ряд фишек
// Необходимо реализовать проверку паттерна "Победа на следующем ходу" и поиска варианта точки для этой победы
// Такая проверка будет идентична функции isWin(), но возвращать должна координаты
// Это даст возможность ИИ оценивать ситуацию - закрывать возможную победную клетку игроку
// И реализовывать свою победу если появляется возможность
// Это будет первым усовершенствованием ИИ

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static final int SIZE_MAP = 5;
    private static final int WIN_DOT = 4;
    private static char[][] map;
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if(isWin(DOT_X)) {
                System.out.println("\nВы победили, поздравляем!");
                break;
            }
            if(isMapFull()) {
                System.out.println("\nНичья");
                break;
            }
            aiTurn();
            printMap();
            if(isWin(DOT_0)) {
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

    private static void initMap() {
        map = new char[SIZE_MAP][SIZE_MAP];
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
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
        do {
            x = new Random().nextInt(SIZE_MAP);
            y = new Random().nextInt(SIZE_MAP);
        }
        while (!isValid(x, y));
        map[x][y] = DOT_0;
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < SIZE_MAP && y >= 0 && y < SIZE_MAP && map[x][y] == DOT_EMPTY;
    }

    private static boolean isWin(char checkDOT) {
        int win;
        int diagXpoint, diagYpoint;
        // Проверяем горизонтали
        win = 0;
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (map[i][j] == checkDOT) win++;
                else win = 0;
                if (win >= WIN_DOT) return true;
            }
        }
        //Проверяем вертикали
        win = 0;
        for (int j = 0; j < SIZE_MAP; j++) {
            for (int i = 0; i < SIZE_MAP; i++) {
                if (map[i][j] == checkDOT) win++;
                else win = 0;
                if (win >= WIN_DOT) return true;
            }
        }
        //Проверяем "прямые" диаганоли
        win = 0;
        diagXpoint = 0;
        diagYpoint = SIZE_MAP - WIN_DOT;
        while (diagXpoint != SIZE_MAP - WIN_DOT) {
            for (int i = diagYpoint, j = diagXpoint; i < SIZE_MAP && j < SIZE_MAP; i++, j++) {
                if (map[i][j] == checkDOT) win++;
                else win = 0;
                if (win >= WIN_DOT) return true;
            }
            diagYpoint--;
            if (diagYpoint < 0) {
                diagXpoint++;
                diagYpoint = 0;
            }
        }
        //Проверяем "обратные" диаганоли
        win = 0;
        diagXpoint = WIN_DOT-1;
        diagYpoint = 0;
        while (diagYpoint != SIZE_MAP - WIN_DOT) {
            for (int i = diagYpoint, j = diagXpoint; i < SIZE_MAP && j >= 0; i++, j--) {
                if (map[i][j] == checkDOT) win++;
                else win = 0;
                if (win >= WIN_DOT) return true;
            }
            diagXpoint++;
            if (diagXpoint > SIZE_MAP-1) {
                diagYpoint++;
                diagXpoint = SIZE_MAP-1;
            }
        }

        return false;
    }

}
