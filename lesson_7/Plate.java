package geekbrains.java.dubovik;

import java.util.Scanner;

public class Plate {
    private int food;

    public void setFood() {
        System.out.print("Сколько положить в тарелку еды: ");
        Scanner input = new Scanner(System.in);
        food += input.nextInt();
    }

    public boolean decreaseFood(int n) {
        boolean state = false;
        if (n > food) {
            System.out.print("Коту нужно больше еды! ");
        }
        else {
            food -= n;
            state = true;
        }
        return state;
    }

    public void info() {
        System.out.println("Количество еды в тарелке: " + food);
    }
}
