package geekbrains.java.dubovik;

public class Main {

    public static void main(String[] args) {
        Cat cat[] = new Cat[3];
        cat[0] = new Cat("Barsik", 20);
        cat[1] = new Cat("Snejok", 30);
        cat[2] = new Cat("Vas'ka", 40);

        Plate plate = new Plate();
        plate.setFood();

        for (Cat eater: cat) {
            plate.info();
            eater.eat(plate);
            while (!eater.isFullness()) {
                plate.setFood();
                plate.info();
                eater.eat(plate);
            }
        }
        plate.info();
    }
}
