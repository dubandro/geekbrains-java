package geekbrains.java.dubovik;

public class Cat extends Animals {
    private String name;
    private String color;
    static int count;

    public Cat(String name, String color) {
        super(name);
        this.color = color;
        count++;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void run(int distance) {
        if (distance > 200) {
            System.out.println("Кот не может пробежать более 200 метров");
        }
        else super.run(distance);
    }

    @Override
    public void swim(int distance) {
        System.out.println("Котейка плавать не умеет");
    }
}
